package by.ladyka.carapp


import org.junit.experimental.categories.Category
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@ComponentScan(basePackages = ['com.lifetouch.oms.data', 'com.lifetouch.oms.service', 'com.lifetouch.oms.mapper', 'com.lifetouch.oms.mock', 'com.lifetouch.oms.rule', 'com.lifetouch.oms.util', 'com.lifetouch.oms.logging', 'com.lifetouch.oms.audit'])
@AutoConfigureJson
@Category(DataTest.class)
abstract class BaseDataJpaTest extends Specification {
}
