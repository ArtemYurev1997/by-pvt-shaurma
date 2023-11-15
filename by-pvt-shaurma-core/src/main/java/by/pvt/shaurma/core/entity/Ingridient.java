package by.pvt.shaurma.core.entity;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Table(schema = "shaurmasch", name ="ingridient")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingridient {
    @Id
    @SequenceGenerator(name = "seq_ingridient", sequenceName = "ingridient_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ingridient")
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "total_count")
    private Long total;
    @ManyToMany
    private List<Shawarma> shawarmas;

    public Ingridient(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
