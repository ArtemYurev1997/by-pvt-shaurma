package by.pvt.shaurma.core.repository.spring;


import by.pvt.shaurma.core.entity.BasketShawarma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketShawarmaRepository extends JpaRepository<BasketShawarma, Long> {

    @Query("select s from BasketShawarma s where s.id.orderId=:orderId and s.id.shawarmaId=:shawarmaId")
    void delete(@Param("orderId") Long orderId, @Param("shawarmaId") Long shawarmaId);
}
