/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.model;

import com.zuriontech.contact.registry.dao.ContactDao;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author perpetual-akinyi
 */
public class TestContactDao {
    
     public static void main(String[] args) {
        ContactDao dao = new ContactDao();

        try {
            // 🟩 Test inserting a new contact
            dao.addContact(
                "Jane Doe",
                "0712345678",
                "jane.doe@example.com",
                "12345678",
                new Date(), // current date as birthdate
                "Female",
                "Nairobi",
                "ZurionTech"
            );

            // 🟦 Test reading all contacts
            List<Contacts> contacts = dao.getAllContacts();
            for (Contacts c : contacts) {
                System.out.println(
                    c.getId() + " - " + c.getFullName() + ", " +
                    c.getPhoneNumber() + ", " +
                    c.getEmailAddress() + ", " +
                    c.getDateOfBirth()
                );
            }

        } catch (SQLException e) {
          System.err.println("error" + e.getMessage());
        }
    }
}
    

