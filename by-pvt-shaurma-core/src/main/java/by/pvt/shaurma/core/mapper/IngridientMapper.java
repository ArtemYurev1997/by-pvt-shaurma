package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.IngridientDto;
import by.pvt.shaurma.core.entity.Ingridient;
import org.springframework.stereotype.Component;

@Component
public class IngridientMapper {
    public IngridientDto toIngridientDto(Ingridient ingridient) {
        IngridientDto dto = new IngridientDto();
        dto.setName(ingridient.getName());
        return dto;
    }
}
