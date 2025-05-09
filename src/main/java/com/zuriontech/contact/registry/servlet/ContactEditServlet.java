package com.zuriontech.contact.registry.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/edit-contact")
public class ContactEditServlet extends HttpServlet {
    private final ContactDao dao = new ContactDao();
    private final Gson gson = new Gson();

    // Handles GET for editing in JSP
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

    // Handles form submission (JSP)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                request.getRequestDispatcher("/contact-edit.jsp").forward(request, response);
                return;
            }

            if (!idNumber.matches("\\d+")) {
                request.setAttribute("error", "ID number must contain only digits.");
                request.getRequestDispatcher("/contact-edit.jsp").forward(request, response);
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                request.setAttribute("error", "Invalid email format.");
                request.getRequestDispatcher("/contact-edit.jsp").forward(request, response);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = sdf.parse(dob);

            Contacts contact = new Contacts(id, name, phone, email, idNumber, dateOfBirth, gender, county, organizationName);
            dao.updateContact(contact);
            response.sendRedirect("contacts");

        } catch (ParseException | NumberFormatException e) {
            request.setAttribute("error", "Invalid input.");
            request.getRequestDispatcher("/contact-edit.jsp").forward(request, response);
        }
    }

    // Handles JSON update from API clients like React or Postman
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "PUT, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }

        try {
            Contacts contact = gson.fromJson(jsonBuilder.toString(), Contacts.class);

            if (contact.getId() <= 0) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Invalid or missing contact ID.\"}");
                return;
            }

            boolean success = dao.updateContact(contact);

            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"Contact updated successfully.\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Contact not found or update failed.\"}");
            }

        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid JSON format.\"}");
        }
    }

    // Handles preflight CORS
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "PUT, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
