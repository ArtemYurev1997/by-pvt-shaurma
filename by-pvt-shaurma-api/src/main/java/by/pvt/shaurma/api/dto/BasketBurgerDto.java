package by.pvt.shaurma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketBurgerDto {
    private BurgerDto burger;
    private Long count;
    private BigDecimal cost;
}
