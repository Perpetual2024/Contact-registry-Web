package com.zuriontech.contact.registry.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/create-contact")
public class ContactCreateServlet extends HttpServlet {

    private final ContactDao dao = new ContactDao();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String contentType = request.getContentType();
        boolean isJson = contentType != null && contentType.contains("application/json");

        try {
            Contacts contact;

            if (isJson) {
                // Enable CORS for React or other frontend
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

                contact = gson.fromJson(jsonBuilder.toString(), Contacts.class);
            } else {
                // Handle form input (from JSP)
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String idNumber = request.getParameter("idNumber");
                String dob = request.getParameter("dob");
                String gender = request.getParameter("gender");
                String county = request.getParameter("county");
                String organizationName = request.getParameter("organizationName");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirth = sdf.parse(dob);

                contact = new Contacts(0, name, phone, email, idNumber, dateOfBirth, gender, county, organizationName);
            }

            // Validation
            if (!contact.getPhoneNumber().matches("\\d{10}")) {
                throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
            }
            if (!contact.getEmailAddress().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new IllegalArgumentException("Invalid email format.");
            }
            if (!contact.getIdNumber().matches("\\d+")) {
                throw new IllegalArgumentException("ID number must contain only digits.");
            }

            dao.createContact(contact);

            if (isJson) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"Contact created successfully.\"}");
            } else {
                response.sendRedirect("contacts");
            }

        } catch (JsonSyntaxException | IOException | IllegalArgumentException | ParseException e) {
            if (isJson) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
            } else {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/contact-create.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
