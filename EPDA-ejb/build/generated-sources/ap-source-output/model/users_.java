package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-01T14:38:44")
@StaticMetamodel(users.class)
public class users_ { 

    public static volatile SingularAttribute<users, Date> createdAt;
    public static volatile SingularAttribute<users, String> password;
    public static volatile SingularAttribute<users, Date> deletedAt;
    public static volatile SingularAttribute<users, Integer> roleId;
    public static volatile SingularAttribute<users, String> name;
    public static volatile SingularAttribute<users, Long> id;
    public static volatile SingularAttribute<users, String> email;
    public static volatile SingularAttribute<users, String> status;
    public static volatile SingularAttribute<users, Date> updatedAt;

}