package by.pvt.shaurma.core.mapper.spring;

import by.pvt.shaurma.api.dto.OrderRequest;
import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.core.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMappers {
    OrderResponse toResponse(Order order);
    Order toEntity(OrderRequest orderRequest);
}
