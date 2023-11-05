package by.pvt.shaurma.core.repository.spring;

import by.pvt.shaurma.core.entity.BasketBurger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketBurgerRepository extends JpaRepository<BasketBurger, Long> {
    @Query("select b from BasketBurger b where b.id.orderId=:orderId and b.id.burgerId=:burgerId")
    void delete(@Param("orderId") Long orderId, @Param("burgerId") Long burgerId);
}
