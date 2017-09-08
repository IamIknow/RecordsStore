package View.Model;

import DatabaseService.entities.entities.Customer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by sergey on 02.05.17.
 */
public class CustomerModel {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty email;


    public CustomerModel(Customer customer) {
        this.id = new SimpleIntegerProperty(customer.getId());
        this.email = new SimpleStringProperty(customer.getEmail());
        this.name = new SimpleStringProperty(customer.getName());
    }

    public CustomerModel(IntegerProperty id, StringProperty name, StringProperty email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
