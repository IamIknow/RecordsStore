package View.Controllers;

import DatabaseService.entities.entities.Record;
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

import java.io.IOException;
import java.util.List;


public class RecordController extends Controller{

    private ObservableList<RecordModel> records = FXCollections.observableArrayList();

    @FXML
    private TableView<RecordModel> recordTableView;

    @FXML
    private TableColumn<RecordModel,Integer> id;

    @FXML
    private TableColumn<RecordModel, String> artist;

    @FXML
    private TableColumn<RecordModel, String> album;

    @FXML
    private TableColumn<RecordModel, String> year;

    @FXML
    private TableColumn<RecordModel, String> genre;

    @FXML
    private TableColumn<RecordModel, String> price;

    @FXML
    private Button addButton;

    @FXML
    private ContextMenu menu;

    @FXML
    private MenuItem updateItem;

    @FXML
    private MenuItem deleteItem;

    public RecordController() {

    }


    @FXML
    private void initialize(){



        this.recordTableView.setEditable(true);

        id.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        album.setCellValueFactory(cellData -> cellData.getValue().albumProperty());
        year.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        artist.setCellValueFactory(cellData->cellData.getValue().artistProperty());
        genre.setCellValueFactory(cellData->cellData.getValue().genreProperty());
        price.setCellValueFactory(cellData->cellData.getValue().priceProperty());

        refreshData();
    }


    private void refreshData() {
        records.clear();
        getData();
        recordTableView.setItems(records);
    }

    private void getData() {

        List<Record> list = service.findAll(Record.class);
        for(Record r : list) {
            RecordModel model = new RecordModel(r);
            records.add(model);
        }
    }



    @FXML
    private void handleAdding() {
        try {
            EditRecordController controller = showEditDialog(new Record());
            if(controller.isOk()) {
                service.persist(controller.getRecord());
            }

            refreshData();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDelete() {

        RecordModel selected = recordTableView.getSelectionModel().getSelectedItem();
        Integer id = selected.getId();

        Record deleted = (Record) service.findById(Record.class, id);

        service.delete(deleted);

        refreshData();
    }

    @FXML
    public void handleUpdate() {

        RecordModel selected = recordTableView.getSelectionModel().getSelectedItem();
        Integer id = selected.getId();

        Record updated = (Record) service.findById(Record.class, id);

        try {
            EditRecordController controller = showEditDialog(updated);
            if(controller.isOk()){
                Record newRecord = controller.getRecord();
                updated.setGenre(newRecord.getGenre())
                        .setPrice(newRecord.getPrice())
                        .setAlbum(newRecord.getAlbum())
                        .setYear(newRecord.getYear())
                        .setArtist(newRecord.getArtist());
                service.merge(updated);

                refreshData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EditRecordController showEditDialog(Record record) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("editRecordDialog.fxml"));
        AnchorPane pane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add/Edit record");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(pane);
        dialogStage.setScene(scene);

        EditRecordController controller = loader.getController();
        controller.setRecord(record);
        controller.setStage(dialogStage);
        dialogStage.showAndWait();

        return controller;
    }
}
