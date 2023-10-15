package by.pvt.shaurma.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Table(schema = "shaurmasch", name = "comment")
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
    @Column(name = "commentary")
    private String comment;
    @Column(name = "date")
    private LocalDate date;
}
