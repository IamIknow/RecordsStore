package View.Model;

import DatabaseService.entities.entities.Card;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by sergey on 02.05.17.
 */
public class CardModel {

    private StringProperty type;
    private IntegerProperty discount;

    public CardModel(StringProperty type, IntegerProperty discount) {
        this.type = type;
        this.discount = discount;
    }

    public CardModel (Card card) {
        this.discount = new SimpleIntegerProperty(card.getDiscount());
        this.type = new SimpleStringProperty(card.getType());
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getDiscount() {
        return discount.get();
    }

    public IntegerProperty discountProperty() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount.set(discount);
    }
}
