/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dao.CustomerDAO;
import com.entities.Customer;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author User
 */
@Named(value = "rigesterBean")
@RequestScoped
public class RigesterBean {

    @Inject
    private CustomerDAO customerDao;
    
    private int userId;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Date birthDate;

    public RigesterBean() {
    }

    public String addCoustomer() {

        Customer customer = new Customer(this.userId, this.username, this.firstname, this.lastname, this.password, this.email);
        customer.setPhone(this.phone);
        customer.setAddress(this.address);
        try{
           customerDao.addCustomer(customer);
        }catch(ValidatorException e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),null);
         FacesContext.getCurrentInstance().addMessage(null, facesMessage);
         return "register";
        }
        return "succses";
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
