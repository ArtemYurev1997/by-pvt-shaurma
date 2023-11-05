package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.DrinkDto;
import by.pvt.shaurma.core.entity.Drink;
import org.springframework.stereotype.Component;

@Component
public class DrinkMapper {
    public DrinkDto toDrinkDto(Drink drink) {
        DrinkDto dto = new DrinkDto();
        dto.setId(drink.getId());
        dto.setCode(drink.getCode());
        dto.setType(drink.getType());
        dto.setPrice(drink.getPrice());
        return dto;
    }
}
