package View.Model;

import DatabaseService.entities.entities.Card;
import DatabaseService.entities.entities.Cardholder;
import DatabaseService.entities.entities.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by sergey on 03.05.17.
 */
public class CardholderModel {

    private Customer customer;

    private StringProperty customerName;

    private StringProperty phone;

    private StringProperty address;

    private StringProperty email;

    private Card card;

    private StringProperty cardType;

    public CardholderModel(Cardholder cardholder) {
        this.address = new SimpleStringProperty(cardholder.getAddress());
        this.customerName = new SimpleStringProperty(cardholder.getId().getName());
        this.customer = cardholder.getId();
        this.card = cardholder.getCard();
        this.cardType = new SimpleStringProperty(cardholder.getCard().getType());
        this.email = new SimpleStringProperty(cardholder.getEmail());
        this.phone = new SimpleStringProperty(cardholder.getPhone());
    }


    public Customer getCustomer() {
        return customer;
    }

    public CardholderModel setCustomer(Customer customer) {
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

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public Card getCard() {
        return card;
    }

    public CardholderModel setCard(Card card) {
        this.card = card;
        return this;
    }

    public String getCardType() {
        return cardType.get();
    }

    public StringProperty cardTypeProperty() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType.set(cardType);
    }
}
