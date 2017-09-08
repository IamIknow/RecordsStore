package DatabaseService.entities.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "PURCHASE")
public class Purchase {

    @Id
    @Column(name = "PID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @Column(name = "PurDate")
    private String date;

    @Column(name = "Price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "Customer_CID", foreignKey = @ForeignKey(name = "fk_Purchases_Customers1"))
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "Shop_SID", foreignKey = @ForeignKey(name = "PURCHASE_SHOP_fk"))
    private Shop shop;



    public Integer getId() {
        return id;
    }

    public Purchase setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Purchase setDate(String date) {
        this.date = date;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Purchase setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Purchase setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }


    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }

    public Shop getShop() {
        return shop;
    }

    public Purchase setShop(Shop shop) {
        this.shop = shop;
        return this;
    }
}
