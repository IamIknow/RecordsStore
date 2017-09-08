package DatabaseService.entities.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sergey on 28.04.17.
 */

@Entity
@Table(name = "ORD")
public class Order {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "OID")
    private Integer id;


    @Column(name = "OrderDate")
    private Date orderDate;

    @Column(name = "ShipDate")
    private Date shippingDate;

    @ManyToOne
    @JoinColumn(name = "Customer_CID",
            foreignKey = @ForeignKey(name = "fk_Orders_Customers1"))
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public Order setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", shippingDate=" + shippingDate +
                ", customer=" + customer +
                '}';
    }
}
