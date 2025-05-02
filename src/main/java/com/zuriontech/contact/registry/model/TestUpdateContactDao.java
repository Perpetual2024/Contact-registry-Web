/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.model;

import com.zuriontech.contact.registry.dao.ContactDao;
import java.sql.SQLException;
import java.util.Date;


/**
 *
 * @author perpetual-akinyi
 */
public class TestUpdateContactDao {
    public static void main(String[] args){
        ContactDao dao = new ContactDao();
        Contacts contact = new Contacts();
        
        try {
            contact.setId(1);
            
            contact.setFullName("Updated Name");
            contact.setPhoneNumber("0709876543");
            contact.setEmailAddress("akinyiperpe@gmail.com");
            contact.setIdNumber("4564894");
            contact.setDateOfBirth(new Date());
            contact.setGender("Female");
            contact.setCounty("Updated");
            contact.setIdNumber("Moringa");
            
            dao.updateContact(contact);
            
            
        
        }catch (SQLException e){
            System.err.println("SQL Error:" + e.getMessage());
        }catch (Exception e){
            System.err.println(e.getMessage() + "General Error: ");
        }
        
    
    }
    
}
