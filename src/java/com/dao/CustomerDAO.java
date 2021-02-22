/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entities.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class CustomerDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    
    public void addCustomer(Customer customer){
        em.persist(customer);
    }
    
    public Customer getCustomerByUserNameAndPassword(String userName,String password){
            Customer customer = (Customer)em.createNamedQuery("Customer.controller").setParameter("username", userName).setParameter("password", password).getSingleResult();
            return customer;
    }
    
}
