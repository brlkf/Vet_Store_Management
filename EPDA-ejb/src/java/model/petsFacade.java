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
public class petsFacade extends AbstractFacade<pets> {

    @PersistenceContext(unitName = "EPDA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public petsFacade() {
        super(pets.class);
    }
    
    public List<pets> findAllPet() {
        List<pets> allPets = findAll();

        Iterator<pets> iterator = allPets.iterator();

        while (iterator.hasNext()) {
            pets tmpCust = iterator.next();
            if (tmpCust.getDeletedAt() != null) {
                iterator.remove();
            }
        }

        System.out.println("Length: " + allPets.size());

        return allPets;
    }
    
    
    
}
