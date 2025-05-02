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
public class TestGetContactById {
    public static void main(String[] args) {
        ContactDao dao = new ContactDao();
        int contactId = 1; 

        try {
            Contacts contact = dao.getContactById(contactId);
            if (contact != null) {
                System.out.println("Contact found:");
                System.out.println(contact.getId() + " - " + contact.getFullName() + ", " +
                        contact.getPhoneNumber() + ", " + contact.getEmailAddress() + ", " +
                        contact.getIdNumber() + ", " + contact.getDateOfBirth() + ", " +
                        contact.getGender() + ", " + contact.getCounty() + ", " +
                        contact.getOrganizationName());
            }
        } catch (SQLException e) {
            System.err.println("Error fetching contact: " + e.getMessage());
        }
    }
}

    

