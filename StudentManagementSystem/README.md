# Student Management System 

This project is a simple console-based Student Management System implemented in
Java. It allows users to perform various operations like adding a new student,
updating student information, searching for a student, displaying all students,
and removing a student. The student data is stored in a MySQL database using
JDBC.

## Features

- Add a new student with details like name, roll number, and grade.
- Update existing student information (name and grade).
- Search for a student based on their roll number.
- Display all students stored in the database.
- Remove a student from the database based on their roll number.
- Proper input validation to ensure correct data entry.
- Uses a MySQL database to store student information.

## Prerequisites

Before running the application, make sure you have the following installed:

- Java Development Kit (JDK)
- MySQL Database Server
- MySQL Connector/J (JDBC driver)

## Getting Started

1. Clone the repository to your local machine:

2. Create a MySQL database named `codsoft_db` using your preferred MySQL client
   (e.g., MySQL Workbench).

3. Update the `StudentManagementSystem` class constructor with your MySQL
   connection details (URL, username, and password).

4. Make sure to have the MySQL Connector/J (JDBC driver) in your project's
   classpath.

5. Compile the Java files:

   ```
   javac Student.java StudentManagementSystem.java
   ```

6. Run the application:

   ```
   java StudentManagementSystem
   ```

## How to Use

Upon running the application, you will see a menu with options to perform
various operations. Follow the on-screen instructions to interact with the
system.

- To add a new student, select option `1` and enter the student's details when
  prompted.
- To remove a student, select option `2` and provide the student's roll number.
- To update a student's information, select option `3`, search for the student
  by their roll number, and provide the updated details.
- To search for a student, select option `4` and enter the student's roll
  number.
- To display all students, select option `5`.
- To exit the application, select option `6`.

## Acknowledgments

This project is a simple demonstration of a Student Management System using Java
and MySQL. It can be extended and enhanced with additional features like error
handling, sorting, or implementing a GUI using libraries like Swing or JavaFX.

Feel free to use and modify this project for your needs. If you encounter any
issues or have suggestions for improvements, please feel free to submit an issue
or a pull request. Happy coding!
