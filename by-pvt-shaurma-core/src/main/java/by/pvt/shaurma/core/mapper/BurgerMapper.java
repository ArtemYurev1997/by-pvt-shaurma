package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.BurgerDto;
import by.pvt.shaurma.core.entity.Burger;

public class BurgerMapper {
    public BurgerDto toBurgerDto(Burger burger) {
        BurgerDto dto = new BurgerDto();
        dto.setId(burger.getId());
        dto.setCode(burger.getCode());
        dto.setType(burger.getType());
        dto.setPrice(burger.getPrice());
        return dto;
    }
}
