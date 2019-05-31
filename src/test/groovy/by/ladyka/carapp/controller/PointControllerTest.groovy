package by.ladyka.carapp.controller;

import by.ladyka.carapp.BaseWebTest
import by.ladyka.carapp.data.PointData
import by.ladyka.carapp.dto.PointDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

class PointControllerTest extends BaseWebTest {

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    PointData points

    def 'get by id'() {
        def bundle = points.create()
        when:
        def result = restTemplate.getForEntity('/api/point/{id}', PointDto, [
                id : bundle.entity.id
        ])
        then:
        result.body.id == bundle.entity.id
        result.body.latitude == bundle.entity.latitude
        result.body.longitude == bundle.entity.longitude
        result.body.vin == bundle.entity.vin

        cleanup:
        delete bundle
    }

    def 'create'() {

    }

    def 'update'() {

    }
}
