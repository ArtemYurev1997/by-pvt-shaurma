package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.BasketDto;
import by.pvt.shaurma.core.entity.BasketBurger;
import by.pvt.shaurma.core.entity.BasketDrink;
import by.pvt.shaurma.core.entity.BasketShawarma;

public class BasketMapper {
    public BasketDto mapToBasketShawarmaDto(BasketShawarma basketShawarma){
        BasketDto dto = new BasketDto();
        dto.setId();
        dto.setOrderId(basketShawarma.getOrder().getId());
        dto.setShawarmaId(basketShawarma.getShawarma().getId());
        dto.setCount(basketShawarma.getCount());
        dto.setCost(basketShawarma.getCost());
        return dto;
    }

    public BasketDto mapToBasketBurgerDto(BasketBurger basketBurger) {
        BasketDto dto = new BasketDto();
        dto.setId();
        dto.setBurgerId(basketBurger.getBurger().getId());
        dto.setCount(basketBurger.getCount());
        dto.setCost(basketBurger.getCost());
        return dto;
    }

    public BasketDto mapToBasketDrinkDto(BasketDrink basketDrink) {
        BasketDto dto = new BasketDto();
        dto.setId();
        dto.setBurgerId(basketDrink.getDrink().getId());
        dto.setCount(basketDrink.getCount());
        dto.setCost(basketDrink.getCost());
        return dto;
    }
}
