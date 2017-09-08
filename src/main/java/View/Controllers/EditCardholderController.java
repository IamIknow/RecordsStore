package View.Controllers;

import DatabaseService.entities.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by sergey on 03.05.17.
 */
public class EditCardholderController extends Controller{


    @FXML
    public TextField phone;
    @FXML
    public TextField addressField;
    @FXML
    public ChoiceBox<String> card;
    @FXML
    public ChoiceBox<String> customer;
    @FXML
    public Button okButton;

    private Cardholder cardholder = new Cardholder();
    private boolean isOk = false;
    private Stage stage;

    public EditCardholderController() {
    }

    @FXML
    private void initialize() {

        ObservableList<String> observavbleCards = FXCollections.observableArrayList();
        List<Card> cards = service.findAll(Card.class);
        for(Card card : cards) {
            String model = card.getType();
            observavbleCards.add(model);
        }
        this.card.setItems(observavbleCards);

        ObservableList<String> observableCustomers = FXCollections.observableArrayList();
        List<Customer> customers = service.findAll(Customer.class);
        for(Customer customer : customers) {
            String model = customer.getId()+" "+customer.getName();
            observableCustomers.add(model);
        }
        this.customer.setItems(observableCustomers);
    }

    public Cardholder getCardholder() {
        return cardholder;
    }

    public void setCardholder(Cardholder cardholder) {
        this.cardholder = cardholder;

        this.addressField.setText(cardholder.getAddress());
        this.phone.setText(cardholder.getPhone());

        if(cardholder.getCard() != null)
            this.card.setValue(cardholder.getCard().getType());
        if(cardholder.getId() != null)
            this.customer.setValue(cardholder.getId().getId()+" "+cardholder.getId().getName());

    }

    public boolean isOk() {
        return isOk;
    }

    public EditCardholderController setOk(boolean ok) {
        isOk = ok;
        return this;
    }

    public Stage getStage() {
        return stage;
    }

    public EditCardholderController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    @FXML
    private void handleOk() {
        cardholder.setAddress(addressField.getText());
        cardholder.setPhone(phone.getText());

        Card foundCard = service.findCard(new Card().setType(card.getValue()));
        cardholder.setCard(foundCard);

        Customer foundCustomer = (Customer) service.findById(Customer.class, Integer.valueOf(String.valueOf(customer.getValue().charAt(0))));
        cardholder.setId(foundCustomer);

        this.stage.close();
        isOk = true;
    }
}
