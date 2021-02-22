/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entities.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Shaimaa
 */
@Stateless
public class ProductDAO {
    
    @PersistenceContext
    private EntityManager em;

   
   
   //get all products by category
    //get all products 
    
    public Product addProduct(Product p){
        em.persist(p);
        em.flush();
        return p;
    }
    
    public void removeProduct(Product p){
        em.remove(p);
    }
    
    public void editProduct(Product p){
        em.merge(p);
        em.flush();
    }
    
    public Product getProductById(int productId){
        Query query = em.createNamedQuery("Product.findByProductId");
        query.setParameter("productId", productId);
        return (Product) query.getSingleResult();
    }
    
    public List<Product> getAllProducts(){
        return em.createNamedQuery("Product.findAll").getResultList();
    }
    

}
