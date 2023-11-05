package by.pvt.shaurma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BurgerDto {
    private Long id;
    private String type;
    private Long code;
    private BigDecimal price;
    private List<IngridientDto> ingridients = new ArrayList<>();
}
