package by.pvt.shaurma.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngridientDto {
    private String name;
    private BigDecimal price;
    private Long total;

    @Override
    public String toString() {
        return name;
    }
}
