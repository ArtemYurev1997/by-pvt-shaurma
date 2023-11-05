package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.BasketApi;
import by.pvt.shaurma.api.dto.BasketBurgerDto;
import by.pvt.shaurma.api.dto.BasketDrinkDto;
import by.pvt.shaurma.api.dto.BasketDto;
import by.pvt.shaurma.api.dto.BasketShawarmaDto;
import by.pvt.shaurma.core.entity.*;
import by.pvt.shaurma.core.mapper.BasketMapper;
import by.pvt.shaurma.core.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketService implements BasketApi {
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

    public BasketShawarmaDto createBasketWithShawarma(Long orderId, Long shawarmaId, Long count) {
        Order order = orderDao.getOrderById(orderId);
        Shawarma shawarma = shawarmaDao.getShawarmaById(shawarmaId);
        BasketShawarma basket = new BasketShawarma(order, shawarma, count);
        basket.setCost(shawarma.getPrice().multiply(BigDecimal.valueOf(count)));
        basketShawarmaDao.add(basket);
        return basketMapper.toBasketShawarmaDto(basket);
    }

    public BasketBurgerDto createBasketWithBurger(Long orderId, Long burgerId, Long count) {
        Order order = orderDao.getOrderById(orderId);
        Burger burger = burgerDao.getBurgerById(burgerId);
        BasketBurger basket = new BasketBurger(order, burger, count);
        basket.setCost(burger.getPrice().multiply(BigDecimal.valueOf(count)));
        basketBurgerDao.add(basket);
        return basketMapper.toBasketBurgerDto(basket);
    }

    public BasketDrinkDto createBasketWithDrink(Long orderId, Long drinkId, Long count) {
        Order order = orderDao.getOrderById(orderId);
        Drink drink = drinkDao.getDrinkById(drinkId);
        BasketDrink basket = new BasketDrink(order, drink, count);
        basket.setCost(drink.getPrice().multiply(BigDecimal.valueOf(count)));
        basketDrinkDao.add(basket);
        return basketMapper.toBasketDrinkDto(basket);
    }

    public List<BasketShawarmaDto> deleteBasketWithShawarma(Long orderId, Long shawarmaId) {
        basketShawarmaDao.delete(orderId, shawarmaId);
        return basketShawarmaDao.getAllBaskets().stream().map(basketMapper::toBasketShawarmaDto).collect(Collectors.toList());
    }

    public List<BasketBurgerDto> deleteBasketWithBurger(Long orderId, Long burgerId) {
        basketBurgerDao.delete(orderId, burgerId);
        return basketBurgerDao.getAllBaskets().stream().map(basketMapper::toBasketBurgerDto).collect(Collectors.toList());
    }

    public List<BasketDrinkDto> deleteBasketWithDrink(Long orderId, Long drinkId) {
        basketDrinkDao.delete(orderId, drinkId);
        return basketDrinkDao.getAllBaskets().stream().map(basketMapper::toBasketDrinkDto).collect(Collectors.toList());
    }

    public BigDecimal totalPriceAllBasketsForOrder(Long orderId) {
        BigDecimal cost;
        if (basketDrinkDao.totalPriceDrink(orderId) == null) {
            cost = basketShawarmaDao.totalPriceShawarma(orderId).add(basketBurgerDao.totalPriceBurger(orderId));
        }
        else if(basketBurgerDao.totalPriceBurger(orderId) == null) {
            cost = basketShawarmaDao.totalPriceShawarma(orderId).add(basketDrinkDao.totalPriceDrink(orderId));
        }
        else if(basketShawarmaDao.totalPriceShawarma(orderId) == null) {
            cost = basketBurgerDao.totalPriceBurger(orderId).add(basketDrinkDao.totalPriceDrink(orderId));
        }
        else if(basketBurgerDao.totalPriceBurger(orderId) == null && basketDrinkDao.totalPriceDrink(orderId) == null) {
            cost = basketShawarmaDao.totalPriceShawarma(orderId);
        }
        else if(basketBurgerDao.totalPriceBurger(orderId) == null && basketShawarmaDao.totalPriceShawarma(orderId) == null) {
            cost = basketDrinkDao.totalPriceDrink(orderId);
        }
        else if(basketShawarmaDao.totalPriceShawarma(orderId) == null && basketDrinkDao.totalPriceDrink(orderId) == null) {
            cost = basketBurgerDao.totalPriceBurger(orderId);
        }
        else {
            cost = basketShawarmaDao.totalPriceShawarma(orderId).add(basketBurgerDao.totalPriceBurger(orderId)).add(basketDrinkDao.totalPriceDrink(orderId));
        }
        Order order = orderDao.getOrderById(orderId);
        order.setCost(cost);
        orderDao.update(order);
        return cost;
    }

    public Long totalCountAllBasketsForOrder(Long orderId) {
        Long count = null;
        if(basketDrinkDao.totalCountDrink(orderId) == null) {
            count = basketBurgerDao.totalCountBurger(orderId) + basketShawarmaDao.totalCountShawarma(orderId);
        }
        Order order = orderDao.getOrderById(orderId);
        order.setCount(count);
        orderDao.update(order);
        return count;
    }

    public Shawarma updateShawarmaToPrice(Long shawarmaId, BigDecimal price) {
        Shawarma shawarma = shawarmaDao.getShawarmaById(shawarmaId);
        shawarma.setPrice(price);
        shawarmaDao.update(shawarma);
        return shawarma;
    }

    public Burger updateBurgerToPrice(Long burgerId, BigDecimal price) {
        Burger burger = burgerDao.getBurgerById(burgerId);
        burger.setPrice(price);
        burgerDao.update(burger);
        return burger;
    }
}
