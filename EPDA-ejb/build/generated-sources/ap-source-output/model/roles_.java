package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-01T14:38:44")
@StaticMetamodel(roles.class)
public class roles_ { 

    public static volatile SingularAttribute<roles, Timestamp> createdAt;
    public static volatile SingularAttribute<roles, Timestamp> deletedAt;
    public static volatile SingularAttribute<roles, String> roleName;
    public static volatile SingularAttribute<roles, Long> id;
    public static volatile SingularAttribute<roles, Timestamp> updatedAt;

}