<%-- 
    Document   : error.jsp
    Created on : May 4, 2025, 10:38:53 PM
    Author     : perpetual-akinyi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 100px;
            background-color: #f8d7da;
            color: #721c24;
        }
        .error-box {
            border: 1px solid #f5c6cb;
            padding: 20px;
            background-color: #f8d7da;
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="error-box">
        <h2>Oops! Something went wrong.</h2>
        <p>An error occurred while processing your request.</p>
        <p><strong><%= exception != null ? exception.getMessage() : "Unknown error" %></strong></p>
        <a href="contacts">Back to Contact List</a>
    </div>
</body>
</html>
