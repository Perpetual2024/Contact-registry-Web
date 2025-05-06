/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zuriontech.contact.registry.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuriontech.contact.registry.dao.ContactDao;
import com.zuriontech.contact.registry.model.Contacts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contacts/by-county")
public class ContactsByCountyServlet extends HttpServlet {

    private final ContactDao dao = new ContactDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String county = request.getParameter("county");

        if (county == null || county.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing 'county' query parameter");
            return;
        }

        try {
            List<Contacts> contacts = dao.getContactsByCounty(county);

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(contacts);

            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();

        } catch (SQLException e) {
            throw new ServletException("Failed to retrieve contacts by county", e);
        }
    }
}
