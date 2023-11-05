package by.pvt.shaurma.core.repository.spring;

import by.pvt.shaurma.core.entity.BasketDrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketDrinkRepository extends JpaRepository<BasketDrink, Long> {
    @Query("select d from BasketDrink d where d.id.orderId=:orderId and d.id.drinkId=:drinkId")
    void delete(@Param("orderId") Long orderId, @Param("drinkId") Long drinkId);
}
