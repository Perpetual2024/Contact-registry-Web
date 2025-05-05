/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.servlet;

import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author perpetual-akinyi
 */

@WebServlet("/edit-contact")
public class ContactEditServlet extends HttpServlet {
    private final ContactDao dao = new ContactDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Contacts contact = dao.getContactById(id);
            request.setAttribute("contact", contact);
            request.getRequestDispatcher("/contact-edit.jsp").forward(request, response);
        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            response.sendRedirect("error.jsp");
        }
    }
}
