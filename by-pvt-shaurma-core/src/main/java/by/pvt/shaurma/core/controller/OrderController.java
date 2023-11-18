package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.BasketApi;
import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.*;
import by.pvt.shaurma.core.entity.BasketBurger;
import by.pvt.shaurma.core.entity.BasketDrink;
import by.pvt.shaurma.core.entity.BasketShawarma;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
    public List<OrderResponse> getAll() {
        return orderApi.getAll();
    }

    @PostMapping("/add")
    public OrderResponse add(@RequestBody OrderRequest orderRequest) {
        return orderApi.save(orderRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderApi.delete(id);
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
    public OrderResponse addCostAndCountToOrder(@RequestBody OrderRequest orderRequest) {
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

    @PostMapping("/addShawarmaToBasket")
    public OrderResponse addShawarmaToBasket(@RequestBody BasketShawarma basketShawarma) {
        Long shawarmaId = basketShawarma.getShawarma().getId();
        Long orderId = basketShawarma.getOrder().getId();
        BasketShawarmaDto basketShawarmaDto = basketApi.createBasketWithShawarma(orderId, shawarmaId, basketShawarma.getCount());
        return orderApi.getOrderById(orderId);
    }

    @PostMapping("/addBurgerToBasket")
    public OrderResponse addBurgerToBasket(@RequestBody BasketBurger basketBurger) {
        Long burgerId = basketBurger.getBurger().getId();
        Long orderId = basketBurger.getOrder().getId();
        BasketBurgerDto basketShawarmaDto = basketApi.createBasketWithBurger(orderId, burgerId, basketBurger.getCount());
        return orderApi.getOrderById(orderId);
    }

    @PostMapping("/addDrinkToBasket")
    public OrderResponse addDrinkToBasket(@RequestBody BasketDrink basketDrink) {
        Long drinkId = basketDrink.getDrink().getId();
        Long orderId = basketDrink.getOrder().getId();
        BasketDrinkDto basketShawarmaDto = basketApi.createBasketWithDrink(orderId, drinkId, basketDrink.getCount());
        return orderApi.getOrderById(orderId);
    }

    @PostMapping("/deleteShawarmaToBasket")
    public OrderResponse deleteShawarmaToBasket(@RequestBody BasketShawarma basketShawarma) {
        Long shawarmaId = basketShawarma.getShawarma().getId();
        Long orderId = basketShawarma.getOrder().getId();
        List<BasketShawarmaDto> basketShawarmaDto = basketApi.deleteBasketWithShawarma(orderId, shawarmaId);
        return orderApi.getOrderById(orderId);
    }

    @PostMapping("/deleteBurgerToBasket")
    public OrderResponse deleteBurgerToBasket(@RequestBody BasketBurger basketBurger) {
        Long shawarmaId = basketBurger.getBurger().getId();
        Long orderId = basketBurger.getOrder().getId();
        List<BasketBurgerDto> basketShawarmaDto = basketApi.deleteBasketWithBurger(orderId, shawarmaId);
        return orderApi.getOrderById(orderId);
    }

    @PostMapping("/totalPrice")
    public BigDecimal totalPriceOfOrder(@RequestBody OrderRequest orderRequest) {
        return basketApi.totalPriceAllBasketsForOrder(orderRequest.getId());
    }

    @PostMapping("/totalCount")
    public Long totalCountOfOrder(@RequestBody OrderRequest orderRequest) {
        return basketApi.totalCountAllBasketsForOrder(orderRequest.getId());
    }
}
