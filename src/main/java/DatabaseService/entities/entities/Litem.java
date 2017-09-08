package DatabaseService.entities.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;

/**
 * Created by sergey on 01.05.17.
 */

@Entity
@Table(name = "LITEM")
public class Litem {

    @Id
    @Column(name = "LITEM_ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @Column(name = "Amount")
    private String amount;

    @ManyToOne()
    @JoinColumn(name = "RECORD_RID", foreignKey = @ForeignKey(name = "fk_LITEM_RECORD1"))
    Record record;

    @ManyToOne()
    @JoinColumn(name = "RECLIST_LID", foreignKey = @ForeignKey(name = "fk_LITEM_RECLIST1"))
    RecordsList recordsList;

    public Integer getId() {
        return id;
    }

    public Litem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public Litem setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public Record getRecord() {
        return record;
    }

    public Litem setRecord(Record record) {
        this.record = record;
        return this;
    }

    public RecordsList getRecordsList() {
        return recordsList;
    }

    public Litem setRecordsList(RecordsList recordsList) {
        this.recordsList = recordsList;
        return this;
    }

    @Override
    public String toString() {
        return "Litem{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", record=" + record +
                ", recordsList=" + recordsList +
                '}';
    }
}
