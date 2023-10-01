package by.pvt.shaurma.core.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(schema = "shaurmasch", name ="order")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(name = "seq_order", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
    @Column(name = "id")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private Client userId;
    @Column(name = "count")
    private Long count;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "payment", nullable = false)
    private String payment;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(schema = "shaurmasch", name = "cart",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "good_id")})
    private List<Good> goods = new ArrayList<>();

    public Order(Long id, Client userId, Long count, BigDecimal cost, LocalDate date, String status, String payment) {
        this.id = id;
        this.userId = userId;
        this.count = count;
        this.cost = cost;
        this.date = date;
        this.status = status;
        this.payment = payment;
    }
}
