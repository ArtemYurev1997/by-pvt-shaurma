package by.pvt.shaurma.core.mapper.spring;

import by.pvt.shaurma.api.dto.ShawarmaDto;
import by.pvt.shaurma.core.entity.Shawarma;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShawarmaMappers {
    ShawarmaDto toDto(Shawarma shawarma);
}
