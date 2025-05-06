package com.zuriontech.contact.registry.servlet;

import com.google.gson.Gson;
import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contacts")
public class ContactsListServlet extends HttpServlet {

    private final ContactDao dao = new ContactDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Contacts> contactList = dao.getAllContacts();

            // Check if client wants JSON
            String acceptHeader = request.getHeader("Accept");
            String formatParam = request.getParameter("format");

            boolean wantsJson = (acceptHeader != null && acceptHeader.contains("application/json")) ||
                                "json".equalsIgnoreCase(formatParam);

            if (wantsJson) {
                response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); // for React
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                Gson gson = new Gson();
                String json = gson.toJson(contactList);
                response.getWriter().write(json);
            } else {
                // Default to JSP
                request.setAttribute("contacts", contactList);
                request.getRequestDispatcher("/contact-list.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve contacts", e);
        }
    }
}
