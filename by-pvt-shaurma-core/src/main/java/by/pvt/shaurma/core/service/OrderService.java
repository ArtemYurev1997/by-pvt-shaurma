package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.entity.Order;
import by.pvt.shaurma.core.mapper.ClientMapper;
import by.pvt.shaurma.core.mapper.OrderMapper;
import by.pvt.shaurma.core.repository.ClientDao;
import by.pvt.shaurma.core.repository.OrderDao;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements OrderApi {
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;


    public OrderService( OrderDao orderDao, OrderMapper orderMapper, ClientDao clientDao, ClientMapper clientMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
    }

    @Override
    public OrderResponse createOrder() {
        Order order;
        order = new Order(null, null, 0L, new BigDecimal(0), null, "Не оформлен", "Не оплачено", null);
        orderDao.addOrder(order);
        return orderMapper.mapToOrderResponse(order);
    }

    public OrderResponse updateOrderToClient(Long userId, Long orderId) {
        Client client = clientDao.getClientById(userId);
        Order order = orderDao.getOrderById(orderId);
        order.setUserId(client);
        orderDao.update(order);
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(orderId));
    }

    @Override
    public OrderResponse checkOut() {
        return null;
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        return orderDao.findOrderByUserId(userId).stream().map(orderMapper::mapToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderByOrderId(Long orderId) {
        return null;
    }

    @Override
    public OrderResponse deleteShawarmaByOrder(Long shawarmaId, Long orderId) {
        orderDao.deleteShawarmaInOrder(shawarmaId, orderId);
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(orderId));
    }

    @Override
    public OrderResponse addShawarmaByOrder(Long shawarmaId, Long orderId) {
        orderDao.addShawarmaInOrder(shawarmaId, orderId);
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(orderId));
    }

    @Override
    public OrderResponse deleteBurgerByOrder(Long burgerId, Long orderId) {
        orderDao.deleteBurgerInOrder(burgerId, orderId);
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(orderId));
    }

    @Override
    public OrderResponse addBurgerByOrder(Long burgerId, Long orderId) {
        orderDao.addBurgerInOrder(burgerId, orderId);
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(orderId));
    }

    @Override
    public OrderResponse changeStatus(Long orderId) {
        Order order = orderDao.getOrderById(orderId);
        order.setStatus("Оформлен");
        orderDao.update(order);
        return orderMapper.mapToOrderResponse(order);
    }




}
