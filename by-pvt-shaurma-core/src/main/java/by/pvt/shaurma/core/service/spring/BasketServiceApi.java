package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.BasketApi;
import by.pvt.shaurma.api.dto.BasketBurgerDto;
import by.pvt.shaurma.api.dto.BasketDrinkDto;
import by.pvt.shaurma.api.dto.BasketDto;
import by.pvt.shaurma.api.dto.BasketShawarmaDto;
import by.pvt.shaurma.core.entity.*;
import by.pvt.shaurma.core.exception.ProgramException;
import by.pvt.shaurma.core.mapper.spring.BasketMappers;
import by.pvt.shaurma.core.repository.spring.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
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
        BasketShawarma basket;
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Shawarma> shawarma = shawarmaRepository.findById(shawarmaId);
        if(order.isPresent() && shawarma.isPresent()) {
            basket = new BasketShawarma(order.get(), shawarma.get(), count);
            basket.setCost(shawarma.get().getPrice().multiply(BigDecimal.valueOf(count)));
            basketShawarmaRepository.save(basket);
        }
        else throw new ProgramException("Значения шаурмы и заказа не являются null!");
        return basketMappers.toBasketShawarmaDto(basket);
    }

    public BasketBurgerDto createBasketWithBurger(Long orderId, Long burgerId, Long count) {
        BasketBurger basket;
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Burger> burger = burgerRepository.findById(burgerId);
        if(order.isPresent() && burger.isPresent()) {
            basket = new BasketBurger(order.get(), burger.get(), count);
            basket.setCost(burger.get().getPrice().multiply(BigDecimal.valueOf(count)));
            basketBurgerRepository.save(basket);
        }
        else throw new ProgramException("Значения бургера и заказа не являются null!");
        return basketMappers.toBasketBurgerDto(basket);
    }

    public BasketDrinkDto createBasketWithDrink(Long orderId, Long drinkId, Long count) {
        BasketDrink basket;
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Drink> drink = drinkRepository.findById(drinkId);
        if(order.isPresent() && drink.isPresent()) {
            basket = new BasketDrink(order.get(), drink.get(), count);
            basket.setCost(drink.get().getPrice().multiply(BigDecimal.valueOf(count)));
            basketDrinkRepository.save(basket);
        }
        else throw new ProgramException("Значения напитка и заказа не являются null!");
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
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<BigDecimal> shawarmaCost = Optional.ofNullable(basketShawarmaRepository.totalPriceShawarma(orderId));
        Optional<BigDecimal> burgerCost = Optional.ofNullable(basketBurgerRepository.totalPriceBurger(orderId));
        Optional<BigDecimal> drinkCost = Optional.ofNullable(basketDrinkRepository.totalPriceDrink(orderId));
        BigDecimal cost = null;
        if (order.isPresent()) {
            if(shawarmaCost.isPresent() && burgerCost.isPresent() && drinkCost.isPresent()) {
                cost = shawarmaCost.get().add(burgerCost.get()).add(drinkCost.get());
            }
            if(shawarmaCost.isPresent()) {
                cost = shawarmaCost.get();
            }
            if(burgerCost.isPresent()) {
                cost = burgerCost.get();
            }
            if(drinkCost.isPresent()) {
                cost = drinkCost.get();
            }
            if(shawarmaCost.isPresent() && burgerCost.isPresent()) {
                cost = shawarmaCost.get().add(burgerCost.get());
            }
            if(shawarmaCost.isPresent() && drinkCost.isPresent() && burgerCost.isEmpty()) {
                cost = shawarmaCost.get().add(drinkCost.get());
            }
            if(burgerCost.isPresent() && drinkCost.isPresent() && shawarmaCost.isEmpty()) {
                cost = burgerCost.get().add(drinkCost.get());
            }
                order.get().setCost(cost);
                orderRepository.save(order.get());
            }
        return cost;
    }

    @Override
    public Long totalCountAllBasketsForOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        Long count = null;
        Optional<Long> shawarmaCount = Optional.ofNullable(basketShawarmaRepository.totalCountShawarma(orderId));
        Optional<Long> burgerCount = Optional.ofNullable(basketBurgerRepository.totalCountBurger(orderId));
        Optional<Long> drinkCount = Optional.ofNullable(basketDrinkRepository.totalCountDrink(orderId));
        if (order.isPresent()) {
            if(shawarmaCount.isPresent() && burgerCount.isPresent() && drinkCount.isPresent()) {
                count = shawarmaCount.get() + shawarmaCount.get() + drinkCount.get();
            }
            if(shawarmaCount.isPresent()) {
                count = shawarmaCount.get();
            }
            if(burgerCount.isPresent()) {
                count = burgerCount.get();
            }
            if(drinkCount.isPresent()) {
                count = drinkCount.get();
            }
            if(shawarmaCount.isPresent() && burgerCount.isPresent()) {
                count = shawarmaCount.get() + burgerCount.get();
            }
            if(shawarmaCount.isPresent() && drinkCount.isPresent() && burgerCount.isEmpty()) {
                count = shawarmaCount.get() + drinkCount.get();
            }
            if(burgerCount.isPresent() && drinkCount.isPresent() && shawarmaCount.isEmpty()) {
                count = burgerCount.get() + drinkCount.get();
            }
            order.get().setCount(count);
            orderRepository.save(order.get());
        }
        return count;
    }



//            if (basketDrinkRepository.totalPriceDrink(orderId) == null) {
//            cost = basketShawarmaRepository.totalPriceShawarma(orderId).add(basketBurgerRepository.totalPriceBurger(orderId));
//        }
//        else if(basketBurgerRepository.totalPriceBurger(orderId) == null) {
//            cost = basketShawarmaRepository.totalPriceShawarma(orderId).add(basketDrinkRepository.totalPriceDrink(orderId));
//        }
//        else if(basketShawarmaRepository.totalPriceShawarma(orderId) == null) {
//            cost = basketBurgerRepository.totalPriceBurger(orderId).add(basketDrinkRepository.totalPriceDrink(orderId));
//        }
//        else if(basketBurgerRepository.totalPriceBurger(orderId) == null && basketDrinkRepository.totalPriceDrink(orderId) == null) {
//            cost = basketShawarmaRepository.totalPriceShawarma(orderId);
//        }
//        else if(basketBurgerRepository.totalPriceBurger(orderId) == null && basketShawarmaRepository.totalPriceShawarma(orderId) == null) {
//            cost = basketDrinkRepository.totalPriceDrink(orderId);
//        }
//        else if(basketShawarmaRepository.totalPriceShawarma(orderId) == null && basketDrinkRepository.totalPriceDrink(orderId) == null) {
//            cost = basketBurgerRepository.totalPriceBurger(orderId);
//        }
//        else {
//            cost = basketShawarmaRepository.totalPriceShawarma(orderId).add(basketBurgerRepository.totalPriceBurger(orderId)).add(basketDrinkRepository.totalPriceDrink(orderId));
//        }
}
