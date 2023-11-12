package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.*;
import by.pvt.shaurma.core.entity.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderApi orderApi;

    public OrderController(@Qualifier("orderServiceApi") OrderApi orderApi) {
        this.orderApi = orderApi;
    }

    @GetMapping("/getAll")
    public List<OrderResponse> getAll() {
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
    public OrderResponse updateOrderToClient(@RequestBody OrderRequest orderRequest) {
        return orderApi.updateOrderToClient(orderRequest);
    }

    @PostMapping("/addCostAndCount")
    public OrderResponse addCostAndCountToOrder(@RequestBody  OrderRequest orderRequest) {
        return orderApi.addCostAndCountToOrder(orderRequest);
    }

    @PostMapping("/checkout")
    public OrderResponse checkout(@RequestBody OrderRequest orderRequest) {
        return orderApi.checkOut(orderRequest);
    }

    @PostMapping("/payment")
    public OrderResponse payment(@RequestBody OrderRequest orderRequest) {
        return orderApi.payment(orderRequest);
    }

    @GetMapping("/getOrdersByUserId/{id}")
    public List<OrderResponse> getOrdersByUserId(@PathVariable("id") Long userId) {
        return orderApi.getOrdersByUserId(userId);
    }

    @PostMapping("/changeStatus")
    public OrderResponse changeStatus(@RequestBody OrderRequest orderRequest) {
        return orderApi.changeStatus(orderRequest);
    }

    @PostMapping("/createComment")
    public CommentResponse createCommentByClient( @RequestBody CommentRequest commentRequest) {
        return orderApi.createCommentByClient(commentRequest);
    }

    @GetMapping("/page")
    public Page<OrderResponse> getOrderResponses(@RequestParam int page, @RequestParam int size) {
        return orderApi.getOrdersPages(page, size);
    }
}
