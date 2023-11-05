package by.pvt.shaurma.core.repository.spring;

import by.pvt.shaurma.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.userId=:userId")
    List<Order> getOrdersByUserId(@Param("userId") Long userId);

    @Query("select o.cost from Order o where o.id=:orderId and o.userId.id=:userId")
    BigDecimal getCostOfOrder(@Param("orderId") Long orderId, @Param("userId") Long userId);
}
