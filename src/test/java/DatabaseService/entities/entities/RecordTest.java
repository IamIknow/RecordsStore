package DatabaseService.entities.entities;

import DatabaseService.entities.DatabaseService;
import org.junit.Test;

import javax.persistence.NoResultException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sergey on 24.04.17.
 */
public class RecordTest {

    DatabaseService service = new DatabaseService();

    @Test
    public void testRecordEntity() {

        List<Record> list = service.findAll(Record.class);
        assertNotNull(list.get(0));
        assertNotNull(list.get(5));

        Record found = (Record) service.findById(Record.class, 1);
        assertNotNull(found);

        Record record = new Record();
        record.setArtist("Aphex Twin").setAlbum("sdf").setGenre("elec").setPrice("300");

        service.persist(record);
        assertNotNull(service.findRecord(record));
        service.delete(record);
        try {
            assertNull(service.findRecord(record));
        } catch (NoResultException e){
        }
    }
}