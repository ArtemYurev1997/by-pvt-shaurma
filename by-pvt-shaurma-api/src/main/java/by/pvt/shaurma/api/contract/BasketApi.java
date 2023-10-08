package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.BasketDto;

public interface BasketApi {
    BasketDto addShawarmaInBasket(Long shawarmaId);

    BasketDto deleteShawarmaInBasket(Long shawarmaId);

    BasketDto addBurgerInBasket(Long burgerId);

    BasketDto deleteBurgerInBasket(Long burgerId);

    BasketDto addDrinkInBasket(Long drinkId);

    BasketDto deleteDrinkInBasket(Long drinkId);

    BasketDto createBasket(Long orderId, Long count);
}
