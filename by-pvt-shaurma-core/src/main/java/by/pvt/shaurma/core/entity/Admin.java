package by.pvt.shaurma.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(schema = "shaurmasch", name ="admin")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_admin_id")
public class Admin extends User{
    @Column(name = "date_enter")
    private LocalDate dateEnter;
    @Column(name = "date_exit")
    private LocalDate dateExit;
    @Column(name = "post")
    private String post;
    @Column(name = "amount_spent")
    private BigDecimal salary;
}
