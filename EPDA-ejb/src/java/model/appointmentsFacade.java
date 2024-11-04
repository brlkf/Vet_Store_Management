       /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class appointmentsFacade extends AbstractFacade<appointments> {

    @PersistenceContext(unitName = "EPDA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public appointmentsFacade() {
        super(appointments.class);
    }
    
        public List<appointments> findAllActiveApp() {
        List<appointments> allAppointments = findAll();

        Iterator<appointments> iterator = allAppointments.iterator();

        while (iterator.hasNext()) {
            appointments tmpCust = iterator.next();
            if (tmpCust.getDeletedAt() != null) {
                iterator.remove();
            }
        }
        
        System.out.println("Length: " + allAppointments.size());
        
        return allAppointments;
    }
        
         public List<appointments> findAllScheduledApp() {
        List<appointments> allAppointments = findAll();

        Iterator<appointments> iterator = allAppointments.iterator();

        while (iterator.hasNext()) {
            appointments tmpCust = iterator.next();
            if (!tmpCust.getStatus().equals("Scheduled")) {
                iterator.remove();
            }
        }
                
        return allAppointments;
    }
         
             public List<appointments> findAllCompletedApp() {
        List<appointments> allAppointments = findAll();

        Iterator<appointments> iterator = allAppointments.iterator();

        while (iterator.hasNext()) {
            appointments tmpCust = iterator.next();
            if (!tmpCust.getStatus().equals("Complete")) {
                iterator.remove();
            }
        }
                
        return allAppointments;
    }
             
    
}
