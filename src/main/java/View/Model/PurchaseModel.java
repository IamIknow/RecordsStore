package View.Model;

import DatabaseService.entities.entities.Customer;
import DatabaseService.entities.entities.Purchase;
import DatabaseService.entities.entities.Shop;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by sergey on 04.05.17.
 */
public class PurchaseModel {

    private IntegerProperty id;

    private StringProperty date;

    private StringProperty shopAddress;

    private Customer customer;

    private Shop shop;

    private StringProperty customerName;

    public PurchaseModel(Purchase purchase) {
        this.id = new SimpleIntegerProperty(purchase.getId());
        this.date = new SimpleStringProperty(purchase.getDate());
        this.customer = purchase.getCustomer();
        this.customerName = new SimpleStringProperty(purchase.getCustomer().getName());
        if(purchase.getShop()!=null)
            this.shopAddress = new SimpleStringProperty(purchase.getShop().getAddress());
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

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public Customer getCustomer() {
        return customer;
    }

    public PurchaseModel setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getShopAddress() {
        return shopAddress.get();
    }

    public StringProperty shopAddressProperty() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress.set(shopAddress);
    }
}
