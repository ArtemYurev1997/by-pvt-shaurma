package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.BasketBurgerDto;
import by.pvt.shaurma.api.dto.BasketDrinkDto;
import by.pvt.shaurma.api.dto.BasketDto;
import by.pvt.shaurma.api.dto.BasketShawarmaDto;
import by.pvt.shaurma.core.entity.BasketBurger;
import by.pvt.shaurma.core.entity.BasketDrink;
import by.pvt.shaurma.core.entity.BasketShawarma;

public class BasketMapper {
    ShawarmaMapper shawarmaMapper = new ShawarmaMapper();
    BurgerMapper burgerMapper = new BurgerMapper();
    DrinkMapper drinkMapper = new DrinkMapper();


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

    public BasketShawarmaDto toBasketShawarmaDto(BasketShawarma basketShawarma) {
        BasketShawarmaDto dto = new BasketShawarmaDto();
        dto.setShawarma(shawarmaMapper.toShawarmaDto(basketShawarma.getShawarma()));
        dto.setCost(basketShawarma.getCost());
        dto.setCount(basketShawarma.getCount());
        return dto;
    }

    public BasketBurgerDto toBasketBurgerDto(BasketBurger basketBurger) {
        BasketBurgerDto dto = new BasketBurgerDto();
        dto.setBurger(burgerMapper.toBurgerDto(basketBurger.getBurger()));
        dto.setCost(basketBurger.getCost());
        dto.setCount(basketBurger.getCount());
        return dto;
    }

    public BasketDrinkDto toBasketDrinkDto(BasketDrink basketDrink) {
        BasketDrinkDto dto = new BasketDrinkDto();
        dto.setDrink(drinkMapper.toDrinkDto(basketDrink.getDrink()));
        dto.setCost(basketDrink.getCost());
        dto.setCount(basketDrink.getCount());
        return dto;
    }


}
