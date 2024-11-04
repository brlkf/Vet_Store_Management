package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-01T14:38:44")
@StaticMetamodel(vets.class)
public class vets_ { 

    public static volatile SingularAttribute<vets, Timestamp> createdAt;
    public static volatile SingularAttribute<vets, String> expName;
    public static volatile SingularAttribute<vets, Timestamp> deletedAt;
    public static volatile SingularAttribute<vets, Long> id;
    public static volatile SingularAttribute<vets, users> userID;
    public static volatile SingularAttribute<vets, Timestamp> updatedAt;

}