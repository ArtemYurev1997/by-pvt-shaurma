package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.api.dto.UserRequest;

import java.util.List;

public interface OrderApi {
    OrderResponse createOrder();

    OrderResponse updateOrderToClient(Long userId, Long orderId);

    OrderResponse checkOut();

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse getOrderByOrderId(Long orderId);

    OrderResponse deleteShawarmaByOrder(Long shawarmaId, Long orderId);

    OrderResponse addShawarmaByOrder(Long shawarmaId, Long orderId);

    OrderResponse deleteBurgerByOrder(Long burgerId, Long orderId);

    OrderResponse addBurgerByOrder(Long burgerId, Long orderId);

    OrderResponse changeStatus(Long orderId);
}
