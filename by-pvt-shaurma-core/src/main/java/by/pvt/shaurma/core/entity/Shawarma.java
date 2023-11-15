package by.pvt.shaurma.core.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "shaurmasch", name ="shawarma")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "shawarma")
public class Shawarma {
    @Id
    @SequenceGenerator(name = "seq_shawarma", sequenceName = "shawarma_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_shawarma")
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String type;
    @Column(name = "code", nullable = false)
    private Long code;
    @Column(name = "price")
    private BigDecimal price;
    @OneToMany(mappedBy = "shawarma")
    private List<BasketShawarma> shawarmaList = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(schema = "shaurmasch", name = "shawarma_ingridient",
            joinColumns = {@JoinColumn(name = "shawarma_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingridient_id")})
    private List<Ingridient> ingridients= new ArrayList<>();

    public Shawarma(String type, Long code, BigDecimal price) {
        this.type = type;
        this.code = code;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Shawarma{" +
                "id=" + id +
                ", name='" + type + '\'' +
                ", code=" + code +
                ", price=" + price +
                ", ingridients=" + ingridients +
                '}';
    }
}
