package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.api.dto.ShawarmaDto;
import by.pvt.shaurma.api.dto.UserRequest;

import java.util.List;

public interface OrderApi {
    OrderResponse createOrder();

    OrderResponse updateOrderToClient(Long userId, Long orderId);

    OrderResponse checkOut(Long orderId);

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse changeStatus(Long orderId);

    OrderResponse getOrderById(Long id);

    List<ShawarmaDto>  getShawarmaDtoForIngridient(String name);
}
