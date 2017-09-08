package DatabaseService.entities.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pitem.class)
public abstract class Pitem_ {

	public static volatile SingularAttribute<Pitem, String> amount;
	public static volatile SingularAttribute<Pitem, Purchase> purchase;
	public static volatile SingularAttribute<Pitem, Integer> id;
	public static volatile SingularAttribute<Pitem, Litem> litem;

}

