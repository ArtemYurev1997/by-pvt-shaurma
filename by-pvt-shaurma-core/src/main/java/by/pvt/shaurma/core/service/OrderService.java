package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.BasketApi;
import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.api.dto.UserRequest;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.entity.Order;
import by.pvt.shaurma.core.entity.User;
import by.pvt.shaurma.core.mapper.BasketMapper;
import by.pvt.shaurma.core.mapper.GoodMapper;
import by.pvt.shaurma.core.mapper.OrderMapper;
import by.pvt.shaurma.core.mapper.UserMapper;
import by.pvt.shaurma.core.repository.GoodDao;
import by.pvt.shaurma.core.repository.OrderDao;
import by.pvt.shaurma.core.repository.UserDao;

import java.util.List;
import java.util.Optional;

public class OrderService implements OrderApi {
    private final GoodDao goodDao;
    private final BasketApi basketApi;
    private final OrderDao orderDao;
    private final GoodMapper goodMapper;
    private final OrderMapper orderMapper;
    private final BasketMapper basketMapper;
    private final UserDao userDao;
    private final UserMapper userMapper;

    public OrderService(GoodDao goodDao, BasketApi basketApi, OrderDao orderDao, GoodMapper goodMapper, OrderMapper orderMapper, BasketMapper basketMapper, UserDao userDao, UserMapper userMapper) {
        this.goodDao = goodDao;
        this.basketApi = basketApi;
        this.orderDao = orderDao;
        this.goodMapper = goodMapper;
        this.orderMapper = orderMapper;
        this.basketMapper = basketMapper;
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public OrderResponse createOrder(Long userId) {
        User user = userDao.getClientById(userId);
        Order order;
        Optional<Order> findOrder =  orderDao.findOrderByUserId(userId).stream().filter(order1 -> order1.getStatus().equals("Не оформлен")).findFirst();
        if(findOrder.isEmpty()) {
            order = new Order(null, user, null, null, "Не оформлен", "Не оплачено");
            orderDao.addOrder(order);
        }
        else {
            order = findOrder.get();
        }
        return orderMapper.mapToOrderResponse(order);
    }

    @Override
    public OrderResponse checkOut() {
        return null;
    }

    @Override
    public List<OrderResponse> getOrdersByUserId() {
        return null;
    }

    @Override
    public OrderResponse getOrderByOrderId(Long orderId) {
        return null;
    }

    @Override
    public OrderResponse deleteProductByOrder(Long productId, Long orderId) {
        return null;
    }

    @Override
    public OrderResponse addProductByOrder(Long productId, Long orderId) {
        return null;
    }

    @Override
    public OrderResponse changeStatus(Long orderId, Integer count) {
        return null;
    }
}
