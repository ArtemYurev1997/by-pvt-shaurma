package by.pvt.shaurma.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

//@Table(schema = "shaurmasch", name ="basket")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
    @Id
    @SequenceGenerator(name = "seq_basket", sequenceName = "basket_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_basket")
    @Column(name = "id")
    private Long id;
    @Column(name = "good_id")
    private Long goodId;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "count")
    private Integer count;

    public Basket(Long goodId, Long orderId, Integer count) {
        this.goodId = goodId;
        this.orderId = orderId;
        this.count = count;
    }
}
