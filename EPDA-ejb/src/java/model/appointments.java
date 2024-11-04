/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author User
 */
@Entity
public class appointments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;

    private String description;
    private String status;
    private Timestamp Date;
    private Timestamp  createdAt;
    private Timestamp  updatedAt;
    private Timestamp  deletedAt;
    
    @OneToOne
    @JoinColumn(name = "vetId", referencedColumnName = "id")
    private vets vet;

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp Date) {
        this.Date = Date;
    }
    
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id") // 'createdBy' is the column in the 'appointments' table that references 'id' in the 'users' table.
    private users created_by;

  

  
   
    
    @OneToOne
    @JoinColumn(name = "petId", referencedColumnName = "id")
    private pets pet;
    
    
    @OneToOne
    @JoinColumn(name = "cusId", referencedColumnName = "id")
    private customers customers;
    
      public pets getPet() {
        return pet;
    }

    public void setPet(pets pet) {
        this.pet = pet;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public users getCreated_by() {
        return created_by;
    }

    public void setCreated_by(users created_by) {
        this.created_by = created_by;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String ststus) {
        this.status = ststus;
    }

    public vets getVet() {
        return vet;
    }

    public void setVet(vets vet) {
        this.vet = vet;
    }

    public customers getCustomers() {
        return customers;
    }

    public void setCustomers(customers customers) {
        this.customers = customers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof appointments)) {
            return false;
        }
        appointments other = (appointments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.appointments[ id=" + id + " ]";
    }
    
}
