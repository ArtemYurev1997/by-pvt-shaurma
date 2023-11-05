package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.OrderRequest;
import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.core.entity.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    BasketMapper basketMapper = new BasketMapper();
    ClientMapper clientMapper = new ClientMapper();

    public OrderResponse mapToOrderResponse(Order order){
        OrderResponse dto = new OrderResponse();
        dto.setId(order.getId());
        dto.setUserId(clientMapper.mapToClientDto(order.getUserId())); //???
        dto.setCount(order.getCount());
        dto.setCost(order.getCost());
        dto.setDate(order.getDate());
        dto.setAddress(order.getAddress());
        dto.setTelephone(order.getTelephone());
        dto.setComment(order.getComment());
        dto.setStatus(order.getStatus());
        dto.setPayment(order.getPayment());
        dto.setShawarmaList(order.getShawarmaList().stream().map(basketMapper::toBasketShawarmaDto).collect(Collectors.toList()));
        dto.setBurgerList(order.getBurgerList().stream().map(basketMapper::toBasketBurgerDto).collect(Collectors.toList()));
        dto.setDrinkList(order.getDrinkList().stream().map(basketMapper::toBasketDrinkDto).collect(Collectors.toList()));
        return dto;
    }

    public Order toEntity(OrderRequest orderRequest) {
        Order dto = new Order();
        dto.setUserId(clientMapper.mapToClientEntity(orderRequest.getUserId()));
        dto.setCount(orderRequest.getCount());
        dto.setCost(orderRequest.getCost());
        dto.setDate(orderRequest.getDate());
        dto.setAddress(orderRequest.getAddress());
        dto.setTelephone(orderRequest.getTelephone());
        dto.setComment(orderRequest.getComment());
        dto.setStatus(orderRequest.getStatus());
        dto.setPayment(orderRequest.getPayment());
        return dto;
    }
}
