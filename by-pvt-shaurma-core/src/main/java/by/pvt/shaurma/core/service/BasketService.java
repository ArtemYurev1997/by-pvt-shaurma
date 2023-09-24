package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.BasketApi;
import by.pvt.shaurma.api.dto.BasketDto;
import by.pvt.shaurma.api.dto.GoodResponse;
import by.pvt.shaurma.core.entity.Basket;
import by.pvt.shaurma.core.mapper.BasketMapper;
import by.pvt.shaurma.core.mapper.GoodMapper;
import by.pvt.shaurma.core.repository.BasketDao;
import by.pvt.shaurma.core.repository.GoodDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketService implements BasketApi {
    private final GoodDao goodDao;
    private final BasketDao basketDao;
    private final BasketMapper basketMapper;
    private final GoodMapper goodMapper;

    public BasketService(GoodDao goodDao, BasketDao basketDao, BasketMapper basketMapper, GoodMapper goodMapper) {
        this.goodDao = goodDao;
        this.basketDao = basketDao;
        this.basketMapper = basketMapper;
        this.goodMapper = goodMapper;
    }



    @Override
    public BasketDto deleteGoodInBasket(Long goodId) {
        return null;
    }



    @Override
    public BasketDto createBasket(Long orderId, Long goodId, Integer count) {
        Basket basket = new Basket(goodId, orderId, count);
        basketDao.add(basket);
        return basketMapper.mapToBasketDto(basket);
    }
}
