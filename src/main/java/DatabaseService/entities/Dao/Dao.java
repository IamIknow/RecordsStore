package DatabaseService.entities.Dao;

import DatabaseService.entities.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class Dao<T> {

    /**
     * A reference to a manager object for queries executing
     */
    private EntityManager manager;
    private EntityManagerFactory factory;

    public Dao(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * Gets the list of all instances of the certain entity from the database
     * @param cls
     * @return
     */
    public List<T> findAllInstances(Class<T> cls) {
        return manager.createQuery("from "+cls.getName()).getResultList();
    }

    /**
     * Persists entity in the database
     * @param entity
     */
    public void persist(T entity) {
        manager.persist(entity);
    }

    /**
     * Finds the entity by given Id
     * @param cls
     * @param entityId
     * @return
     */
    public T findById(Class<T> cls, Integer entityId) {
        return manager.find(cls, entityId);
    }

    public Record findRecord(Record record) {
        CriteriaBuilder builder = factory.getCriteriaBuilder();
        CriteriaQuery<Record> find = builder.createQuery( Record.class );
        Root<Record> recordRoot = find.from(Record.class);
        find.select(recordRoot);


        recordRoot.get(Record_.artist);

        find.where(builder.equal(recordRoot.get(Record_.artist), record.getArtist()));
        find.where(builder.equal(recordRoot.get(Record_.album),record.getAlbum()));

        return manager.createQuery(find).getSingleResult();
    }

    /**
     * Deletes the entity from the database
     * @param entity
     */
    public void delete(T entity) {
        manager.remove(entity);
    }

    public void merge(T entity) {
        manager.merge(entity);
    }

    public Dao setFactory(EntityManagerFactory factory) {
        this.factory = factory;
        return this;
    }

    public Card findCard(Card card) {
        CriteriaBuilder builder = factory.getCriteriaBuilder();
        CriteriaQuery<Card> find = builder.createQuery(Card.class);

        Root<Card> cardRoot = find.from(Card.class);
        find.select(cardRoot);

        find.where(builder.equal(cardRoot.get(Card_.Type), card.getType()));

        return manager.createQuery(find).getSingleResult();

    }

    public List<Pitem> findPitemsInPurchase(Purchase purchase) {
        CriteriaBuilder builder = factory.getCriteriaBuilder();
        CriteriaQuery<Pitem> find = builder.createQuery(Pitem.class);

        Root<Pitem> purchaseRoot = find.from(Pitem.class);
        find.select(purchaseRoot);

        find.where(builder.equal(purchaseRoot.get(Pitem_.purchase), purchase.getId()));

        return manager.createQuery(find).getResultList();
    }

    public List<Litem> findLitemsInShop(RecordsList recordsList) {
        CriteriaBuilder builder = factory.getCriteriaBuilder();
        CriteriaQuery<Litem> find = builder.createQuery(Litem.class);

        Root<Litem> litemRoot = find.from(Litem.class);
        find.select(litemRoot);

        find.where(builder.equal(litemRoot.get(Litem_.recordsList),recordsList.getId()));

        return manager.createQuery(find).getResultList();
    }
}
