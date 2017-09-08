package DatabaseService.entities.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import javax.swing.*;

/**
 * Created by sergey on 01.05.17.
 */
@Entity
@Table(name = "RECLIST")
public class RecordsList {

    @Id
    @Column(name = "LID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "Shop_SID", foreignKey = @ForeignKey(name = "fk_StoreLists_Stores1"))
    private Shop shop;


    public Integer getId() {
        return id;
    }

    public RecordsList setId(Integer id) {
        this.id = id;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public RecordsList setShop(Shop shop) {
        this.shop = shop;
        return this;
    }
}
