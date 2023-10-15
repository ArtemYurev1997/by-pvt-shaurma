package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.BasketShawarma;

import java.math.BigDecimal;
import java.util.List;

public interface BasketShawarmaDao {
    List<BasketShawarma> getAllBaskets();

    void add(BasketShawarma basket);

    void delete(Long orderId, Long shawarmaId);

    BigDecimal totalPriceShawarma(Long orderId);

    Long totalCountShawarma(Long orderId);
}
