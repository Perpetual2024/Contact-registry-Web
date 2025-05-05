<%@page import="com.zuriontech.contact.registry.model.Contacts" %>
<%
    Contacts contact = (Contacts) request.getAttribute("contact");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Contact</title>
</head>
<body>
    <h2>Are you sure you want to delete this contact?</h2>

    <ul>
        <li>Name: <%= contact.getFullName() %></li>
        <li>Phone: <%= contact.getPhoneNumber() %></li>
        <li>Email: <%= contact.getEmailAddress() %></li>
    </ul>

    <form method="post" action="delete-contact">
        <input type="hidden" name="id" value="<%= contact.getId() %>">
        <button type="submit">Yes, Delete</button>
        <a href="contacts">Cancel</a>
    </form>
</body>
</html>
