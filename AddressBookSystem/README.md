# Address Book System

This is a simple Java console-based application for managing an Address Book
System. It allows users to interact with the system by performing various
operations such as adding new contacts, editing existing contact information,
searching for contacts, displaying all contacts, and exiting the application.
The contact data is stored in a MySQL database.

## Requirements

To run this application, you will need the following:

1. Java Development Kit (JDK) installed on your machine.
2. MySQL database server.
3. MySQL Connector/J JDBC driver.

## Getting Started

1. Clone the repository or download the source code files to your local machine.
2. Import the project into your preferred Java IDE (e.g., IntelliJ, Eclipse).
3. Set up a MySQL database and create a table named `contacts` with columns
   `name`, `phone_number`, and `email_address`. The data types for these columns
   should be suitable for holding the respective contact information.
4. Update the `getConnection()` method in the `AddressBookSystem` class with
   your MySQL database connection details (URL, username, and password).

## How to Use

1. Run the `AddressBookSystem` class in your IDE or compile and run the
   `AddressBookSystem.java` file from the terminal.

2. Once the application starts, you will see a menu with the following options:

   ```
   +-------------------------------+
   |     Address Book System       |
   +-------------------------------+

   1. Add New Contact Details
   2. Remove Contact from Database
   3. Update Contact Details
   4. Search Contact in the Database
   5. Display All The Contacts
   6. EXIT
   ```

3. Choose the desired option by entering the corresponding number:

   - **Option 1**: Add a new contact. Enter the name, phone number, and email
     address for the contact.

   - **Option 2**: Remove a contact from the database. Enter the phone number of
     the contact you want to remove.

   - **Option 3**: Update an existing contact's details. Enter the phone number
     of the contact you want to update and provide the new information.

   - **Option 4**: Search for a contact in the database. Enter the phone number
     of the contact you want to find.

   - **Option 5**: Display all contacts in the database.

   - **Option 6**: Exit the application.

4. Follow the on-screen instructions to perform the desired operations. The
   application will provide feedback on the success or failure of each
   operation.

5. After you are done using the application, choose Option 6 to close the
   database connection and exit the program.

## Input Validation

The application implements input validation to ensure that the required fields
are not left empty and that the contact data is in the correct format. It
validates the phone number to be a 10-digit number and the email address to be
in standard email format.

---

Feel free to explore and extend the application further based on your needs and
preferences. Happy coding!
