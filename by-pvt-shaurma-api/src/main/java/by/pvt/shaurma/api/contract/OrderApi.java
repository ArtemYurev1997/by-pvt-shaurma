package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.api.dto.UserRequest;

import java.util.List;

public interface OrderApi {
    OrderResponse createOrder(Long userId);

    OrderResponse checkOut();

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse getOrderByOrderId(Long orderId);

    OrderResponse deleteProductByOrder(Long productId, Long orderId);

    OrderResponse addProductByOrder(Long productId, Long orderId);

    OrderResponse changeStatus(Long orderId, Long goodId, Long count);
}
