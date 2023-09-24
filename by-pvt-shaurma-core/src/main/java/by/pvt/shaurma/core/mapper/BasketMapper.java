package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.BasketDto;
import by.pvt.shaurma.api.dto.GoodResponse;
import by.pvt.shaurma.core.entity.Basket;
import by.pvt.shaurma.core.entity.Good;

public class BasketMapper {
    public BasketDto mapToBasketDto(Basket basket){
        BasketDto dto = new BasketDto();
        dto.setId(basket.getId());
        dto.setGoodId(basket.getGoodId());
        dto.setOrderId(basket.getOrderId());
        dto.setCount(basket.getCount());
        return dto;
    }

    public Basket mapToBasketEntity(BasketDto basketDto){
        Basket basket = new Basket();
        basket.setId(basketDto.getId());
        basket.setGoodId(basketDto.getGoodId());
        basket.setOrderId(basketDto.getOrderId());
        basket.setCount(basketDto.getCount());
        return basket;
    }

}
