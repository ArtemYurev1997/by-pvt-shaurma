package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.BasketDrink;

import java.math.BigDecimal;
import java.util.List;

public interface BasketDrinkDao {
    List<BasketDrink> getAllBaskets();

    void add(BasketDrink basket);

    void delete(Long orderId, Long drinkId);

    BigDecimal totalPriceDrink(Long orderId);

    Long totalCountDrink(Long orderId);
}
