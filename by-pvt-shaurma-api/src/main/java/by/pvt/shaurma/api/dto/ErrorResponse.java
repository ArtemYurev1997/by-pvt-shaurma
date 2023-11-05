package by.pvt.shaurma.api.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    String message;
    Integer code;
}
