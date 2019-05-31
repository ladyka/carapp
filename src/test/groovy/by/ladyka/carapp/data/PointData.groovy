package by.ladyka.carapp.data

import by.ladyka.carapp.data.Bundle
import by.ladyka.carapp.domain.PointEntity
import by.ladyka.carapp.repository.PointRepository
import org.springframework.beans.factory.annotation.Autowired

class PointData {

    @Autowired
    PointRepository repository;

    Bundle<PointEntity> create(Map properties = [:]) {
        def bundle = new Bundle(repository)

        bundle.entity = repository.save(new PointEntity(
                id: properties.id ?: null,
                latitude: properties.latitude ?: 50,
                longitude: properties.longitude ?: 30,
                vin: properties.vin ?: "12345678901234567",
        ))

        bundle
    }
}
