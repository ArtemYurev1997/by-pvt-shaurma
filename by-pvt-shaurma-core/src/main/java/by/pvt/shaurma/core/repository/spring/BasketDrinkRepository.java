package by.pvt.shaurma.core.repository.spring;

import by.pvt.shaurma.core.entity.BasketDrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface BasketDrinkRepository extends JpaRepository<BasketDrink, Long> {
    @Query("select d from BasketDrink d where d.id.orderId=:orderId and d.id.drinkId=:drinkId")
    void delete(@Param("orderId") Long orderId, @Param("drinkId") Long drinkId);

    @Query("select sum(d.cost) from BasketDrink d where d.id.orderId=:orderId")
    BigDecimal totalPriceDrink(@Param("orderId") Long orderId);

    @Query("select sum(d.count) from BasketDrink d where d.id.orderId=:orderId")
    Long totalCountDrink(@Param("orderId") Long orderId);


}
