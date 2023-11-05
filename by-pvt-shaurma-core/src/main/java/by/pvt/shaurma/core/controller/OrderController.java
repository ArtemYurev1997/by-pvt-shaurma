package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.BasketApi;
import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderApi orderApi;
    private final BasketApi basketApi;

    public OrderController(@Qualifier("orderServiceApi") OrderApi orderApi, @Qualifier("basketServiceApi") BasketApi basketApi) {
        this.orderApi = orderApi;
        this.basketApi = basketApi;
    }

    @GetMapping("/getAll")
    List<OrderResponse> getAll() {
        return orderApi.getAll();
    }

    @PostMapping("/add")
    public OrderResponse add(@RequestBody OrderRequest orderRequest) {
        return orderApi.save(orderRequest);
    }

    @PostMapping("/create/newOrder")
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderApi.createOrder(orderRequest);
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable("id") Long id) {
        return orderApi.findById(id);
    }

    @PostMapping("/addClientToOrder")
    public OrderResponse updateOrderToClient(@RequestBody OrderResponse orderResponse) {
        Long userId = orderResponse.getUserId().getId();
        Long orderId = orderResponse.getId();
        return orderApi.updateOrderToClient(userId, orderId);
    }

    @PostMapping("/payment")
    public OrderResponse payment(BigDecimal sum, Long orderId, Long userId) {
        return orderApi.payment(sum, orderId, userId);
    }

    @GetMapping("/getOrdersByUserId")
    List<OrderResponse> getOrdersByUserId(Long userId) {
        return orderApi.getOrdersByUserId(userId);
    }

    @PostMapping("/changeStatus")
    public OrderResponse changeStatus(Long orderId) {
        return orderApi.changeStatus(orderId);
    }

    @GetMapping("/searchForShawarmaName")
    List<ShawarmaDto> getShawarmaDtoByIngridient(String name) {
        return orderApi.getShawarmaDtoByIngridient(name);
    }

    @GetMapping("/searchForBurgerName")
    List<BurgerDto> getBurgersDtoByIngridient(String name) {
        return orderApi.getBurgersDtoByIngridient(name);
    }

    @PostMapping("/createComment")
    CommentResponse createCommentByClient(Long clientId, CommentRequest commentRequest) {
        return orderApi.createCommentByClient(clientId, commentRequest);
    }

    @PostMapping("/createShawarma")
    ShawarmaDto createShawarma(Long id, Long start, Long end, String type, Long code) {
        return orderApi.createShawarma(id, start, end, type, code);
    }
}
