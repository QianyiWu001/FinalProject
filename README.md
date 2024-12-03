# Student Management System
## 1. Project Overview
    This is a student management system based on Java Swing and MySQL, supporting the following features:
    (1) User login validation (Admin/Student roles).
    (2) CRUD operations (Create, Read, Update, Delete) focused on courses and students.
    (3) Sorting and refreshing of displayed data
    (4) Database operations (query, insert, etc.)
## 2. Requirements
    **Java Version:** JDK 23
    **IDE:** Recommended VSCode or IntelliJ IDEA
    **MySQL:** Version 5.7 or above
    **JDBC Driver:** Included as mysql-connector-j-9.1.0.jar
## 3. Database Configuration
    (1) Import the SQL file "dump-NewStuManagement-202411291248.sql" to your MySQL database.
    (2) Modify the database connection settings:
        Database connection settings can be found in the **src/DatabaseUtilities.ConnectDB** class.
        Replace the following placeholders with your own database information:
        ```java
        private static final String dbURL = "your_database_url";
        private static final String dbUser = "your_database_username";
        private static final String dbPassword = "your_database_password";
        ```
## 4. Running the Application
    (1) Open the project in your IDE.
    (2) Ensure lib/mysql-connector-j-9.1.0.jar is added to the project's classpath.
    (3) Compile and run the Main.java file.