package by.pvt.shaurma.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AdminResponse extends UserResponse{
    private LocalDate dateEnter;
    private LocalDate dateExit;
    private String post;
    private BigDecimal salary;

}
