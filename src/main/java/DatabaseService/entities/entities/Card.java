package DatabaseService.entities.entities;

import javax.persistence.*;

/**
 * Created by sergey on 22.04.17.
 */

@Entity
@Table(name = "CARD", schema = "RecordsStoreDatabase")

public class Card {

    @Id
    @Column(name = "Type")
    private String Type;

    @Column(name = "Discount")
    private Integer discount;

    public Integer getDiscount() {
        return discount;
    }

    public Card setDiscount(Integer discount) {
        this.discount = discount;
        return this;
    }

    public String getType() {
        return Type;
    }

    public Card setType(String type) {
        Type = type;
        return this;
    }

    @Override
    public String toString() {
        return "Card{" +
                "Type='" + Type + '\'' +
                ", discount=" + discount +
                '}';
    }
}
