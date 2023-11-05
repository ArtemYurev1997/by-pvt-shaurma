package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.*;

import java.math.BigDecimal;
import java.util.List;

public interface OrderApi {
    OrderResponse save(OrderRequest orderRequest);

    void delete(Long id);

    OrderResponse findById(Long id);

    List<OrderResponse> getAll();

    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse updateOrderToClient(Long userId, Long orderId);

    OrderResponse checkOut(Long orderId);

    OrderResponse payment(BigDecimal sum, Long orderId, Long userId);

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse changeStatus(Long orderId);

    OrderResponse getOrderById(Long id);

    List<ShawarmaDto>  getShawarmaDtoByIngridient(String name);

    List<BurgerDto> getBurgersDtoByIngridient(String name);

    CommentResponse createCommentByClient(Long clientId, CommentRequest commentRequest);

    ShawarmaDto createShawarma(Long id, Long start, Long end, String type, Long code);
}
