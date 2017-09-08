package View.Controllers;

import DatabaseService.entities.entities.Customer;
import DatabaseService.entities.entities.Purchase;
import DatabaseService.entities.entities.Shop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by sergey on 04.05.17.
 */
public class EditPurchaseController extends Controller{

    @FXML
    private Button okButton;
    @FXML
    private ChoiceBox<String> shop;
    @FXML
    private TextField date;
    @FXML
    private ChoiceBox<String> customer;

    private Purchase purchase = new Purchase();
    private boolean isOk = false;
    private Stage stage;


    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
        if(purchase.getShop()!=null)
            this.shop.setValue(purchase.getShop().getId()+" "+purchase.getShop().getAddress());
        if(purchase.getCustomer()!=null)
            this.customer.setValue(purchase.getCustomer().getId()+" "+purchase.getCustomer().getName());
        this.date.setText(purchase.getDate());
    }

    @FXML
    private void initialize() {
        ObservableList<String> observableShops = FXCollections.observableArrayList();
        List<Shop> shops = service.findAll(Shop.class);
        for(Shop shop : shops) {
            String model = shop.getId().toString()+" "+shop.getAddress();
            observableShops.add(model);
        }
        this.shop.setItems(observableShops);

        ObservableList<String> observableCustomers = FXCollections.observableArrayList();
        List<Customer> customers = service.findAll(Customer.class);
        for(Customer customer : customers) {
            String model = customer.getId().toString()+" "+customer.getName();
            observableCustomers.add(model);
        }
        this.customer.setItems(observableCustomers);

    }


    public void handleOk(ActionEvent actionEvent) {
        purchase.setDate(date.getText());

        Customer foundCustomer = (Customer) service.findById(Customer.class,
                Integer.valueOf(String.valueOf(customer.getValue().split(" ")[0])));
        Shop foundShop = (Shop) service.findById(Shop.class,
                Integer.valueOf(String.valueOf(shop.getValue().split(" ")[0])));

        purchase.setCustomer(foundCustomer);
        purchase.setShop(foundShop);

        stage.close();
        isOk = true;

    }

    public ChoiceBox<String> getShop() {
        return shop;
    }

    public EditPurchaseController setShop(ChoiceBox<String> shop) {
        this.shop = shop;
        return this;
    }

    public TextField getDate() {
        return date;
    }

    public EditPurchaseController setDate(TextField date) {
        this.date = date;
        return this;
    }

    public ChoiceBox<String> getCustomer() {
        return customer;
    }

    public EditPurchaseController setCustomer(ChoiceBox<String> customer) {
        this.customer = customer;
        return this;
    }

    public boolean isOk() {
        return isOk;
    }

    public EditPurchaseController setOk(boolean ok) {
        isOk = ok;
        return this;
    }

    public Stage getStage() {
        return stage;
    }

    public EditPurchaseController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public Purchase getPurchase() {
        return purchase;
    }


}
