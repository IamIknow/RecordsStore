package View.Model;

import DatabaseService.entities.entities.Shop;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by sergey on 02.05.17.
 */
public class ShopModel {

    private IntegerProperty id;
    private StringProperty address;
    private StringProperty phone;

    public ShopModel(IntegerProperty id, StringProperty address, StringProperty phone) {
        this.id = id;
        this.address = address;
        this.phone = phone;
    }

    public ShopModel (Shop shop) {
        this.id = new SimpleIntegerProperty(shop.getId());
        this.address = new SimpleStringProperty(shop.getAddress());
        this.phone = new SimpleStringProperty(shop.getTelephone());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}
