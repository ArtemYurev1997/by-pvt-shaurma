package by.pvt.shaurma.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long id;
    private String comment;
    private LocalDateTime date;
}
