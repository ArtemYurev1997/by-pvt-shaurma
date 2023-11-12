package by.pvt.shaurma.core.repository.spring;


import by.pvt.shaurma.core.entity.BasketShawarma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface BasketShawarmaRepository extends JpaRepository<BasketShawarma, Long> {

    @Query("select s from BasketShawarma s where s.id.orderId=:orderId and s.id.shawarmaId=:shawarmaId")
    void delete(@Param("orderId") Long orderId, @Param("shawarmaId") Long shawarmaId);

    @Query("select sum(d.cost) from BasketShawarma d where d.id.orderId=:orderId")
    BigDecimal totalPriceShawarma(@Param("orderId") Long orderId);

    @Query("select sum(d.count) from BasketShawarma d where d.id.orderId=:orderId")
    Long totalCountShawarma(@Param("orderId") Long orderId);
}
