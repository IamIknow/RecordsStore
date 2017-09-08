package View.Controllers;

import DatabaseService.entities.entities.Cardholder;
import DatabaseService.entities.entities.Employee;
import View.Model.CardholderModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Created by sergey on 03.05.17.
 */
public class CardholderController extends Controller{

    private ObservableList<CardholderModel> cardholderModels = FXCollections.observableArrayList();

    @FXML
    private TableView<CardholderModel> cardholderModelTableView;

    @FXML
    private TableColumn<CardholderModel, String> customer;

    @FXML
    private TableColumn<CardholderModel, String> cardType;

    @FXML
    private TableColumn<CardholderModel, String> phone;

    @FXML
    private TableColumn<CardholderModel, String> address;

    @FXML
    private Button addButton;

    @FXML
    private ContextMenu menu;

    @FXML
    private MenuItem updateItem;

    @FXML
    private MenuItem deleteItem;


    public CardholderController() {
    }

    @FXML
    private void initialize() {
        this.cardholderModelTableView.setEditable(true);

        customer.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        cardType.setCellValueFactory(cellData -> cellData.getValue().cardTypeProperty());
        address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        phone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        refreshData();
    }

    private void refreshData() {

        cardholderModels.clear();
        List<Cardholder> cardholders = service.findAll(Cardholder.class);

        for(Cardholder cardholder : cardholders) {
            CardholderModel model = new CardholderModel(cardholder);
            cardholderModels.add(model);
        }

        cardholderModelTableView.setItems(cardholderModels);
    }


    private EditCardholderController showEditDialog(Cardholder cardholder) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("editCardholderDialog.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add/Edit record");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        EditCardholderController controller = loader.getController();
        controller.setCardholder(cardholder);
        controller.setStage(dialogStage);
        dialogStage.showAndWait();

        return controller;
    }

    public void handleAdd(ActionEvent actionEvent) {

        try {
            EditCardholderController controller = showEditDialog(new Cardholder());

            if(controller.isOk()) {
                service.persist(controller.getCardholder());

                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDelete() {
        CardholderModel model = cardholderModelTableView.getSelectionModel().getSelectedItem();
        Integer id = model.getCustomer().getId();
        Cardholder cardholder = (Cardholder) service.findById(Cardholder.class, id);

        service.delete(cardholder);
        refreshData();
    }


    public void handleUpdate(ActionEvent actionEvent) {

        CardholderModel cardholderModel = cardholderModelTableView.getSelectionModel().getSelectedItem();
        Integer id = cardholderModel.getCustomer().getId();
        Cardholder updated = (Cardholder) service.findById(Cardholder.class, id);

        try {
            EditCardholderController controller = showEditDialog(updated);

            if(controller.isOk()){
                Cardholder newCardholder = controller.getCardholder();
                updated.setCard(newCardholder.getCard())
                        .setId(newCardholder.getId())
                        .setEmail(newCardholder.getEmail())
                        .setAddress(newCardholder.getAddress())
                        .setPhone(newCardholder.getPhone());

                service.merge(updated);

                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
