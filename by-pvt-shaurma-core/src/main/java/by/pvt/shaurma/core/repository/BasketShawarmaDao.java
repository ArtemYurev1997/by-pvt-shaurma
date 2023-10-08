package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.BasketShawarma;

import java.util.List;

public interface BasketShawarmaDao {
    List<BasketShawarma> getAllBaskets();

    void add(BasketShawarma basket);

    void updateBasket(BasketShawarma basket);

    BasketShawarma getBasketById(Long id);

    void delete(Long id);
}
