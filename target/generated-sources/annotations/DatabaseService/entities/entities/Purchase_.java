package DatabaseService.entities.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Purchase.class)
public abstract class Purchase_ {

	public static volatile SingularAttribute<Purchase, String> date;
	public static volatile SingularAttribute<Purchase, Shop> shop;
	public static volatile SingularAttribute<Purchase, Integer> price;
	public static volatile SingularAttribute<Purchase, Integer> id;
	public static volatile SingularAttribute<Purchase, Customer> customer;

}

