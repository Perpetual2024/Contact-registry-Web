<%-- 
    Document   : contact-edit
    Created on : May 4, 2025, 8:27:07 PM
    Author     : perpetual-akinyi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.zuriontech.contact.registry.model.Contacts" %>
<%
    Contacts contact = (Contacts) request.getAttribute("contact");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Contact</title>
</head>
<body>
    <h2>Edit Contact</h2>
    <form action="update-contact" method="post">
        <input type="hidden" name="id" value="<%= contact.getId() %>" />

        Name: <input type="text" name="name" value="<%= contact.getFullName() %>" required /><br />
        Phone: <input type="text" name="phone" value="<%= contact.getPhoneNumber() %>" pattern="[0-9]{10}" title="Enter a 10-digit number"required /><br />
        Email: <input type="email" name="email" value="<%= contact.getEmailAddress() %>" required /><br />
        ID Number: <input type="text" name="idNumber" value="<%= contact.getIdNumber() %>" pattern="[0-9]+" title="Numbers only"required /><br />
        DOB: <input type="date" name="dob" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(contact.getDateOfBirth()) %>" required /><br />
        Gender: <input type="text" name="gender" value="<%= contact.getGender() %>" required /><br />
        County: <input type="text" name="county" value="<%= contact.getCounty() %>" required /><br />
        Organization Name: <input type="text" name="organizationName" value="<%= contact.getOrganizationName() %>" /><br />

        <button type="submit">Update</button>
    </form>
    <a href="contacts">Back to Contacts</a>
</body>
</html>
