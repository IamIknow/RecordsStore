package View.Model;

import DatabaseService.entities.entities.Employee;
import DatabaseService.entities.entities.Shop;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by sergey on 03.05.17.
 */
public class EmployeeModel {

    private IntegerProperty id;

    private StringProperty name;

    private StringProperty surname;

    private StringProperty birthDate;

    private StringProperty salary;

    private Shop shop;

    private StringProperty shopAddress;

    public EmployeeModel(Employee employee) {
        this.id = new SimpleIntegerProperty(employee.getId());
        this.birthDate = new SimpleStringProperty(employee.getBirthdate());
        this.name = new SimpleStringProperty(employee.getName());
        this.shop  = employee.getShop();
        this.shopAddress = new SimpleStringProperty(employee.getShop().getAddress());
        this.surname = new SimpleStringProperty(employee.getSurname());
        this.salary = new SimpleStringProperty(employee.getSalary());
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

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public StringProperty birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.set(birthDate);
    }

    public String getSalary() {
        return salary.get();
    }

    public StringProperty salaryProperty() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
    }

    public Shop getShop() {
        return shop;
    }

    public EmployeeModel setShop(Shop shop) {
        this.shop = shop;
        return this;
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
