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
public class contactDao {
    public void addContact(String FullName, String phoneNumber, String emailAddress, String idNumber, Date dateOfBirth, String gender, String county, String organizationName ) throws SQLException{
        String sql = "INSERT INTO contacts (full_name, phone_number, email_address, id_number, date_of_birth, gender, county, organization_name)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){}
    }
    
    
}
