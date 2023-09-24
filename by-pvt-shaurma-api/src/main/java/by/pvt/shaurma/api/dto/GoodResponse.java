package by.pvt.shaurma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodResponse {
    private Long id;
    private String name;
    private Long code;
    private String description;
    private BigDecimal price;
}
