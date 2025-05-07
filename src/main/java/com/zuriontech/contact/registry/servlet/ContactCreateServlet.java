package com.zuriontech.contact.registry.servlet;

import com.google.gson.Gson;
import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/create-contact")
public class ContactCreateServlet extends HttpServlet {

    private final ContactDao dao = new ContactDao();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Enable CORS
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Parse JSON request body
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }

        Contacts contact = gson.fromJson(jsonBuilder.toString(), Contacts.class);

        // Validate input (basic examples)
        if (!contact.getPhoneNumber().matches("\\d{10}")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Phone number must be exactly 10 digits.\"}");
            return;
        }

        if (!contact.getEmailAddress().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid email format.\"}");
            return;
        }

        if (!contact.getIdNumber().matches("\\d+")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"ID number must contain only digits.\"}");
            return;
        }

        dao.createContact(contact);

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Contact created successfully.\"}");
    }

    // Handle preflight CORS request (optional but recommended)
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
