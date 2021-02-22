/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Shaimaa
 */
@Embeddable
public class ShoppingCartPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "shopcart_id")
    private int shopcartId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private int customerId;

    public ShoppingCartPK() {
    }

    public ShoppingCartPK(int shopcartId, int customerId) {
        this.shopcartId = shopcartId;
        this.customerId = customerId;
    }

    public int getShopcartId() {
        return shopcartId;
    }

    public void setShopcartId(int shopcartId) {
        this.shopcartId = shopcartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) shopcartId;
        hash += (int) customerId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShoppingCartPK)) {
            return false;
        }
        ShoppingCartPK other = (ShoppingCartPK) object;
        if (this.shopcartId != other.shopcartId) {
            return false;
        }
        if (this.customerId != other.customerId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.ShoppingCartPK[ shopcartId=" + shopcartId + ", customerId=" + customerId + " ]";
    }
    
}
