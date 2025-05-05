 # Contact Registry Web App

A Java-based web application that allows users to perform CRUD (Create, Read, Update, Delete) operations on a contact list. Built as part of a technical test for Zurion Technologies Ltd.
 ## 📌 Overview

The Contact Registry Web App enables users to manage personal and organizational contact information. The app includes functionality for adding, viewing, editing, and deleting contacts through a JSP-based UI and REST endpoints.
## ⚙️ Technologies Used

    Java Servlet API

    JSP (JavaServer Pages)

    JDBC

    Apache Tomcat

    Maven

    MySQL (for database persistence)

    NetBeans IDE (development environment)

## 🚀 Features

    View all contacts

    Add new contact

    Edit existing contact

    Delete contact

    Form validations (e.g., only numeric input for phone number)

    Organized MVC structure

    REST and SOAP-ready backend structure

## 🛠️ Setup Instructions

    Clone the Repository:

    git clone https://github.com/yourusername/contact-registry.git
    cd contact-registry

    Import Project into NetBeans:

        Open NetBeans.

        Go to File > Open Project and select the cloned folder.

    Set Up the Database:

        Create a MySQL database named contact_registry.

        Run the SQL script provided in db/schema.sql (if available) or manually create the table structure used by the DAO layer.

    Configure Database Connection:

        Edit your ContactDao.java to match your local DB credentials.

    Run the App:

        Deploy using Apache Tomcat (via NetBeans).

        Visit http://localhost:8080/contact-registry/

## 📂 Project Structure

src/
└── com.zuriontech.contact.registry
    ├── dao
    ├── model
    ├── servlet
    └── webapp

## 🧪 Testing

    Manual testing was done through browser interactions.

    All CRUD operations were verified.

    Form validations for phone number and email were added using HTML5 input attributes.

# 📝 AUTHOR

Perpetual Akinyi
Email: [akinyiperpetual2@gmail.com]
GitHub: [https://github.com/Perpetual2024]
