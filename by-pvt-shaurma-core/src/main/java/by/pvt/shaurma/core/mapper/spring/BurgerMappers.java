package by.pvt.shaurma.core.mapper.spring;

import by.pvt.shaurma.api.dto.BurgerDto;
import by.pvt.shaurma.core.entity.Burger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BurgerMappers {
    BurgerDto toDto(Burger burger);
}
