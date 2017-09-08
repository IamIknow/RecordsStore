package DatabaseService.entities.entities;

import DatabaseService.entities.DatabaseService;
import org.junit.Test;

import javax.persistence.NoResultException;


import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sergey on 28.04.17.
 */
public class CardTest {

    DatabaseService service = new DatabaseService();

    @Test
    public void testCardDao() {
        List<Card> list = service.findAll(Card.class);
        assertNotNull(list.get(0));

        /*Card found = (Card) service.findById(Card.class, "gold");
        assertNotNull(found);*/

        Card card = new Card();
        card.setType("Naebka");
        card.setDiscount(0);


        service.persist(card);
        assertNotNull(service.findCard(card));
        service.delete(card);
        try {
            assertNull(service.findCard(card));
        } catch (NoResultException e){
        }
    }

}