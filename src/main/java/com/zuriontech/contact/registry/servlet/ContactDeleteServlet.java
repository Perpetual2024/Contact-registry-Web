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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int contactId = Integer.parseInt(request.getParameter("id"));
            var contact = dao.getContactById(contactId); // Get contact details
            request.setAttribute("contact", contact);
            request.getRequestDispatcher("/contact-delete.jsp").forward(request, response); // Show confirmation page
        } catch (SQLException | NumberFormatException e) {
            Logger.getLogger(ContactDeleteServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int contactId = Integer.parseInt(request.getParameter("id"));
            dao.deleteContact(contactId);
            response.sendRedirect("contacts");
        } catch (SQLException | NumberFormatException e) {
            Logger.getLogger(ContactDeleteServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("error.jsp");
        }
    }
}



