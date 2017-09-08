package View.Controllers;

import DatabaseService.entities.entities.Card;
import DatabaseService.entities.entities.Record;
import View.Model.CardModel;
import View.Model.RecordModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * Created by sergey on 02.05.17.
 */
public class CardController extends Controller {

    private ObservableList<CardModel> cardModels = FXCollections.observableArrayList();

    @FXML
    private TableView<CardModel> cardModelTableView;

    @FXML
    private TableColumn<CardModel, String> type;

    @FXML
    private TableColumn<CardModel, Integer> discount;

    @FXML
    private ContextMenu menu;

    @FXML
    private MenuItem updateItem;

    @FXML
    private MenuItem deleteItem;

    @FXML
    private Button addButton;

    @FXML
    private void initialize() {

        type.setCellValueFactory(cellData->cellData.getValue().typeProperty());
        discount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDiscount()));

        refreshData();
    }

    public CardController() {
    }

    private void getData() {
        List<Card> list = service.findAll(Card.class);

        for(Card card : list) {
            CardModel model = new CardModel(card);
            cardModels.add(model);
        }
    }

    @FXML
    public void handleDelete() {

        CardModel selected = cardModelTableView.getSelectionModel().getSelectedItem();
        Card card = new Card().setDiscount(selected.getDiscount()).setType(selected.getType());

        Card deleted = service.findCard(card);

        service.delete(deleted);

        refreshData();
    }

    @FXML
    public void handleAdd() {
        try {
            EditCardController controller = showEditCardDialog(new Card());

            if(controller.isOk()) {
                service.persist(controller.getCard());
            }

            refreshData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleUpdate() {
        CardModel selected = this.cardModelTableView.getSelectionModel().getSelectedItem();
        Card card = new Card().setType(selected.getType()).setDiscount(selected.getDiscount());

        Card updated = service.findCard(card);

        try {
            EditCardController controller = showEditCardDialog(updated);
            if(controller.isOk()){
                Card newCard = controller.getCard();
                updated.setDiscount(newCard.getDiscount())
                        .setType(newCard.getType());
                service.merge(updated);
                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void refreshData() {
        cardModels.clear();
        getData();
        cardModelTableView.setItems(cardModels);
    }

    private EditCardController showEditCardDialog(Card card) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("editCardDialog.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add/Edit card");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        EditCardController controller = loader.getController();
        controller.setCard(card);
        controller.setStage(dialogStage);
        dialogStage.showAndWait();

        return controller;
    }

}
