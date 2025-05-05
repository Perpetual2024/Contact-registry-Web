/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.servlet;

import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author perpetual-akinyi
 */
@WebServlet("/create-contact")
public class ContactCreateServlet extends HttpServlet{
     private final ContactDao dao = new ContactDao();
    @Override
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
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

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("error", "Invalid email format.");
            request.getRequestDispatcher("/contact-create.jsp").forward(request, response);
            return;
        }

        if (!idNumber.matches("\\d+")) {
            request.setAttribute("error", "ID number must contain only digits.");
            request.getRequestDispatcher("/contact-create.jsp").forward(request, response);
            return;
        }
    
    
    
    dao.createContact(new Contacts( name, phone, email, idNumber, dob, gender, county, organizationName));
    
    response.sendRedirect("contacts");
    
    }
    
}

