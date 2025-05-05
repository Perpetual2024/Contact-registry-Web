<%-- 
    Document   : contact-list
    Created on : May 2, 2025, 4:46:35 PM
    Author     : perpetual-akinyi
--%>

<%@page import="com.zuriontech.contact.registry.model.Contacts"%>
<%@page import="java.util.List"%>
<%@page import="com.zuriontech.contact.registry.model.Contacts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact List</title>
    </head>
    <body>
        <h1>Available Contacts</h1>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>ID</th>
                <th>DOB</th>
                <th>Gender</th>
                <th>County</th>
                <th>Organization</th>
            </tr>
            <%
                List<Contacts> contacts = (List<Contacts>) request.getAttribute("contacts");
                for (Contacts c: contacts){
                %>
                }
            <tr>
                <td><%= c.getFullName()%></td>
                <td><%= c.getPhoneNumber()%></td>
                <td><%= c.getEmailAddress()%></td>
                <td><%= c.getIdNumber()%></td>
                <td><%= c.getDateOfBirth()%></td>
                <td><%= c.getGender()%></td>
                <td><%= c.getCounty()%></td>
                <td><%= c.getOrganizationName()%></td>
                <td>
                    <a href="edit-contact?id=<%= c.getIdNumber() %>">Edit</a>
                </td>
            </tr>
            <% 
             }
            
            %>
            
            
        </table>
        
    </body>
</html>
