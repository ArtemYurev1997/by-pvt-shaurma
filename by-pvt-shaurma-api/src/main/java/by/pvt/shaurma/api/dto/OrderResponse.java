package by.pvt.shaurma.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Long userId;
    private Long count;
    private BigDecimal cost;
    private String address;
    private String telephone;
    private String comment;
    private LocalDate date;
    private String status;
    private String payment;
    private List<BasketShawarmaDto> shawarmaList = new ArrayList<>();
    private List<BasketBurgerDto> burgerList = new ArrayList<>();
    private List<BasketDrinkDto> drinkList = new ArrayList<>();
}
