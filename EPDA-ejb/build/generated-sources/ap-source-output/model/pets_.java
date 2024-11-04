package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.appointments;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-01T14:38:44")
@StaticMetamodel(pets.class)
public class pets_ { 

    public static volatile SingularAttribute<pets, String> petName;
    public static volatile SingularAttribute<pets, Timestamp> createdAt;
    public static volatile SingularAttribute<pets, Timestamp> deletedAt;
    public static volatile ListAttribute<pets, appointments> appointments;
    public static volatile SingularAttribute<pets, Long> id;
    public static volatile SingularAttribute<pets, String> type;
    public static volatile SingularAttribute<pets, Timestamp> updatedAt;

}