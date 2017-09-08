package View.Controllers;

import DatabaseService.entities.entities.Litem;
import DatabaseService.entities.entities.Shop;
import View.Model.StorageModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Created by sergey on 03.05.17.
 */
public class StorageController  extends Controller{

    ObservableList<StorageModel> storageModels = FXCollections.observableArrayList();

    @FXML
    public TableView<StorageModel> storageTable;
    @FXML
    public TableColumn<StorageModel, Integer> id;
    @FXML
    public TableColumn<StorageModel, String> artist;
    @FXML
    public TableColumn<StorageModel, String> album;
    @FXML
    public TableColumn<StorageModel, String> shop;
    @FXML
    public TableColumn<StorageModel, String> amount;
    public MenuItem update;
    public MenuItem delete;


    @FXML
    private void initialize() {

        storageTable.setEditable(true);

        id.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        album.setCellValueFactory(cellData -> cellData.getValue().albumProperty());
        artist.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        shop.setCellValueFactory(cellData-> cellData.getValue().shopAddressProperty());
        amount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());

        refreshData();
    }

    private void refreshData() {
        storageModels.clear();
        List<Litem> list = service.findAll(Litem.class);

        for (Litem litem : list) {
            StorageModel model = new StorageModel(litem);
            storageModels.add(model);
        }

        storageTable.setItems(storageModels);
    }

    public void handleUpdate(ActionEvent actionEvent) {
        StorageModel model = storageTable.getSelectionModel().getSelectedItem();
        Integer id = model.getId();

        Litem litem = (Litem) service.findById(Litem.class, id);

        try {
            EditStorageController controller = showEditDialog(litem);
            if(controller.isOk()) {
                Litem newLitem = controller.getLitem();
                litem.setRecord(newLitem.getRecord());
                litem.setRecordsList(newLitem.getRecordsList());
                litem.setAmount(newLitem.getAmount());

                service.merge(litem);
                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshData();

    }

    public void handleDelete(ActionEvent actionEvent) {
        StorageModel model = storageTable.getSelectionModel().getSelectedItem();
        Integer id = model.getId();

        Litem litem = (Litem) service.findById(Litem.class, id);
        service.delete(litem);
        refreshData();
    }

    public void handleAdd(ActionEvent actionEvent) {

        try {
            EditStorageController controller = showEditDialog(new Litem());

            if(controller.isOk()) {
                service.persist(controller.getLitem());
                refreshData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private EditStorageController showEditDialog(Litem litem) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("editStorage.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add/Edit shop");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        EditStorageController controller = loader.getController();
        controller.setLitem(litem);
        controller.setStage(dialogStage);
        dialogStage.showAndWait();

        return controller;
    }
}
