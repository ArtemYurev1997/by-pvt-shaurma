package by.pvt.shaurma.core.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "shaurmasch", name ="drink")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "drink")
public class Drink {
    @Id
    @SequenceGenerator(name = "seq_drink", sequenceName = "drink_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_drink")
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String type;
    @Column(name = "code", nullable = false)
    private Long code;
    @Column(name = "price")
    private BigDecimal price;
    @OneToMany(mappedBy = "drink")
    private List<BasketDrink> drinkList = new ArrayList<>();

    public Drink(String type, Long code, BigDecimal price) {
        this.type = type;
        this.code = code;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", code=" + code +
                ", price=" + price +
                ", drinkList=" + drinkList +
                '}';
    }
}
