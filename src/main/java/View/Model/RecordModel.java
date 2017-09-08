package View.Model;

import DatabaseService.entities.entities.Record;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by sergey on 01.05.17.
 */
public class RecordModel {

    private IntegerProperty id;
    private StringProperty artist;
    private StringProperty album;
    private StringProperty year;
    private StringProperty genre;
    private StringProperty price;

    public RecordModel(Integer id, String artist, String album, String year, String genre, String price) {
        this.id = new SimpleIntegerProperty(id);
        this.artist = new SimpleStringProperty(artist);
        this.album = new SimpleStringProperty(album);
        this.year = new SimpleStringProperty(year);
        this.genre = new SimpleStringProperty(genre);
        this.price = new SimpleStringProperty(price);
    }

    public RecordModel(Record record) {
        this.id = new SimpleIntegerProperty(record.getId());
        this.artist = new SimpleStringProperty(record.getArtist());
        this.album = new SimpleStringProperty(record.getAlbum());
        this.year = new SimpleStringProperty(record.getYear());
        this.genre = new SimpleStringProperty(record.getGenre());
        this.price = new SimpleStringProperty(record.getPrice());
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

    public String getArtist() {
        return artist.get();
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
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

    public String getYear() {
        return year.get();
    }

    public StringProperty yearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}
