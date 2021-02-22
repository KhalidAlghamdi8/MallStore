/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shaimaa
 */
@Entity
@Table(name = "shopping_cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShoppingCart.findAll", query = "SELECT s FROM ShoppingCart s"),
    @NamedQuery(name = "ShoppingCart.findByShopcartId", query = "SELECT s FROM ShoppingCart s WHERE s.shoppingCartPK.shopcartId = :shopcartId"),
    @NamedQuery(name = "ShoppingCart.findByCustomerId", query = "SELECT s FROM ShoppingCart s WHERE s.shoppingCartPK.customerId = :customerId")})
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ShoppingCartPK shoppingCartPK;
//    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private Customer customer;

    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Customer customer;
    
    public ShoppingCart() {
    }

    public ShoppingCart(ShoppingCartPK shoppingCartPK) {
        this.shoppingCartPK = shoppingCartPK;
    }

    public ShoppingCart(int shopcartId, int customerId) {
        this.shoppingCartPK = new ShoppingCartPK(shopcartId, customerId);
    }

    public ShoppingCartPK getShoppingCartPK() {
        return shoppingCartPK;
    }

    public void setShoppingCartPK(ShoppingCartPK shoppingCartPK) {
        this.shoppingCartPK = shoppingCartPK;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shoppingCartPK != null ? shoppingCartPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShoppingCart)) {
            return false;
        }
        ShoppingCart other = (ShoppingCart) object;
        if ((this.shoppingCartPK == null && other.shoppingCartPK != null) || (this.shoppingCartPK != null && !this.shoppingCartPK.equals(other.shoppingCartPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.ShoppingCart[ shoppingCartPK=" + shoppingCartPK + " ]";
    }
    
}
