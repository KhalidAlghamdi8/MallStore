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
import com.entities.ShoppingCartItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.FetchProfile.Item;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author khalid
 */
@Named
@SessionScoped
public class ShoppingCart implements Serializable {

    @Inject
    private ProductDAO productDAO;

//    private HashMap<Product,Integer> shoppingCartProducts;
    private List<ShoppingCartItem> shoppingCartProducts;

    public ShoppingCart() {
//       shoppingCartProducts = new HashMap<>();
        shoppingCartProducts = new ArrayList<>();
    }

    public List<ShoppingCartItem> getShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void setShoppingCartProducts(List<ShoppingCartItem> shoppingCartProducts) {
        this.shoppingCartProducts = shoppingCartProducts;
    }

    public String addProductToShoppingCart(int productId) {

        int index = getIndexOfItem(productId);
        if (index == -1) {
            Product product = productDAO.getProductById(productId);
            ShoppingCartItem item = new ShoppingCartItem(product, 1);
            this.shoppingCartProducts.add(item);
        } else {
            this.shoppingCartProducts.get(index).increaseQuantityByOne();

        }

//        }
        return "ShoppingCart";

    }

    public String addProductToShoppingCart() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int productId = Integer.valueOf(request.getParameter("productId"));
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Product Id: "+productId);
        return addProductToShoppingCart(productId);

    }

    public void removeProductFromShoppingCart(ShoppingCartItem item) {

        int index = getIndexOfItem(item.getProduct().getProductId());
        this.shoppingCartProducts.remove(index);
    }

    public void changeQuantity(ValueChangeEvent e) {
        int newQuantity = (Integer) e.getNewValue();
        Integer id = (Integer) e.getComponent().getAttributes().get("productId");

        int index = getIndexOfItem(id);
        this.shoppingCartProducts.get(index).setQuantity(newQuantity);

    }

    public float getSubTotalOfProduct(ShoppingCartItem item) {
        int quantity = item.getQuantity();
        return item.getProduct().getProductPrice() * quantity;
    }

    public float getShoppingCartTotalCost() {
        float cost = 0;
        for (ShoppingCartItem item : this.shoppingCartProducts) {
            cost += getSubTotalOfProduct(item);
        }

        return cost;
    }

    public int getAllQuantities() {
        int quantity = 0;
        for (ShoppingCartItem item : this.shoppingCartProducts) {
            quantity += item.getQuantity();
        }
        return quantity;
    }

    private boolean containsProduct(Product product) {
        boolean contains = false;
        for (ShoppingCartItem item : this.shoppingCartProducts) {
            if (product.getProductId() == item.getProduct().getProductId()) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    private int getIndexOfItem(int ProductId) {
        int index = -1;
        boolean contains = false;
        for (ShoppingCartItem item : this.shoppingCartProducts) {
            index++;
            if (ProductId == item.getProduct().getProductId()) {
                contains = true;
                break;
            }
        }
        return contains ? index : -1;
    }

    private Product getProductById(int productId) {
        Product product = null;
        for (ShoppingCartItem item : this.shoppingCartProducts) {
            if (productId == item.getProduct().getProductId()) {
                product = item.getProduct();
                break;
            }
        }
        return product;
    }

}
