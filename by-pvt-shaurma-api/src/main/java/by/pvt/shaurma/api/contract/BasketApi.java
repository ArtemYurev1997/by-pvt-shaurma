package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.BasketDto;

import java.math.BigDecimal;

public interface BasketApi {

    BasketDto deleteGoodInBasket(Long goodId);

    BasketDto createBasket(Long orderId, Long goodId, Integer count);
}
