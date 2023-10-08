package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.BasketBurger;

import java.util.List;

public interface BasketBurgerDao {
    List<BasketBurger> getAllBaskets();

    void add(BasketBurger basket);

    void updateBasket(BasketBurger basket);

    BasketBurger getBasketById(Long id);

    void delete(Long id);
}
