package by.pvt.shaurma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest extends UserRequest{
    private LocalDate dateEnter;
    private LocalDate dateExit;
    private String post;
    private BigDecimal salary;
}
