/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.model;

import com.zuriontech.contact.registry.dao.ContactDao;
import java.sql.SQLException;

/**
 *
 * @author perpetual-akinyi
 */
public class TestDeleteContactDao {
    public static void main(String[] args){
    ContactDao dao = new ContactDao();
    
    try {
        boolean result = dao.deleteContact(1);
        if (result) {
        System.out.println("Delete succesfull.");
        }else {
        System.out.println("No contact found to delete.");
        }
        
    
    }catch (SQLException e){
    System.err.println("Error: " + e.getMessage());
    }
    
    
    
    
    }
    
}
