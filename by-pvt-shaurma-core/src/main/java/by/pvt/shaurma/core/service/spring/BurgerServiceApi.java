package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.BurgerApi;
import by.pvt.shaurma.api.dto.BurgerDto;
import by.pvt.shaurma.core.mapper.spring.BurgerMappers;
import by.pvt.shaurma.core.repository.spring.BurgerRepository;
import by.pvt.shaurma.core.repository.spring.IngridientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BurgerServiceApi implements BurgerApi {
    private final BurgerRepository burgerRepository;
    private final BurgerMappers burgerMappers;
    private final IngridientRepository ingridientRepository;

    @Override
    public List<BurgerDto> getBurgersDtoByIngridient(String name) {
        return burgerRepository.getBurgersByIngridientName(name).stream().map(burgerMappers::toDto).collect(Collectors.toList());
    }
}
