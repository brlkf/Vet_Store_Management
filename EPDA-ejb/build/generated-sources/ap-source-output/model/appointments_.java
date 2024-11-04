package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.customers;
import model.pets;
import model.users;
import model.vets;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-01T14:38:44")
@StaticMetamodel(appointments.class)
public class appointments_ { 

    public static volatile SingularAttribute<appointments, Timestamp> createdAt;
    public static volatile SingularAttribute<appointments, Timestamp> deletedAt;
    public static volatile SingularAttribute<appointments, vets> vet;
    public static volatile SingularAttribute<appointments, String> subject;
    public static volatile SingularAttribute<appointments, String> description;
    public static volatile SingularAttribute<appointments, Long> id;
    public static volatile SingularAttribute<appointments, customers> customers;
    public static volatile SingularAttribute<appointments, users> created_by;
    public static volatile SingularAttribute<appointments, Timestamp> Date;
    public static volatile SingularAttribute<appointments, pets> pet;
    public static volatile SingularAttribute<appointments, String> status;
    public static volatile SingularAttribute<appointments, Timestamp> updatedAt;

}