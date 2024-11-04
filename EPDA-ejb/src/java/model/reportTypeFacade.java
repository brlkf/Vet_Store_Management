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
public class reportTypeFacade extends AbstractFacade<reportType> {

    @PersistenceContext(unitName = "EPDA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public reportTypeFacade() {
        super(reportType.class);
    }
    
     public List<reportType> findAllActiveReportType() {
        List<reportType> allreportType = findAll();

        Iterator<reportType> iterator = allreportType.iterator();

        while (iterator.hasNext()) {
            reportType tmpCust = iterator.next();
            if (tmpCust.getDeletedAt() != null) {
                iterator.remove();
            }
        }
        
        System.out.println("Length: " + allreportType.size());
        
        return allreportType;
    }
    
}
