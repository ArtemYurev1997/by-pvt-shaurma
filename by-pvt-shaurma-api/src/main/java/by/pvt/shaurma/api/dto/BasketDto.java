package by.pvt.shaurma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long goodId;
    private Long orderId;
    private Integer count;
    static Long nextId = 1L;

    public void setId() {
        id = nextId;
        nextId++;
    }
}
