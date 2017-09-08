package View.Controllers;

import DatabaseService.entities.entities.Record;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 * Created by sergey on 02.05.17.
 */
public class EditRecordController extends Controller{

    @FXML
    private TextField artistField;

    @FXML
    private TextField albumField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField priceField;

    private Record record = new Record();
    private boolean isOk = false;
    private Stage stage;


    public Record getRecord() {
        return record;
    }

    @FXML
    private void initialize(){

    }

    public void setRecord(Record record) {
        this.record = record;

        artistField.setText(record.getArtist());
        albumField.setText(record.getAlbum());
        genreField.setText(record.getGenre());
        priceField.setText(record.getPrice());
        yearField.setText(record.getYear());
    }

    public boolean isOk() {
        return isOk;
    }

    @FXML
    private void handleOk() {
        record.setArtist(artistField.getText());
        record.setAlbum(albumField.getText());
        record.setGenre(genreField.getText());
        record.setYear(yearField.getText());
        record.setPrice(priceField.getText());

        this.stage.close();
        this.isOk = true;
    }

    public Stage getStage() {
        return stage;
    }

    public EditRecordController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }
}
