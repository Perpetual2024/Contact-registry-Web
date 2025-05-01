/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.model;

import java.util.Date;

/**
 *
 * @author perpetual-akinyi
 */
public class Contacts {
    private int id;
    private String fullName;
    private String phoneNumber;
    private String emailAddress;
    private String idNumber;
    private Date dateOfBirth;
    private String gender;
    private String county;
    private String organizationName;
    
    
    public Contacts (){};
    
    public Contacts (int id, String fullName, String phoneNumber, String emailAddress, String idNumber, Date dateOfBirth, String gender, String county, String organizationName){
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.idNumber = idNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.county = county;
        this.organizationName = organizationName;
    
    }
    
    public int getId(){
    return id;
    }
    
    public void setId (int id){
    this.id = id;
    }
    
}
