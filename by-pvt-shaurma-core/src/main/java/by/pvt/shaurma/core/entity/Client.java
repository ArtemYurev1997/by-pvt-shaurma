package by.pvt.shaurma.core.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Table(schema = "shaurmasch", name ="client")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_client_id")
//@NamedEntityGraph(name = "client_entity-graph", attributeNodes = {@NamedAttributeNode("orders"), @NamedAttributeNode("comments")})
public class Client extends User {
    @Column(name = "first_visit")
    private LocalDate firstVisit;
    @Column(name = "last_visit")
    private LocalDate lastVisit;
    @Column(name = "telephone_number")
    private String telephone;
    @Column(name = "address")
    private String address;
    @Column(name = "amount_spent")
    private BigDecimal amountSpent;
    @OneToMany(mappedBy = "userId")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Order> orders;
    @OneToMany
    private List<Comment> comments;

    public Client(Long id, String name, String surname, String login, String password, String role, LocalDate firstVisit, LocalDate lastVisit, String telephone, String address, BigDecimal amountSpent) {
        super(id, name, surname, login, password, role);
        this.firstVisit = firstVisit;
        this.lastVisit = lastVisit;
        this.telephone = telephone;
        this.address = address;
        this.amountSpent = amountSpent;
    }

//    @Override
//    public String toString() {
//        return "Client{" + "name=" + getName() +
//                ", surname=" + getSurname() +
//                ", login=" + getLogin() +
//                ", role=" + getRole() +
//                ", firstVisit=" + firstVisit +
//                ", lastVisit=" + lastVisit +
//                ", telephone='" + telephone + '\'' +
//                ", amountSpent=" + amountSpent +
//                ", orders=" + orders +
//                ", comments=" + comments +
//                '}';
//    }
}
