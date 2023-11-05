package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.BasketApi;
import by.pvt.shaurma.api.dto.BasketBurgerDto;
import by.pvt.shaurma.api.dto.BasketDrinkDto;
import by.pvt.shaurma.api.dto.BasketDto;
import by.pvt.shaurma.api.dto.BasketShawarmaDto;
import by.pvt.shaurma.core.entity.*;
import by.pvt.shaurma.core.mapper.spring.BasketMappers;
import by.pvt.shaurma.core.repository.spring.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceApi implements BasketApi {
    private final BasketShawarmaRepository basketShawarmaRepository;
    private final BasketBurgerRepository basketBurgerRepository;
    private final BasketDrinkRepository basketDrinkRepository;
    private final ShawarmaRepository shawarmaRepository;
    private final BurgerRepository burgerRepository;
    private final DrinkRepository drinkRepository;
    private final OrderRepository orderRepository;
    private final BasketMappers basketMappers;

    public BasketShawarmaDto createBasketWithShawarma(Long orderId, Long shawarmaId, Long count) {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Shawarma> shawarma = shawarmaRepository.findById(shawarmaId);
        BasketShawarma basket = new BasketShawarma(order.get(), shawarma.get(), count);
        basket.setCost(shawarma.get().getPrice().multiply(BigDecimal.valueOf(count)));
        basketShawarmaRepository.save(basket);
        return basketMappers.toBasketShawarmaDto(basket);
    }

    public BasketBurgerDto createBasketWithBurger(Long orderId, Long burgerId, Long count) {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Burger> burger = burgerRepository.findById(burgerId);
        BasketBurger basket = new BasketBurger(order.get(), burger.get(), count);
        basket.setCost(burger.get().getPrice().multiply(BigDecimal.valueOf(count)));
        basketBurgerRepository.save(basket);
        return basketMappers.toBasketBurgerDto(basket);
    }

    public BasketDrinkDto createBasketWithDrink(Long orderId, Long drinkId, Long count) {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Drink> drink = drinkRepository.findById(drinkId);
        BasketDrink basket = new BasketDrink(order.get(), drink.get(), count);
        basket.setCost(drink.get().getPrice().multiply(BigDecimal.valueOf(count)));
        basketDrinkRepository.save(basket);
        return basketMappers.toBasketDrinkDto(basket);
    }

    public List<BasketShawarmaDto> deleteBasketWithShawarma(Long orderId, Long shawarmaId) {
        basketShawarmaRepository.delete(orderId, shawarmaId);
        return basketShawarmaRepository.findAll().stream().map(basketMappers::toBasketShawarmaDto).collect(Collectors.toList());
    }

    public List<BasketBurgerDto> deleteBasketWithBurger(Long orderId, Long burgerId) {
        basketBurgerRepository.delete(orderId, burgerId);
        return basketBurgerRepository.findAll().stream().map(basketMappers::toBasketBurgerDto).collect(Collectors.toList());
    }

    public List<BasketDrinkDto> deleteBasketWithDrink(Long orderId, Long drinkId) {
        basketDrinkRepository.delete(orderId, drinkId);
        return basketDrinkRepository.findAll().stream().map(basketMappers::toBasketDrinkDto).collect(Collectors.toList());
    }

    @Override
    public BigDecimal totalPriceAllBasketsForOrder(Long orderId) {
        return null;
    }

    @Override
    public Long totalCountAllBasketsForOrder(Long orderId) {
        return null;
    }

}
