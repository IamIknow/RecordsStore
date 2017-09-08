package View.Controllers;

import DatabaseService.entities.entities.Litem;
import DatabaseService.entities.entities.Record;
import DatabaseService.entities.entities.RecordsList;
import DatabaseService.entities.entities.Shop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by sergey on 03.05.17.
 */
public class EditStorageController extends Controller{


    @FXML
    public TextField amount;
    @FXML
    public ChoiceBox<String> shop;
    @FXML
    public ChoiceBox<String> record;
    @FXML
    private Button okButton;

    private Litem  litem = new Litem();
    private boolean isOk =  false;
    private Stage stage;

    public Litem getLitem() {
        return litem;
    }

    @FXML
    private void initialize() {
        ObservableList<String> observavbleRecords = FXCollections.observableArrayList();
        List<Record> records = service.findAll(Record.class);
        for(Record record : records) {
            String model = record.getId().toString()+" "+record.getArtist()+" "+record.getAlbum();
            observavbleRecords.add(model);
        }
        this.record.setItems(observavbleRecords);

        ObservableList<String> observableShops = FXCollections.observableArrayList();
        List<Shop> shops = service.findAll(Shop.class);
        for(Shop shop : shops) {
            String model = shop.getId()+" "+shop.getAddress();
            observableShops.add(model);
        }
        this.shop.setItems(observableShops);
    }

    public void setLitem(Litem litem) {
        this.litem = litem;

        this.amount.setText(litem.getAmount());
        if(litem.getRecord() != null)
            this.record.setValue(litem.getRecord().getId()+" "+litem.getRecord().getArtist()
                    +" "+litem.getRecord().getAlbum());
        if(litem.getRecordsList()!=null)
            this.shop.setValue(litem.getRecordsList().getShop().getId()+" "+
                    litem.getRecordsList().getShop().getAddress());
    }

    public boolean isOk() {
        return isOk;
    }

    public EditStorageController setOk(boolean ok) {
        isOk = ok;
        return this;
    }

    public Stage getStage() {
        return stage;
    }

    public EditStorageController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    @FXML
    private void handleOk() {
        litem.setAmount(amount.getText());

        String[] recordIds = String.valueOf(record.getValue()).split(" ");
        Integer recordId = Integer.valueOf(recordIds[0]);

        Record foundRecord = (Record) service.findById(Record.class, recordId);
        litem.setRecord(foundRecord);

        String[] ShopIds = String.valueOf(shop.getValue()).split(" ");
        Integer shopId = Integer.valueOf(ShopIds[0]);
        RecordsList foundRecordsList = (RecordsList)
                service.findById(RecordsList.class, shopId);
        litem.setRecordsList(foundRecordsList);

        stage.close();
        isOk = true;
    }
}
