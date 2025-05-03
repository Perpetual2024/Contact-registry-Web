<%-- 
    Document   : contact-create
    Created on : May 4, 2025, 1:20:55 AM
    Author     : perpetual-akinyi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <h1>Create your contact</h1>
         <form action="create-contact" method="post">
                    Name: <input type="text" name="name"><br>
                    Phone: <input type="text" name="phone"><br>
                    Email: <input type="email" name="email"><br>
                    ID Number: <input type="text" name="idNumber"><br>
                    Date of Birth: <input type="date" name="dob"><br>
                    Gender: <input type="text" name="gender"><br>
                    County: <input type="text" name="county"><br>
                    Organization: <input type="text" name="organizationName"><br>
                    <button type="submit">Create Contact</button>
        </form>

    </body>
</html>
