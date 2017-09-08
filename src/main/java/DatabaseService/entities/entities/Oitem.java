package DatabaseService.entities.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sergey on 01.05.17.
 */

@Entity
@Table(name = "OITEM")
public class Oitem {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "OITEM_ID")
    private Integer id;

    @Column(name = "Amount")
    private Integer amount;

    @ManyToOne()
    @JoinColumn(name = "RECORD_RID", foreignKey = @ForeignKey(name = "fk_OITEM_RECORD1"))
    private Record record;

    @ManyToOne()
    @JoinColumn(name = "ORD_OID", foreignKey = @ForeignKey(name = "fk_OTEM_ORD1"))
    private Order order;


    public Integer getId() {
        return id;
    }

    public Oitem setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public Oitem setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Record getRecord() {
        return record;
    }

    public Oitem setRecord(Record record) {
        this.record = record;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Oitem setOrder(Order order) {
        this.order = order;
        return this;
    }
}
