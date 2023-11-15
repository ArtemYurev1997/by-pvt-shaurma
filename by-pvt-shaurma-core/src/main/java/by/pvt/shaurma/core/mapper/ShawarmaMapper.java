package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.BasketShawarmaDto;
import by.pvt.shaurma.api.dto.ShawarmaDto;
import by.pvt.shaurma.core.entity.BasketShawarma;
import by.pvt.shaurma.core.entity.Shawarma;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ShawarmaMapper {
    IngridientMapper ingridientMapper = new IngridientMapper();

    public ShawarmaDto toShawarmaDto(Shawarma shawarma) {
        ShawarmaDto dto = new ShawarmaDto();
        dto.setId(shawarma.getId());
        dto.setCode(shawarma.getCode());
        dto.setType(shawarma.getType());
        dto.setPrice(shawarma.getPrice());
        dto.setIngridients(shawarma.getIngridients().stream().map(ingridientMapper::toIngridientDto).collect(Collectors.toList()));
        return dto;
    }
}
