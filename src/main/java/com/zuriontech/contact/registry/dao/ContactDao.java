/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.dao;

import com.zuriontech.contact.registry.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author perpetual-akinyi
 */
public class ContactDao {

   
    public void addContact(String fullName, String phoneNumber, String emailAddress, String idNumber, Date dateOfBirth, String gender, String county, String organizationName ) throws SQLException{
        String sql = "INSERT INTO contacts (full_name, phone_number, email_address, id_number, date_of_birth, gender, county, organization_name)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, fullName);
            stmt.setString(2, phoneNumber);
            stmt.setString(3, emailAddress);
            stmt.setString(4, idNumber);
            stmt.setDate(5, new java.sql.Date(dateOfBirth.getTime())); 
            stmt.setString(6, gender);
            stmt.setString(7, county);
            stmt.setString(8, organizationName);
            
            
            int inserted = stmt.executeUpdate();
            if (inserted > 0){
                System.out.println("Contact information Successfully inserted");
            }       
        }catch (SQLException e){
         System.err.println("Failed to insert contacts info:" + e.getMessage());}
    }
    
    
}
