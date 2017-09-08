package DatabaseService.entities.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;

/**
 * Created by sergey on 01.05.17.
 */

@Entity
@Table(name = "PITEM")
public class Pitem {

    @Id
    @Column(name = "PITEM_ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @Column(name = "Amount")
    private String amount;

    @ManyToOne()
    @JoinColumn(name = "PURCHASE_PID", foreignKey = @ForeignKey(name = "fk_PITEM_PURCHASE1"))
    private Purchase purchase;

    @ManyToOne()
    @JoinColumn(name = "LITEM_LITEM_ID", foreignKey = @ForeignKey(name = "fk_PITEM_LITEM1"))
    private Litem litem;

    public Integer getId() {
        return id;
    }

    public Pitem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public Pitem setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public Pitem setPurchase(Purchase purchase) {
        this.purchase = purchase;
        return this;
    }

    public Litem getLitem() {
        return litem;
    }

    public Pitem setLitem(Litem litem) {
        this.litem = litem;
        return this;
    }
}
