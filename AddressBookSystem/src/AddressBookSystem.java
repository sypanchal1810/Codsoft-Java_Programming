import java.sql.*;
import java.util.*;

// Contact Class which holds the 'Contact' object
class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "\nContact" + " {" +
                "\n  Name: '" + name + "\'" +
                ", \n  Phone No.: " + phoneNumber +
                ", \n  Email: '" + emailAddress + "\'" +
                "\n}";
    }
}

// AddressBookSystem Class => Allows the user to interact with the application
public class AddressBookSystem {
    private List<Contact> contacts;
    private Connection connection;

    public AddressBookSystem() {
        contacts = new ArrayList<>();
        connection = getConnection();
    }

    // Connect with MySQL using JDBC driver
    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/codsoft_db";
            String username = "root";
            String password = "admin1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Validate phone number (10 digits)
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    // Check if the phone number exists in the database
    private boolean isPhoneNumberExists(String phoneNumber) {
        String query = "SELECT COUNT(*) FROM contacts WHERE phone_number = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Validate email address (standard email format)
    private boolean isValidEmailAddress(String emailAddress) {
        String emailRegex = "^[a-z0-9][a-z0-9_.-]*@[a-z0-9.-]+\\.[a-z]{2,6}$";
        return emailAddress.matches(emailRegex);
    }

    // Check if the email address exists in the database
    private boolean isEmailExists(String emailAddress) {
        String query = "SELECT COUNT(*) FROM contacts WHERE email_address = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, emailAddress);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean runValidators(Contact contact) {
        // Validate phone number & email address
        if (!isValidPhoneNumber(contact.getPhoneNumber())) {
            System.out.println("Invalid phone number. Phone number must be a 10-digit number.");
            return false;
        }
        if (!isValidEmailAddress(contact.getEmailAddress())) {
            System.out.println("Invalid email address. Please enter a valid email address.");
            return false;
        }

        // Check if the phone number or email address already exists in the database
        if (isPhoneNumberExists(contact.getPhoneNumber())) {
            System.out.println("Contact not added. Phone number already exists in the database.");
            return false;
        }
        if (isEmailExists(contact.getEmailAddress())) {
            System.out.println("Contact not added. Email address already exists in the database.");
            return false;
        }

        return true;
    }

    // Add the contact into Database
    public void addContact(Contact contact) {
        String query = "INSERT INTO contacts (name, phone_number, email_address) VALUES (?, ?, ?)";
        try {
            if (!runValidators(contact))
                return;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getPhoneNumber());
            statement.setString(3, contact.getEmailAddress());
            statement.executeUpdate();

            System.out.println("Contact added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update the contact details
    public void updateContact(Contact oldContact, Contact newContact) {
        String query = "UPDATE contacts SET name = ?, phone_number = ?, email_address = ? WHERE phone_number = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newContact.getName());
            statement.setString(2, newContact.getPhoneNumber());
            statement.setString(3, newContact.getEmailAddress());
            statement.setString(4, oldContact.getPhoneNumber());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contact updated successfully.");
            } else {
                System.out.println("Contact not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete the contact record from Database based on name
    public void removeContact(Contact contact) {
        String query = "DELETE FROM contacts WHERE phone_number = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contact.getPhoneNumber());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contact removed successfully.");
            } else {
                System.out.println("Contact not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search the contact record from Database based on name
    public Contact searchContact(String phone) {
        String query = "SELECT * FROM contacts WHERE phone_number = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, phone);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phone_number");
                String emailAddress = resultSet.getString("email_address");
                return new Contact(name, phoneNumber, emailAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Display all the contacts
    public void displayAllContacts() {
        String query = "SELECT * FROM contacts";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No contacts found.");
            } else {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String phoneNumber = resultSet.getString("phone_number");
                    String emailAddress = resultSet.getString("email_address");
                    Contact contact = new Contact(name, phoneNumber, emailAddress);
                    contacts.add(contact);
                    System.out.println(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("\t\t +-------------------------------+ \t\t");
            System.out.println("\t\t |     Address Book System       | \t\t");
            System.out.println("\t\t +-------------------------------+ \t\t");
            System.out.println();
            System.out.println("1. Add New Contact Details");
            System.out.println("2. Remove Contact from Database");
            System.out.println("3. Update Contact Details");
            System.out.println("4. Search Contact in the Database");
            System.out.println("5. Display All The Contacts ");
            System.out.println("6. EXIT ");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();
                    Contact contact = new Contact(name, phoneNumber, emailAddress);
                    addressBookSystem.addContact(contact);
                    break;

                case 2:
                    System.out.print("Enter contact's phone_number to remove: ");
                    String removePhone = scanner.nextLine();
                    Contact removeContact = addressBookSystem.searchContact(removePhone);
                    if (removeContact != null) {
                        System.out.println("Contact found: " + removeContact);
                        addressBookSystem.removeContact(removeContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter contact's phone_number to update: ");
                    String updatePhone = scanner.nextLine();
                    Contact updateContact = addressBookSystem.searchContact(updatePhone);
                    if (updateContact != null) {
                        System.out.println("Contact found: " + updateContact);
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new phone number: ");
                        String newPhoneNumber = scanner.nextLine();
                        System.out.print("Enter new email address: ");
                        String newEmailAddress = scanner.nextLine();
                        Contact newContact = new Contact(newName, newPhoneNumber, newEmailAddress);
                        addressBookSystem.updateContact(updateContact, newContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter contact's phone_number to search: ");
                    String searchPhone = scanner.nextLine();
                    Contact searchContact = addressBookSystem.searchContact(searchPhone);
                    if (searchContact != null) {
                        System.out.println("Contact found: " + searchContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 5:
                    addressBookSystem.displayAllContacts();
                    break;

                case 6:
                    // Close the database connection
                    addressBookSystem.closeConnection();
                    System.out.println("Closing... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
}
