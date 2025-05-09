package com.zuriontech.contact.registry.servlet;

import com.google.gson.Gson;
import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/contacts/*")
public class GetContactByIdServlet extends HttpServlet {

    private final ContactDao dao = new ContactDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Extract the ID from the URL, e.g., /contacts/3 → "3"
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing contact ID in path.");
            return;
        }

        try {
            int contactId = Integer.parseInt(pathInfo.substring(1));
            Contacts contact = dao.getContactById(contactId);

            if (contact == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Contact not found.");
                return;
            }

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String json = new Gson().toJson(contact);
            response.getWriter().write(json);

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid contact ID format.");
        } catch (SQLException e) {
            throw new ServletException("Database error while retrieving contact.", e);
        }
    }
}
