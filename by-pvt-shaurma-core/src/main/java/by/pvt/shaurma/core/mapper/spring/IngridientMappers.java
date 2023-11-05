package by.pvt.shaurma.core.mapper.spring;

import by.pvt.shaurma.api.dto.IngridientDto;
import by.pvt.shaurma.core.entity.Ingridient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngridientMappers {
    IngridientDto toDto(Ingridient ingridient);
}
