package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.BasketDrink;

import java.util.List;

public interface BasketDrinkDao {
    List<BasketDrink> getAllBaskets();

    void add(BasketDrink basket);

    void updateBasket(BasketDrink basket);

    BasketDrink getBasketById(Long id);

    void delete(Long id);
}
