/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.dao;

import com.zuriontech.contact.registry.model.Contacts;
import com.zuriontech.contact.registry.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    public Contacts getContactById(int id) throws SQLException {
    String sql = "SELECT * FROM contacts WHERE id = ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Contacts contact = new Contacts();
                contact.setId(rs.getInt("id"));
                contact.setFullName(rs.getString("full_name"));
                contact.setPhoneNumber(rs.getString("phone_number"));
                contact.setEmailAddress(rs.getString("email_address"));
                contact.setIdNumber(rs.getString("id_number"));
                contact.setDateOfBirth(rs.getDate("date_of_birth"));
                contact.setGender(rs.getString("gender"));
                contact.setCounty(rs.getString("county"));
                contact.setOrganizationName(rs.getString("organization_name"));

                return contact;
            } else {
                System.out.println("No contact found with ID: " + id);
                return null;
            }
        }
    }
    }
    
    /**
     *
     * @param county
     * @return
     * @throws SQLException
     */
    public List<Contacts> getContactsByCounty(String county) throws SQLException {
    List<Contacts> contactList = new ArrayList<>();
    String sql = "SELECT * FROM contacts WHERE county = ?";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, county);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Contacts contact = new Contacts();
                contact.setId(rs.getInt("id"));
                contact.setFullName(rs.getString("full_name"));
                contact.setPhoneNumber(rs.getString("phone_number"));
                contact.setEmailAddress(rs.getString("email_address"));
                contact.setIdNumber(rs.getString("id_number"));
                contact.setDateOfBirth(rs.getDate("date_of_birth"));
                contact.setGender(rs.getString("gender"));
                contact.setCounty(rs.getString("county"));
                contact.setOrganizationName(rs.getString("organization_name"));

                contactList.add(contact);
            }
        }
    }

    return contactList;
}
    public Contacts getContactByPhoneNumber(String phoneNumber) throws SQLException {
    String query = "SELECT * FROM contacts WHERE phone_number LIKE ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, "%" + phoneNumber.trim() + "%"); // Wildcard search
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Contacts(
                rs.getInt("id"),
                rs.getString("full_name"),
                rs.getString("phone_number"),
                rs.getString("email_address"),
                rs.getString("id_number"),
                rs.getDate("date_of_birth"),
                rs.getString("gender"),
                rs.getString("county"),
                rs.getString("organization_name")
            );
        }
    }
    return null;
}



    
    
            
    public List<Contacts> getAllContacts() throws SQLException {
        List<Contacts> contactList = new ArrayList<>();
        String sql = "SELECT * FROM contacts";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Contacts contact = new Contacts();
                contact.setId(rs.getInt("id"));
                contact.setFullName(rs.getString("full_name"));
                contact.setPhoneNumber(rs.getString("phone_number"));
                contact.setEmailAddress(rs.getString("email_address"));
                contact.setIdNumber(rs.getString("id_number"));
                contact.setDateOfBirth(rs.getDate("date_of_birth"));
                contact.setGender(rs.getString("gender"));
                contact.setCounty(rs.getString("county"));
                contact.setOrganizationName(rs.getString("organization_name"));

                contactList.add(contact);
            }
        }

        return contactList;
}
     public boolean updateContact(Contacts contact) {
    String sql = "UPDATE contacts SET full_name=?, phone_number=?, email_address=?, id_number=?, date_of_birth=?, gender=?, county=?, organization_name=? WHERE id=?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, contact.getFullName());
        stmt.setString(2, contact.getPhoneNumber());
        stmt.setString(3, contact.getEmailAddress());
        stmt.setString(4, contact.getIdNumber());
        stmt.setDate(5, new java.sql.Date(contact.getDateOfBirth().getTime()));
        stmt.setString(6, contact.getGender());
        stmt.setString(7, contact.getCounty());
        stmt.setString(8, contact.getOrganizationName());
        stmt.setInt(9, contact.getId());

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;

    } catch (SQLException e) {
            System.err.println("Failed to edit contact: " + e.getMessage());
            return false;
        }
}

     public boolean deleteContact(int id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Contact deleted successfully.");
                return true;
            } else {
                System.out.println("No contact found with the given ID.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Failed to delete contact: " + e.getMessage());
            return false;
        }
    }

    public void createContact(Contacts contact) {
    String sql = "INSERT INTO contacts (full_name, phone_number, email_address, id_number, date_of_birth, gender, county, organization_name) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, contact.getFullName());
        stmt.setString(2, contact.getPhoneNumber());
        stmt.setString(3, contact.getEmailAddress());
        stmt.setString(4, contact.getIdNumber());
        stmt.setDate(5, new java.sql.Date(contact.getDateOfBirth().getTime()));
        stmt.setString(6, contact.getGender());
        stmt.setString(7, contact.getCounty());
        stmt.setString(8, contact.getOrganizationName());

        stmt.executeUpdate();
    } catch (SQLException e) {
       System.err.println("error" + e.getMessage()); 
    }
    }
}


    


