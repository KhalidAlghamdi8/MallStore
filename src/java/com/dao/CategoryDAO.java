/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entities.Category;
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
public class CategoryDAO {
    
    @PersistenceContext
    private EntityManager em;
    

    
    public void addCategory(Category category){
        em.persist(category);
    }
    
    public void removeCategory(Category category){
        em.remove(category);
    }
    
    public void editCategory(Category category){
        em.merge(category);
        em.flush();
    }
    
    public List<Category> getAllCategories(){
        Query query = em.createNamedQuery("Category.findAll");
        return query.getResultList();
    }
    
    public Category getCategoryById(Integer id){
        Query query = em.createNamedQuery("Category.findByCategoryId");
//        Query query = em.createQuery("SELECT c FROM Category c WHERE c.categoryId = :categoryId");
        query.setParameter("categoryId", id);
        return (Category) query.getSingleResult();
    }
    
    public List<Product> getAllProductsOfCategory(Integer catId){
        Category category = getCategoryById(catId);
        return category.getProductList();
    }
}
