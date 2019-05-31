package by.ladyka.carapp.service;

import by.ladyka.carapp.domain.PointEntity;
import by.ladyka.carapp.dto.PointCreateRequestDto;
import by.ladyka.carapp.dto.PointDto;
import by.ladyka.carapp.mapper.PointMapper;
import by.ladyka.carapp.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PointService {

    @Autowired
    private PointRepository repository;

    @Autowired
    private PointMapper mapper;

    public PointDto create(PointCreateRequestDto pointDto) {
        PointEntity entity = mapper.toEntity(pointDto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public PointDto getById(Long id) {
        PointEntity entity = getPointEntity(id);
        return mapper.toDto(entity);
    }

    private PointEntity getPointEntity(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public PointDto update(PointDto pointDto) {
        PointEntity entity = getPointEntity(pointDto.getId());
        entity = mapper.toEntity(entity, pointDto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }
}
