package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.dto.BasketDto;
import by.pvt.shaurma.core.entity.*;
import by.pvt.shaurma.core.mapper.BasketMapper;
import by.pvt.shaurma.core.repository.*;

import java.math.BigDecimal;

public class BasketService {
    private final BasketShawarmaDao basketShawarmaDao;
    private final BasketBurgerDao basketBurgerDao;
    private final BasketDrinkDao basketDrinkDao;
    private final OrderDao orderDao;
    private final ShawarmaDao shawarmaDao;
    private final BurgerDao burgerDao;
    private final DrinkDao drinkDao;
    private final BasketMapper basketMapper;

    public BasketService(BasketShawarmaDao basketShawarmaDao, BasketBurgerDao basketBurgerDao, BasketDrinkDao basketDrinkDao, OrderDao orderDao, ShawarmaDao shawarmaDao, BurgerDao burgerDao, DrinkDao drinkDao, BasketMapper basketMapper) {
        this.basketShawarmaDao = basketShawarmaDao;
        this.basketBurgerDao = basketBurgerDao;
        this.basketDrinkDao = basketDrinkDao;
        this.orderDao = orderDao;
        this.shawarmaDao = shawarmaDao;
        this.burgerDao = burgerDao;
        this.drinkDao = drinkDao;
        this.basketMapper = basketMapper;
    }


    public BasketDto createBasketWithShawarma(Long orderId, Long shawarmaId, Long count) {
        Order order = orderDao.getOrderById(orderId);
        Shawarma shawarma = shawarmaDao.getShawarmaById(shawarmaId);
        BasketShawarma basket = new BasketShawarma(order, shawarma, count);
        basket.setCost(shawarma.getPrice().multiply(BigDecimal.valueOf(count)));
        basketShawarmaDao.add(basket);
        return basketMapper.mapToBasketShawarmaDto(basket);
    }

    public BasketDto createBasketWithBurger(Long orderId, Long burgerId, Long count) {
        Order order = orderDao.getOrderById(orderId);
        Burger burger = burgerDao.getBurgerById(burgerId);
        BasketBurger basket = new BasketBurger(order, burger, count);
        basket.setCost(burger.getPrice().multiply(BigDecimal.valueOf(count)));
        basketBurgerDao.add(basket);
        return basketMapper.mapToBasketBurgerDto(basket);
    }

    public BasketDto createBasketWithDrink(Long orderId, Long drinkId, Long count) {
        Order order = orderDao.getOrderById(orderId);
        Drink drink = drinkDao.getDrinkById(drinkId);
        BasketDrink basket = new BasketDrink(order, drink, count);
        basket.setCost(drink.getPrice().multiply(BigDecimal.valueOf(count)));
        basketDrinkDao.add(basket);
        return basketMapper.mapToBasketDrinkDto(basket);
    }
}
