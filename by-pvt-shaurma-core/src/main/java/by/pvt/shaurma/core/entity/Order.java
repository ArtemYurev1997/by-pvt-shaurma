package by.pvt.shaurma.core.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Table(schema = "shaurmasch", name ="order")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(name = "seq_order", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private User userId;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "payment", nullable = false)
    private String payment;
    @ManyToMany(mappedBy = "orders")
    private List<Good> goods;

    public Order(Long id, User userId, BigDecimal cost, LocalDate date, String status, String payment) {
        this.id = id;
        this.userId = userId;
        this.cost = cost;
        this.date = date;
        this.status = status;
        this.payment = payment;
    }
}
