/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/contact_registry_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Change this if your MySQL username is different
    private static final String PASSWORD = "Perpe2005."; // Put your actual password here

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.err.println("MySQL JBCD Driver not found" + e.getMessage());
            
            
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
