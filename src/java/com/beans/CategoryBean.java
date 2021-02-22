/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dao.CategoryDAO;
import com.entities.Category;
import com.entities.Product;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Shaimaa
 */
@Named(value = "categoryBean")
@RequestScoped
public class CategoryBean {
    
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryImagePath;
    
    @Inject
    private CategoryDAO categoryDao;

    /**
     * Creates a new instance of CategoryBean
     */
    public CategoryBean() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryImagePath() {
        return categoryImagePath;
    }

    public void setCategoryImagePath(String categoryImagePath) {
        this.categoryImagePath = categoryImagePath;
    }

    public CategoryDAO getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDAO categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    public void addCategory(){
        Category category = new Category(this.categoryId, this.categoryName);
        category.setCategoryDescription(this.categoryDescription);
        category.setCategoryImagePath(this.categoryImagePath);
        categoryDao.addCategory(category);
    }
    
    public List<Category> getAllCategories(){
        return categoryDao.getAllCategories();
    }
    
     public List<Product> getAllProductsByCategoryId(int categoryId){
        return categoryDao.getAllProductsOfCategory(categoryId);
    }
    
}
