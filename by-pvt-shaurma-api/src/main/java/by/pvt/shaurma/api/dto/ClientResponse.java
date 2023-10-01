package by.pvt.shaurma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse extends UserResponse {
    private LocalDate firstVisit;
    private LocalDate lastVisit;
    private String telephone;
    private BigDecimal amountSpent;
}
