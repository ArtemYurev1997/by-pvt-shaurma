package by.pvt.shaurma.core.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Client userId;
    @Column(name = "count")
    private Long count;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "telephone_number")
    private String telephone;
    @Column(name = "address")
    private String address;
    @Column(name = "comment")
    private String comment;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "payment", nullable = false)
    private String payment;
    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<BasketShawarma> shawarmaList = new ArrayList<>();
    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<BasketBurger> burgerList = new ArrayList<>();
    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<BasketDrink> drinkList = new ArrayList<>();

    public Order(Long id, Client userId, Long count, BigDecimal cost, LocalDate date, String status, String payment) {
        this.id = id;
        this.userId = userId;
        this.count = count;
        this.cost = cost;
        this.date = date;
        this.status = status;
        this.payment = payment;
    }

    public Order(Long id, Client userId, Long count, BigDecimal cost, LocalDate date, String telephone, String address, String comment, String status, String payment) {
        this.id = id;
        this.userId = userId;
        this.count = count;
        this.cost = cost;
        this.date = date;
        this.telephone = telephone;
        this.address = address;
        this.comment = comment;
        this.status = status;
        this.payment = payment;
    }
}
