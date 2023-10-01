package by.pvt.shaurma.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private LocalDate date;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id")
    private Client client;
}
