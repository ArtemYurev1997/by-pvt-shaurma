package by.pvt.shaurma.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(schema = "shaurmasch", name ="basket_shawarma")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@org.hibernate.annotations.Immutable
public class BasketShawarma {
    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id implements Serializable {
        @Column(name = "order_id")
        protected Long orderId;
        @Column(name = "shawarma_id")
        protected Long shawarmaId;

    }
    @EmbeddedId
    protected Id id = new Id();
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "shawarma_id", insertable = false, updatable = false)
    private Shawarma shawarma;
    @Column(name = "count", nullable = false)
    private Long count;
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    public BasketShawarma(Order order, Shawarma shawarma, Long count) {
        this.order = order;
        this.shawarma = shawarma;
        this.count = count;

        this.id.orderId = order.getId();
        this.id.shawarmaId = shawarma.getId();

        order.getShawarmaList().add(this);
        shawarma.getShawarmaList().add(this);
    }
}