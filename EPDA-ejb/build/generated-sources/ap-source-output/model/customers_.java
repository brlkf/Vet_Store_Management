package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.pets;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-01T14:38:44")
@StaticMetamodel(customers.class)
public class customers_ { 

    public static volatile SingularAttribute<customers, Timestamp> createdAt;
    public static volatile SingularAttribute<customers, Timestamp> deletedAt;
    public static volatile SingularAttribute<customers, String> phone;
    public static volatile SingularAttribute<customers, String> name;
    public static volatile SingularAttribute<customers, Long> id;
    public static volatile SingularAttribute<customers, pets> pet;
    public static volatile SingularAttribute<customers, Timestamp> updatedAt;

}