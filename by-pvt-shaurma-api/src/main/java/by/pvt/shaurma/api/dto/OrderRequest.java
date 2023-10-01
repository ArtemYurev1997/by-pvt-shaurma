package by.pvt.shaurma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long id;
    private Long userId;
    private Long count;
    private BigDecimal cost;
    private String address;
    private LocalDate date;
    private String status;
    private String payment;
}
