package DatabaseService.entities;

import DatabaseService.entities.Dao.Dao;
import DatabaseService.entities.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class DatabaseService {

    EntityManagerFactory factory;
    EntityManager manager;

    public DatabaseService() {
        this.factory = Persistence.createEntityManagerFactory("RecordStore-persistence");
        this.manager = factory.createEntityManager();
    }

    public List findAll(Class cls) {
        Dao dao = new Dao(manager);
        manager.getTransaction().begin();
        List list = dao.findAllInstances(cls);
        manager.getTransaction().commit();
        return list;
    }

    public Object findById(Class cls, Integer id) {
        Dao dao = new Dao(manager);
        manager.getTransaction().begin();
        Object o = dao.findById(cls, id);
        manager.getTransaction().commit();
        return o;
    }

    /**
     * finds the record in the database which is matching the argument
     * @param o
     * @return
     */

    public Record findRecord(Record o) {
        Dao dao = new Dao(manager);
        dao.setFactory(factory);
        manager.getTransaction().begin();
        Record found = dao.findRecord(o);
        manager.getTransaction().commit();
        return found;
    }

    public void persist(Object o) {
        Dao dao = new Dao(manager);
        manager.getTransaction().begin();
        dao.persist(o);
        manager.getTransaction().commit();
    }

    public void delete(Object o) {
        Dao dao = new Dao(manager);
        manager.getTransaction().begin();
        dao.delete(o);
        manager.getTransaction().commit();
    }

    public Card findCard(Card card) {
        Dao dao = new Dao(manager);
        dao.setFactory(factory);
        manager.getTransaction().begin();
        Card found = dao.findCard(card);
        manager.getTransaction().commit();
        return found;
    }

    public List<Pitem> findPitemsInPurchase(Purchase purchase) {
        Dao dao = new Dao(manager);
        dao.setFactory(factory);
        manager.getTransaction().begin();
        List<Pitem> list = dao.findPitemsInPurchase(purchase);
        manager.getTransaction().commit();
        return list;
    }

    public List<Litem> findLitemsInShop(RecordsList shop) {
        Dao dao = new Dao(manager);
        dao.setFactory(factory);
        manager.getTransaction().begin();
        List<Litem> list = dao.findLitemsInShop(shop);
        manager.getTransaction().commit();
        return list;

    }

    public void merge(Object o) {
        Dao dao = new Dao(manager);
        manager.getTransaction().begin();
        dao.merge(o);
        manager.getTransaction().commit();
    }
}
