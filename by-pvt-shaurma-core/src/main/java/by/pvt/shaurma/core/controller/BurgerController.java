package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.BurgerApi;
import by.pvt.shaurma.api.dto.BurgerDto;
import by.pvt.shaurma.api.dto.IngridientDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("burger")
public class BurgerController {
    private final BurgerApi burgerApi;

    public BurgerController(@Qualifier("burgerServiceApi") BurgerApi burgerApi) {
        this.burgerApi = burgerApi;
    }

    @PostMapping("/searchForBurgerName")
    List<BurgerDto> getBurgersDtoByIngridient(@RequestBody IngridientDto ingridientDto) {
        String name = ingridientDto.getName();
        return burgerApi.getBurgersDtoByIngridient(name);
    }
}
