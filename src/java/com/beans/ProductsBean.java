/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dao.CategoryDAO;
import com.dao.ProductDAO;
import com.entities.Category;
import com.entities.Product;
import com.sun.istack.internal.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Shaimaa
 */
@Named(value = "productsBean")
@RequestScoped
public class ProductsBean {

    @Inject
    private ProductDAO productDao;
    @Inject 
    private CategoryDAO categoryDAO;

    private Integer productId;
    private String productName;
    private float productPrice;
    private String productImage;
    private Integer quantityInStock;
    private String productDescription;
    private String productColor;
    private String productWeight;
    private int categoryId;

    public ProductsBean() {
    }

    public ProductDAO getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDAO productDao) {
        this.productDao = productDao;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Product getProductById(int productId) {
        Product product = productDao.getProductById(productId);
        return product;
    }

    public void addProduct() {
        Product product = new Product(this.productId, this.productName);
        product.setProductDescription(this.productDescription);
        product.setProductImage(this.productImage);
        product.setProductColor(this.productColor);
        product.setProductPrice(this.productPrice);
        product.setProductWeight(this.productWeight);
        product.setQuantityInStock(this.quantityInStock);

        product.setCategoryId(categoryDAO.getCategoryById(this.categoryId));
        productDao.addProduct(product);
      
    }

}
