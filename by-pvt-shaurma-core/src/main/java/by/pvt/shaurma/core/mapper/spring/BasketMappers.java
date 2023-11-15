package by.pvt.shaurma.core.mapper.spring;

import by.pvt.shaurma.api.dto.*;
import by.pvt.shaurma.core.entity.BasketBurger;
import by.pvt.shaurma.core.entity.BasketDrink;
import by.pvt.shaurma.core.entity.BasketShawarma;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasketMappers {
    BasketShawarmaDto toBasketShawarmaDto(BasketShawarma basketShawarma);
    BasketBurgerDto toBasketBurgerDto(BasketBurger basketBurger);
    BasketDrinkDto toBasketDrinkDto(BasketDrink basketDrink);
}
