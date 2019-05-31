package by.ladyka.carapp

import by.ladyka.carapp.data.Bundle
import by.ladyka.carapp.data.DatabaseVerification
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.experimental.categories.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.time.ZonedDateTime

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Category(DataTest.class)
abstract class BaseWebTest extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    @Autowired
    private ObjectMapper objectMapper

    @Autowired
    private DatabaseVerification databaseVerification

    @Value('${oms.enable-tables-check:true}')
    boolean enableTablesCheck

    def startRowCounts
    def tableNames

    def setup() {
        if (enableTablesCheck) {
            tableNames = databaseVerification.findTableNames()
            startRowCounts = databaseVerification.getRowCounts(tableNames)
        }
    }

    def cleanup() {
        if (enableTablesCheck) {
            def finishRowCounts = databaseVerification.getRowCounts(tableNames)
            finishRowCounts.forEach({ name, finishCount ->
                assert startRowCounts.get(name, 0) == finishCount : "Test data is not rolled back from table: \'$name\'"
            })
        }
    }

    ResponseEntity putForEntity(String url, request, Class resultType) {
        HttpEntity<String> entity = new HttpEntity<String>(request?:objectMapper.writeValueAsString(request), new HttpHeaders(contentType: MediaType.APPLICATION_JSON))
        restTemplate.exchange(url, HttpMethod.PUT, entity, resultType, [])
    }

    ResponseEntity putEmpty(String url, Class resultType) {
        restTemplate.exchange(url, HttpMethod.PUT, HttpEntity.EMPTY, resultType, [])
    }

    ResponseEntity deleteForEntity(String url, request, Class resultType) {
        HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(request), new HttpHeaders(contentType: MediaType.APPLICATION_JSON))
        restTemplate.exchange(url, HttpMethod.DELETE, entity, resultType, [])
    }

    protected static void assertZonedDateTime(actualValue, expectedValue) {
        if (expectedValue == null) {
            assert actualValue == null
        } else {
            ZonedDateTime actualTime = actualValue instanceof String ? ZonedDateTime.parse(actualValue) : actualValue
            ZonedDateTime expectedTime = expectedValue instanceof String ? ZonedDateTime.parse(expectedValue) : expectedValue
            assert Math.abs(actualTime.toEpochSecond() - expectedTime.toEpochSecond()) < 2
        }
    }

    def delete(Bundle bundle) {
        bundle && bundle.delete()
    }
}
