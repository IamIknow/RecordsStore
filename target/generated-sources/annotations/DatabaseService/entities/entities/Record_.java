package DatabaseService.entities.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Record.class)
public abstract class Record_ {

	public static volatile SingularAttribute<Record, String> artist;
	public static volatile SingularAttribute<Record, String> year;
	public static volatile SingularAttribute<Record, String> album;
	public static volatile SingularAttribute<Record, String> price;
	public static volatile SingularAttribute<Record, String> genre;
	public static volatile SingularAttribute<Record, Integer> id;

}

