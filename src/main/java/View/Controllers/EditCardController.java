package View.Controllers;

import DatabaseService.entities.entities.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 * Created by sergey on 02.05.17.
 */
public class EditCardController extends Controller {

    @FXML
    private TextField typeField;

    @FXML
    private TextField discountField;

    @FXML
    private Button buttonOk;

    @FXML
    private void initialize(){

    }

    private Card card = new Card();
    private boolean isOk = false;
    private Stage stage;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;

        typeField.setText(card.getType());
        discountField.setText(String.valueOf(card.getDiscount()));
    }

    public boolean isOk() {
        return isOk;
    }


    public EditCardController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    @FXML
    private void handleOk() {
        card.setDiscount(Integer.valueOf(discountField.getText()));
        card.setType(typeField.getText());

        stage.close();
        isOk = true;
    }
}
