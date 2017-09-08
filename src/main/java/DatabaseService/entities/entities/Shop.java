package DatabaseService.entities.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sergey on 28.04.17.
 */

@Entity
@Table(name = "SHOP", schema = "RecordsStoreDatabase")
public class Shop {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "SID")
    private Integer id;

    @Column(name = "Address")
    private String address;

    @Column(name = "Telephone")
    private String telephone;

    public Integer getId() {
        return id;
    }

    public Shop setId(Integer id) {
        this.id = id;
        return this;
    }


    public String getAddress() {
        return address;
    }

    public Shop setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public Shop setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
