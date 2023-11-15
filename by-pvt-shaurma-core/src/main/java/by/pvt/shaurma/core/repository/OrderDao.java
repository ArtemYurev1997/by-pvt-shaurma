package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.api.dto.OrderRequest;
import by.pvt.shaurma.core.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderDao {
    List<Order> getAllOrders();

    Order getOrderById(Long id);

    void delete(Long id);

    Order addOrder(Order order);

    void update(Order order);

    List<Order> findOrderByUserId(Long userId);

    List<Order> findAllOrdersWhereCostGreaterThan(BigDecimal cost);

    BigDecimal getSumCostOfGoodsInOrder();

    List<Order> findAllOrdersByRangeDate(LocalDate min, LocalDate max);

    List<Order> findNameAndLoginClients(String name, String login);
}
