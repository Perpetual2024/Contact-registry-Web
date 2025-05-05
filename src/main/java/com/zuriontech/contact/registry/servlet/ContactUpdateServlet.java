/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.servlet;

import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet("/update-contact")
public class ContactUpdateServlet extends HttpServlet {
    private final ContactDao dao = new ContactDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Contacts contact = dao.getContactById(id);
            request.setAttribute("contact", contact);
            request.getRequestDispatcher("/contact-edit.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ContactUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String idNumber = request.getParameter("idNumber");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String county = request.getParameter("county");
            String organizationName = request.getParameter("organizationName");
            
            
            if (!phone.matches("\\d{10}")) {
            request.setAttribute("error", "Phone number must be exactly 10 digits.");
            request.getRequestDispatcher("/contact-create.jsp").forward(request, response);
            return;
            }


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = sdf.parse(dob);

            Contacts updatedContact = new Contacts(id, name, phone, email, idNumber, dateOfBirth, gender, county, organizationName);
            dao.updateContact(updatedContact);

            response.sendRedirect("contacts");
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ContactUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
        }
    }
}
