package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.OrderRequest;
import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.core.entity.Order;

public class OrderMapper {
    public OrderResponse mapToOrderResponse(Order order){
        OrderResponse dto = new OrderResponse();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setStatus(order.getStatus());
        dto.setPayment(order.getPayment());
        return dto;
    }

    public Order mapToOrderEntity(OrderRequest orderRequest){
        Order dto = new Order();
        dto.setId(orderRequest.getId());
        dto.setDate(orderRequest.getDate());
        dto.setStatus(orderRequest.getStatus());
        dto.setPayment(orderRequest.getPayment());
        return dto;
    }
}
