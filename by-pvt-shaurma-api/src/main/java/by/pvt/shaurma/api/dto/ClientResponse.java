package by.pvt.shaurma.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ClientResponse extends UserResponse {
    private LocalDate firstVisit;
    private LocalDate lastVisit;
    private String telephone;
    private BigDecimal amountSpent;
}
