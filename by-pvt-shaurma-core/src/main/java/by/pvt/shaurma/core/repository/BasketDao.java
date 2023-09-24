package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.Basket;

import java.util.List;

public interface BasketDao {
    List<Basket> getAllBaskets();

    void add(Basket basket);

    void updateBasket(Basket basket);

    Basket getBasketById(Long id);

    void delete(Long id);
}
