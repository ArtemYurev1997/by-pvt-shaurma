package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.BurgerDto;

import java.util.List;

public interface BurgerApi {
    List<BurgerDto> getBurgersDtoByIngridient(String name);
}
