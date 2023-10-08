package by.pvt.shaurma.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(schema = "shaurmasch", name ="basket_drink")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@org.hibernate.annotations.Immutable
public class BasketDrink {
    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id implements Serializable {
        @Column(name = "order_id")
        protected Long orderId;
        @Column(name = "drink_id")
        protected Long drinkId;

    }
    @EmbeddedId
    protected Id id = new Id();
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "drink_id", insertable = false, updatable = false)
    private Drink drink;
    @Column(name = "count", nullable = false)
    private Long count;
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    public BasketDrink(Order order, Drink drink, Long count) {
        this.order = order;
        this.drink = drink;
        this.count = count;

        this.id.orderId = order.getId();
        this.id.drinkId = drink.getId();

        order.getDrinkList().add(this);
        drink.getDrinkList().add(this);
    }
}
