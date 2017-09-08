package View.Controllers;

import DatabaseService.entities.entities.Litem;
import DatabaseService.entities.entities.Pitem;
import DatabaseService.entities.entities.Purchase;
import DatabaseService.entities.entities.RecordsList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;


public class EditPitemController extends Controller {


    ObservableList<String> recordModels = FXCollections.observableArrayList();

    @FXML
    private TextField amount;
    @FXML
    private Button okButton;
    @FXML
    private ChoiceBox<String> record;

    private RecordsList list;

    private Purchase purchase;

    private Pitem pitem = new Pitem();
    private boolean isOk = false;
    private Stage stage;

    public void setPitem(Pitem pitem) {
        this.pitem = pitem;

        if(pitem.getLitem()!= null) {
            this.record.setValue(pitem.getLitem().getRecord().getId() + " " +
            pitem.getLitem().getRecord().getArtist() + " " + pitem.getLitem().getRecord().getAlbum());
            this.purchase = pitem.getPurchase();
        }

    }

    public EditPitemController() {
    }

    @FXML
    private void initialize() {

    }


    @FXML
    private void handleOk() {
        Integer id = Integer.valueOf(String.valueOf(record.getValue().split(" ")[0]));
        Litem litem = (Litem) service.findById(Litem.class, id);
        if (Integer.valueOf(litem.getAmount()) - Integer.valueOf(amount.getText()) >= 0) {
            pitem.setAmount(amount.getText());

            pitem.setLitem((Litem) service.findById(Litem.class, id));
            pitem.setPurchase(this.purchase);

            Integer newAmount = Integer.valueOf(litem.getAmount()) - Integer.valueOf(amount.getText());
            litem.setAmount(String.valueOf(newAmount));
            service.merge(litem);
            stage.close();
            isOk = true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.stage);
            alert.setTitle("Wrong input");
            alert.setContentText("Not enough items in the storage\nItems available: "
            +litem.getAmount());
            alert.showAndWait();
        }

    }


    public RecordsList getList() {
        return list;
    }

    public EditPitemController setList(RecordsList list) {
        this.list = list;
        return this;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public EditPitemController setPurchase(Purchase purchase) {
        List<Litem> list = service.findLitemsInShop((RecordsList) service.findById(RecordsList.class, purchase.getShop().getId()));

        for(Litem litem : list) {
            String string = litem.getId()+" "+litem.getRecord().getArtist()+" "+litem.getRecord().getAlbum();
            recordModels.add(string);
        }
        this.record.setItems(recordModels);
        this.purchase = purchase;

        return this;
    }

    public Pitem getPitem() {
        return pitem;
    }

    public boolean isOk() {
        return isOk;
    }

    public EditPitemController setOk(boolean ok) {
        isOk = ok;
        return this;
    }

    public Stage getStage() {
        return stage;
    }

    public EditPitemController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }
}

