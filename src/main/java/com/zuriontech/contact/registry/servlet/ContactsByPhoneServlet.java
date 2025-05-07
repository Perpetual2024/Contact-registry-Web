package com.zuriontech.contact.registry.servlet;

import com.google.gson.Gson;
import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/contacts/by-phone")
public class ContactsByPhoneServlet extends HttpServlet {

    private final ContactDao dao = new ContactDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String phoneNumber = request.getParameter("phoneNumber");

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing 'phoneNumber' query parameter");
            return;
        }

        try {
            Contacts contact = dao.getContactByPhoneNumber(phoneNumber);

            if (contact == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Contact not found.");
                return;
            }

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Gson gson = new Gson();
            String json = gson.toJson(contact);
            response.getWriter().write(json);

        } catch (SQLException e) {
            throw new ServletException("Failed to retrieve contact by phone", e);
        }
    }
}
