package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.entity.Good;
import by.pvt.shaurma.core.entity.Order;
import by.pvt.shaurma.core.mapper.ClientMapper;
import by.pvt.shaurma.core.mapper.GoodMapper;
import by.pvt.shaurma.core.mapper.OrderMapper;
import by.pvt.shaurma.core.repository.ClientDao;
import by.pvt.shaurma.core.repository.GoodDao;
import by.pvt.shaurma.core.repository.OrderDao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService implements OrderApi {
    private final GoodDao goodDao;
    private final OrderDao orderDao;
    private final GoodMapper goodMapper;
    private final OrderMapper orderMapper;
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;


    public OrderService(GoodDao goodDao, OrderDao orderDao, GoodMapper goodMapper, OrderMapper orderMapper, ClientDao clientDao, ClientMapper clientMapper) {
        this.goodDao = goodDao;
        this.orderDao = orderDao;
        this.goodMapper = goodMapper;
        this.orderMapper = orderMapper;
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
    }

    @Override
    public OrderResponse createOrder(Long userId) {
        Order order;
//        Optional<Order> findOrder = orderDao.findOrderByUserId(userId).stream().filter(order1 -> order1.getStatus().equals("Не оформлен")).findFirst();
//        if(findOrder.isEmpty()) {
            order = new Order(null, null, 0L, new BigDecimal(0), null, "Не оформлен", "Не оплачено");
            orderDao.addOrder(order);
        return orderMapper.mapToOrderResponse(order);
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
    public OrderResponse deleteProductByOrder(Long productId, Long orderId) {
        orderDao.deleteGoodInOrder(productId, orderId);
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(orderId));
    }

    @Override
    public OrderResponse addProductByOrder(Long productId, Long orderId) {
        orderDao.addGoodInOrder(productId, orderId);
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(orderId));
    }

    @Override
    public OrderResponse changeStatus(Long orderId, Long goodId, Long count) {
        Order order = orderDao.getOrderById(orderId);
        Good good = goodDao.getGoodById(goodId);
        order.setCount(count);
        order.setCost(good.getPrice().multiply(BigDecimal.valueOf(count)));
        orderDao.update(order);
        return orderMapper.mapToOrderResponse(order);
    }

    public OrderResponse updateOrderToClient(Long userId, Long orderId) {
        Client client = clientDao.getClientById(userId);
        Order order = orderDao.getOrderById(orderId);
        order.setUserId(client);
        orderDao.update(order);
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(orderId));
    }


}
