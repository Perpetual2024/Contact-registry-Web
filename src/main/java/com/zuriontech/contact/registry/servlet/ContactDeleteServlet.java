package com.zuriontech.contact.registry.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/delete-contact")
public class ContactDeleteServlet extends HttpServlet {
    private final ContactDao dao = new ContactDao();
    private final Gson gson = new Gson();

    // Handle DELETE requests from React or Postman
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Enable CORS
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Read JSON body
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }

        try {
            JsonObject json = gson.fromJson(jsonBuilder.toString(), JsonObject.class);

            if (!json.has("id")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Missing contact ID.\"}");
                return;
            }

            int contactId = json.get("id").getAsInt();

            boolean success = dao.deleteContact(contactId);
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"Contact deleted successfully.\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Contact not found.\"}");
            }

        } catch (Exception e) {
            Logger.getLogger(ContactDeleteServlet.class.getName()).log(Level.SEVERE, null, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Server error while deleting contact.\"}");
        }
    }

    // Optional: Support browser-based delete via JSP (GET confirmation and POST delete)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int contactId = Integer.parseInt(request.getParameter("id"));
            Contacts contact = dao.getContactById(contactId);
            request.setAttribute("contact", contact);
            request.getRequestDispatcher("/contact-delete.jsp").forward(request, response);
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
            response.sendRedirect("contacts?deleted=true");
        } catch (SQLException | NumberFormatException e) {
            Logger.getLogger(ContactDeleteServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("error.jsp");
        }
    }

    // Allow preflight CORS
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
