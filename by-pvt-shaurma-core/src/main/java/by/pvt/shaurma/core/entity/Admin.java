package by.pvt.shaurma.core.entity;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(schema = "shaurmasch", name ="admin")
@Entity
@Getter
@Setter
@ToString
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

    public Admin(Long id, String name, String surname, String login, String password, String role, LocalDate dateEnter, LocalDate dateExit, String post, BigDecimal salary) {
        super(id, name, surname, login, password, role);
        this.dateEnter = dateEnter;
        this.dateExit = dateExit;
        this.post = post;
        this.salary = salary;
    }
}
