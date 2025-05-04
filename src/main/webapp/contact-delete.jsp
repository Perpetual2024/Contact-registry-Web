<%-- 
    Document   : contact-delete
    Created on : May 4, 2025, 8:59:44 PM
    Author     : perpetual-akinyi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String contactId = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm Delete</title>
</head>
<body>
    <h2>Are you sure you want to delete this contact?</h2>

    <form action="delete-contact" method="post">
        <input type="hidden" name="id" value="<%= contactId %>" />
        <button type="submit">Yes, Delete</button>
        <a href="contacts">Cancel</a>
    </form>
</body>
</html>
