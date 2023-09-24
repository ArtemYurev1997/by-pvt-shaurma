package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.api.dto.OrderRequest;
import by.pvt.shaurma.core.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getAllOrders();

    Order getOrderById(Long id);

    void delete(Long id);

    void addOrder(Order order);

    void update(Long id, OrderRequest orderRequest);

    List<Order> findOrderByUserId(Long userId);
}
