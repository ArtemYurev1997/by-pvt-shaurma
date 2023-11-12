package by.pvt.shaurma.core.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "shaurmasch", name ="burger")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "burger")
public class Burger {
    @Id
    @SequenceGenerator(name = "seq_burger", sequenceName = "burger_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_burger")
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String type;
    @Column(name = "code", nullable = false)
    private Long code;
    @Column(name = "price")
    private BigDecimal price;
    @OneToMany(mappedBy = "burger")
    private List<BasketBurger> burgerList = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(schema = "shaurmasch", name = "burger_ingridient",
            joinColumns = {@JoinColumn(name = "burger_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingridient_id")})
    private List<Ingridient> ingridients = new ArrayList<>();
//    @ManyToMany(mappedBy = "burgers", cascade = CascadeType.ALL)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private List<Order> orders;

    public Burger(String type, Long code, BigDecimal price, List<Ingridient> ingridients) {
        this.type = type;
        this.code = code;
        this.price = price;
        this.ingridients = ingridients;
    }

    @Override
    public String toString() {
        return "Burger{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", code=" + code +
                ", price=" + price +
                ", ingridients=" + ingridients +
                '}';
    }
}
