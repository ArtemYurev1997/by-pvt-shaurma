package by.pvt.shaurma.core.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(schema = "shaurmasch", name ="basket_burger")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@org.hibernate.annotations.Immutable
public class BasketBurger {
    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id implements Serializable {
        @Column(name = "order_id")
        protected Long orderId;
        @Column(name = "burger_id")
        protected Long burgerId;
    }

    @EmbeddedId
    protected Id id = new Id();
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "burger_id", insertable = false, updatable = false)
    private Burger burger;
    @Column(name = "count", nullable = false)
    private Long count;
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    public BasketBurger(Order order, Burger burger, Long count) {
        this.order = order;
        this.burger = burger;
        this.count = count;

        this.id.orderId = order.getId();
        this.id.burgerId = burger.getId();

        order.getBurgerList().add(this);
        burger.getBurgerList().add(this);
    }
}
