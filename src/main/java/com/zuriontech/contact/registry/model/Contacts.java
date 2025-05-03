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
    public Contacts(String fullName, String phoneNumber, String emailAddress, String idNumber, String dateOfBirth, String gender, String county, String organizationName) {
    this.fullName = fullName;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.idNumber = idNumber;
    this.gender = gender;
    this.county = county;
    this.organizationName = organizationName;

    // Convert the date string to java.sql.Date
    try {
        this.dateOfBirth = java.sql.Date.valueOf(dateOfBirth); // expects "yyyy-MM-dd"
    } catch (IllegalArgumentException e) {
        this.dateOfBirth = null; // or handle it differently
    }
    }
    
    public int getId(){
     return id;
    }
    
    public void setId (int id){
     this.id = id;
    }
    
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    
    
    
}
