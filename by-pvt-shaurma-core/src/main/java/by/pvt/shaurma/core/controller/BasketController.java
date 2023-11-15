package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.BasketApi;
import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.BasketBurgerDto;
import by.pvt.shaurma.api.dto.BasketShawarmaDto;
import by.pvt.shaurma.api.dto.OrderRequest;
import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.core.entity.BasketBurger;
import by.pvt.shaurma.core.entity.BasketDrink;
import by.pvt.shaurma.core.entity.BasketShawarma;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("basket")
public class BasketController {
    private final BasketApi basketApi;
    private final OrderApi orderApi;

    public BasketController(@Qualifier("basketServiceApi") BasketApi basketApi, @Qualifier("orderServiceApi")OrderApi orderApi) {
        this.basketApi = basketApi;
        this.orderApi = orderApi;
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
        Long shawarmaId = basketBurger.getBurger().getId();
        Long orderId = basketBurger.getOrder().getId();
        BasketShawarmaDto basketShawarmaDto = basketApi.createBasketWithShawarma(orderId, shawarmaId, basketBurger.getCount());
        return orderApi.getOrderById(orderId);
    }

    @PostMapping("/addDrinkToBasket")
    public OrderResponse addDrinkToBasket(@RequestBody BasketDrink basketDrink) {
        Long shawarmaId = basketDrink.getDrink().getId();
        Long orderId = basketDrink.getOrder().getId();
        BasketShawarmaDto basketShawarmaDto = basketApi.createBasketWithShawarma(orderId, shawarmaId, basketDrink.getCount());
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
