package View.Model;

import DatabaseService.entities.entities.Litem;
import DatabaseService.entities.entities.Pitem;
import DatabaseService.entities.entities.Purchase;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by sergey on 04.05.17.
 */
public class PitemModel {

    private IntegerProperty id;

    private StringProperty amount;

    private StringProperty album;

    private StringProperty artist;

    private Litem litem;

    private Purchase purchase;

    public PitemModel(Pitem pitem) {
        this.id = new SimpleIntegerProperty(pitem.getId());
        this.amount = new SimpleStringProperty(pitem.getAmount());
        this.album = new SimpleStringProperty(pitem.getLitem().getRecord().getAlbum());
        this.artist = new SimpleStringProperty(pitem.getLitem().getRecord().getArtist());
        this.litem = pitem.getLitem();
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

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
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

    public Litem getLitem() {
        return litem;
    }

    public PitemModel setLitem(Litem litem) {
        this.litem = litem;
        return this;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public PitemModel setPurchase(Purchase purchase) {
        this.purchase = purchase;
        return this;
    }
}
