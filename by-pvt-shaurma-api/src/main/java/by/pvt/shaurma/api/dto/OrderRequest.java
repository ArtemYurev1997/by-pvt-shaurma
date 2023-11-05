package by.pvt.shaurma.api.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class OrderRequest {
    private ClientRequest userId;
    private Long count;
    @PositiveOrZero
    private BigDecimal cost;
    private String address;
    private String telephone;
    private String comment;
    private LocalDate date;
    private String status;
    private String payment;
}
