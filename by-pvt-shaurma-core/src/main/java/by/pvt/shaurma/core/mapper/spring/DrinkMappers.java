package by.pvt.shaurma.core.mapper.spring;

import by.pvt.shaurma.api.dto.DrinkDto;
import by.pvt.shaurma.core.entity.Drink;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DrinkMappers {
    DrinkDto toDto(Drink drink);
}
