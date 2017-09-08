package DatabaseService.entities.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, Date> shippingDate;
	public static volatile SingularAttribute<Order, Integer> id;
	public static volatile SingularAttribute<Order, Date> orderDate;
	public static volatile SingularAttribute<Order, Customer> customer;

}

