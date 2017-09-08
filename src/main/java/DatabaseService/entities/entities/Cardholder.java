package DatabaseService.entities.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sergey on 28.04.17.
 */

@Entity
@Table(name = "CARDHOLDER")
public class Cardholder implements Serializable{

    @Id
    @OneToOne
    @JoinColumn(name = "Customer_CID", foreignKey = @ForeignKey(name = "fk_CARDHOLDER_Customers1"))
    private Customer id;

    @Column(name = "Telephone")
    private String phone;


    @Column(name = "Address")
    private String address;


    @Column(name = "Email")
    private String email;


    @Column(name = "MoneySpent")
    private String moneySpent;


    @ManyToOne
    @JoinColumn(name = "Card_Type", foreignKey = @ForeignKey(name = "fk_CARDHOLDER_Cards1"))
    private Card card;


    public Customer getId() {
        return id;
    }

    public Cardholder setId(Customer id) {
        this.id = id;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Cardholder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Cardholder setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Cardholder setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMoneySpent() {
        return moneySpent;
    }

    public Cardholder setMoneySpent(String moneySpent) {
        this.moneySpent = moneySpent;
        return this;
    }

    public Card getCard() {
        return card;
    }

    public Cardholder setCard(Card card) {
        this.card = card;
        return this;
    }
}
