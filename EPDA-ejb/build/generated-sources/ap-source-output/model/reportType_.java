package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.customers;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-01T14:38:44")
@StaticMetamodel(reportType.class)
public class reportType_ { 

    public static volatile SingularAttribute<reportType, String> treatment;
    public static volatile SingularAttribute<reportType, Timestamp> createdAt;
    public static volatile SingularAttribute<reportType, Timestamp> deletedAt;
    public static volatile SingularAttribute<reportType, String> diagnosis;
    public static volatile SingularAttribute<reportType, Long> id;
    public static volatile SingularAttribute<reportType, String> prognosis;
    public static volatile SingularAttribute<reportType, Timestamp> updatedAt;
    public static volatile SingularAttribute<reportType, customers> customer;

}