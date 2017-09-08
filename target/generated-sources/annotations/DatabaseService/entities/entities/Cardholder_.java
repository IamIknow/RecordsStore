package DatabaseService.entities.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cardholder.class)
public abstract class Cardholder_ {

	public static volatile SingularAttribute<Cardholder, String> address;
	public static volatile SingularAttribute<Cardholder, String> phone;
	public static volatile SingularAttribute<Cardholder, String> moneySpent;
	public static volatile SingularAttribute<Cardholder, Customer> id;
	public static volatile SingularAttribute<Cardholder, String> email;
	public static volatile SingularAttribute<Cardholder, Card> card;

}

