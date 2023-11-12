package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.*;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface OrderApi {
    OrderResponse save(OrderRequest orderRequest);

    void delete(Long id);

    OrderResponse findById(Long id);

    List<OrderResponse> getAll();

    Page<OrderResponse> getOrdersPages(int page, int size);

    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse updateOrderToClient(OrderRequest orderRequest);

    OrderResponse checkOut(OrderRequest orderRequest);

    OrderResponse payment(OrderRequest orderRequest);

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse changeStatus(OrderRequest orderRequest);

    OrderResponse addCostAndCountToOrder(OrderRequest orderRequest);

    OrderResponse getOrderById(Long id);

    CommentResponse createCommentByClient(CommentRequest commentRequest);


}
