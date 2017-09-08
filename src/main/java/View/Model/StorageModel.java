package View.Model;

import DatabaseService.entities.entities.Litem;
import DatabaseService.entities.entities.Record;
import DatabaseService.entities.entities.RecordsList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by sergey on 03.05.17.
 */
public class StorageModel {

    private IntegerProperty id;

    private StringProperty album;

    private StringProperty artist;

    private StringProperty amount;

    private StringProperty shopAddress;

    private Record record;

    private RecordsList list;

    public StorageModel(Litem litem) {
        this.id = new SimpleIntegerProperty(litem.getId());
        this.album = new SimpleStringProperty(litem.getRecord().getAlbum());
        this.artist = new SimpleStringProperty(litem.getRecord().getArtist());
        this.amount = new SimpleStringProperty(litem.getAmount());
        this.shopAddress = new SimpleStringProperty(litem.getRecordsList().getShop().getAddress());
        this.record = litem.getRecord();
        this.list = litem.getRecordsList();
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getAlbum() {
        return album.get();
    }

    public StringProperty albumProperty() {
        return album;
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public String getArtist() {
        return artist.get();
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public Record getRecord() {
        return record;
    }

    public StorageModel setRecord(Record record) {
        this.record = record;
        return this;
    }

    public RecordsList getList() {
        return list;
    }

    public StorageModel setList(RecordsList list) {
        this.list = list;
        return this;
    }

    public String getShopAddress() {
        return shopAddress.get();
    }

    public StringProperty shopAddressProperty() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress.set(shopAddress);
    }
}
