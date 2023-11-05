package by.pvt.shaurma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    private Long id;
    private Long shawarmaId;
    private Long burgerId;
    private Long drinkId;
    private Long orderId;
    private Long count;
    private BigDecimal cost;
    static Long nextId = 1L;

    public void setId() {
        id = nextId;
        nextId++;
    }
}
