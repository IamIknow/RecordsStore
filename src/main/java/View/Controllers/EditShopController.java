package View.Controllers;

import DatabaseService.entities.entities.Shop;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by sergey on 02.05.17.
 */
public class EditShopController extends Controller {

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneField;

    private Stage stage;
    private Shop shop = new Shop();
    private boolean isOk = false;

    @FXML
    private void initialize() {

    }


    public Stage getStage() {
        return stage;
    }

    public EditShopController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public boolean isOk() {
        return isOk;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;

        addressField.setText(shop.getAddress());
        phoneField.setText(shop.getTelephone());
    }

    @FXML
    private void handleOk() {
        shop.setAddress(addressField.getText());
        shop.setTelephone(phoneField.getText());

        stage.close();
        isOk = true;
    }
}
