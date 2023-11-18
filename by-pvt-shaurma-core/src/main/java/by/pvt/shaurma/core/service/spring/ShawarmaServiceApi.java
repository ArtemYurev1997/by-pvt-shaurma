package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.ShawarmaApi;
import by.pvt.shaurma.api.dto.ShawarmaDto;
import by.pvt.shaurma.core.entity.Ingridient;
import by.pvt.shaurma.core.entity.Shawarma;
import by.pvt.shaurma.core.mapper.spring.ShawarmaMappers;
import by.pvt.shaurma.core.repository.spring.IngridientRepository;
import by.pvt.shaurma.core.repository.spring.ShawarmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShawarmaServiceApi implements ShawarmaApi {
    private final ShawarmaRepository shawarmaRepository;
    private final ShawarmaMappers shawarmaMappers;
    private final IngridientRepository ingridientRepository;

    public List<ShawarmaDto> getShawarmaDtoByIngridient(String name) {
        return shawarmaRepository.getShawarmasByIngridientName(name).stream().map(shawarmaMappers::toDto).collect(Collectors.toList());
    }

    @Transactional
    public ShawarmaDto createShawarma(Long start, Long end, String type, Long code) {
        Shawarma shawarma = new Shawarma(type, code, new BigDecimal(0));
        Shawarma saveShawarma = shawarmaRepository.save(shawarma);
        List<Ingridient> ingridientList = ingridientRepository.selectIngridientsForCreate(start, end);
        ingridientRepository.updateCountIngridients(1L, start, end);
        BigDecimal price = ingridientRepository.getSumOfIngridients(start, end);
        saveShawarma.setPrice(price);
        saveShawarma.setIngridients(ingridientList);
        return shawarmaMappers.toDto(shawarmaRepository.save(saveShawarma));
    }
}
