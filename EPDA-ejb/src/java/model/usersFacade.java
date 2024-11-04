/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
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
public class usersFacade extends AbstractFacade<users> {

    @PersistenceContext(unitName = "EPDA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public usersFacade() {
        super(users.class);
    }
    
    public List<users> findAllActiveUser() {
    List<users> allUsers = findAll();

    Iterator<users> iterator = allUsers.iterator();

    while (iterator.hasNext()) {
        users tmpCust = iterator.next();
        if (tmpCust.getDeletedAt() != null) {
            iterator.remove();
        }
    }


    return allUsers;
}
       
}
