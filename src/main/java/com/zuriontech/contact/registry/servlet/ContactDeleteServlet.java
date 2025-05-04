/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.servlet;

import com.zuriontech.contact.registry.dao.ContactDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author perpetual-akinyi
 */
@WebServlet("/delete-contact")
public class ContactDeleteServlet extends HttpServlet {
    private final ContactDao dao = new ContactDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int contactId = Integer.parseInt(request.getParameter("id"));
        try {
            dao.deleteContact(contactId);  // Delete contact from the database
        } catch (SQLException ex) {
            Logger.getLogger(ContactDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("contacts");  // Redirect to the list of contacts
    }
}


