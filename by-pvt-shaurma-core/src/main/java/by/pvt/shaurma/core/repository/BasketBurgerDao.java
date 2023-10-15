package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.BasketBurger;

import java.math.BigDecimal;
import java.util.List;

public interface BasketBurgerDao {
    List<BasketBurger> getAllBaskets();

    void add(BasketBurger basket);

    void delete(Long orderId, Long burgerId);

    BigDecimal totalPriceBurger(Long orderId);

    Long totalCountBurger(Long orderId);
}
