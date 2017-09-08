package DatabaseService.entities.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sergey on 07.04.17.
 */
@Entity
@Table(name = "RECORD",schema = "RecordsStoreDatabase")
public class Record implements Serializable{
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "RID")
    private Integer id;

    @Column(name = "Artist")
    private String artist;

    @Column(name = "Album")
    private String album;

    @Column(name = "Year")
    private String year;

    @Column(name = "Genre")
    private String genre;

    @Column(name = "Price")
    private String price;

    public Record(Integer id, String artist, String album, String year, String genre, String price) {
        this.id = id;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.price = price;
    }

    public Record() {
    }

    public Integer getId() {
        return id;
    }

    public Record setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public Record setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getAlbum() {
        return album;
    }

    public Record setAlbum(String album) {
        this.album = album;
        return this;
    }

    public String getYear() {
        return year;
    }

    public Record setYear(String year) {
        this.year = year;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public Record setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Record setPrice(String price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "DatabaseService.entities.entities.Record{" +
                "id=" + id +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
