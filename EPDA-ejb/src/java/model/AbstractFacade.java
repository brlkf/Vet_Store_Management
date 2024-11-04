/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author User
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    protected EntityManager em;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

  public T findByAttribute(String attributeName, Object attributeValue) {
    javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    javax.persistence.criteria.CriteriaQuery<T> cq = cb.createQuery(entityClass);
    javax.persistence.criteria.Root<T> root = cq.from(entityClass);

    // Check if the attribute is a String and should be compared case-insensitively
    if (attributeValue instanceof String) {
        // Use lower case for both the column and the value to compare
        cq.select(root).where(cb.equal(cb.lower(root.get(attributeName).as(String.class)), ((String) attributeValue).toLowerCase()));
    } else {
        // If it's not a String or doesn't need case-insensitive comparison, use the original method
        cq.select(root).where(cb.equal(root.get(attributeName), attributeValue));
    }

    try {
        return getEntityManager().createQuery(cq).getSingleResult();
    } catch (NoResultException e) {
        // Log the exception or handle it in some other way
        // For simplicity, we're just printing the exception message
        System.out.println("No result found for attribute: " + attributeName);
        return null;
    }
}


    public List<T> findAllByAttribute(String attributeName, Object attributeValue) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root).where(cb.equal(root.get(attributeName), attributeValue));

        try {
            return getEntityManager().createQuery(cq).getResultList();
        } catch (NoResultException e) {
            // Handle the case where no results are found, if necessary
            return null;
        }
    }






    public List<users> search(String searchTerm) {
        String searchPattern = "%" + searchTerm.toLowerCase() + "%";
        return em.createQuery("SELECT u FROM users u WHERE LOWER(u.name) LIKE :searchPattern OR LOWER(u.email) LIKE :searchPattern OR LOWER(u.otherField) LIKE :searchPattern", users.class)
                .setParameter("searchPattern", searchPattern)
                .getResultList();
    }

}
