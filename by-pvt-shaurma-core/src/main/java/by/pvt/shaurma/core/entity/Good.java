package by.pvt.shaurma.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Table(schema = "shaurmasch", name ="good")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    @Id
    @SequenceGenerator(name = "seq_good", sequenceName = "good_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_good")
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "code", nullable = false)
    private Long code;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(schema = "shaurmasch", name = "cart",
            joinColumns = {@JoinColumn(name = "good_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private List<Order> orders;

    public Good(String name, Long code, String description, BigDecimal price) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
    }
}
