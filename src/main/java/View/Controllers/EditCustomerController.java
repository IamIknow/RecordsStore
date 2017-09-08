package View.Controllers;

import DatabaseService.entities.entities.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by sergey on 02.05.17.
 */
public class EditCustomerController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private Button okButton;

    @FXML
    private void initialize(){

    }

    private Customer customer = new Customer();
    private Stage stage;
    private boolean isOk = false;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;

        nameField.setText(customer.getName());
        emailField.setText(customer.getEmail());
    }

    public boolean isOk() {
        return isOk;
    }


    public Stage getStage() {
        return stage;
    }

    public EditCustomerController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    @FXML
    private void handleOk() {
        customer.setEmail(emailField.getText());
        customer.setName(nameField.getText());

        stage.close();
        isOk = true;
    }
}
