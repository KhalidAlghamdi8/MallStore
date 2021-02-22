/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dao.CustomerDAO;
import com.entities.Customer;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named("login")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private CustomerDAO customerDao;

    private String username;
    private String password;
    private boolean loggedIn = false;
    private Customer currentCustomer;

    public LoginBean() {

    }

    public String checkLogin() {
        Customer customer = customerDao.getCustomerByUserNameAndPassword(username, password);
        if (customer != null) {
             this.loggedIn = true;
             this.currentCustomer = customer;
             return "home";
        }else{
            this.loggedIn = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect UserName and Password or Rigesteration if you not have account", "Please enter correct UserName and Passowrd "));
            return "login";

        }
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    
    
}
