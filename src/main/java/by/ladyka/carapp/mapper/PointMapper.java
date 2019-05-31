package by.ladyka.carapp.mapper;

import by.ladyka.carapp.domain.PointEntity;
import by.ladyka.carapp.dto.PointCreateRequestDto;
import by.ladyka.carapp.dto.PointDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PointMapper {
    PointDto toDto(PointEntity entity);
    @Mapping(target = "id", ignore = true)
    PointEntity toEntity(PointCreateRequestDto dto);
    PointEntity toEntity(@MappingTarget PointEntity entity, PointDto dto);

}
