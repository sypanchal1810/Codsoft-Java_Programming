import java.sql.*;
import java.util.*;

// Student Class which holds the student detail in the object
class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student() {
    }

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "\nStudent" + " {" +
                "\n  Name: '" + name + "\'" +
                ", \n  Roll No.: " + rollNumber +
                ", \n  Grade: '" + grade + "\'" +
                "\n}";
    }
}

// StudentManagementSystem Class which allows the user to interact with the
// application
public class StudentManagementSystem {
    private List<Student> students;
    private Connection connection;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        this.connection = getConnection();
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

    // Add the student into Database
    public void addStudent(Student student) {
        String query = "INSERT INTO students (name, roll_number, grade) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getRollNumber());
            statement.setString(3, student.getGrade());
            statement.executeUpdate();

            System.out.println("\nStudent added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update the student details
    public void updateStudent(Student student) {
        String query = "UPDATE students SET name = ?, grade = ? WHERE roll_number = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setString(2, student.getGrade());
            statement.setInt(3, student.getRollNumber());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nStudent updated successfully.");
            } else {
                System.out.println("\nStudent not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete the student record to MySQL Database based on roll no.
    public void removeStudent(Student student) {
        String query = "DELETE FROM students WHERE roll_number = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, student.getRollNumber());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nStudent removed successfully.");
            } else {
                System.out.println("\nStudent not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search the student record to MySQL Database based on roll no.
    public Student searchStudent(int rollNumber) {
        String query = "SELECT * FROM students WHERE roll_number = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, rollNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String grade = resultSet.getString("grade");
                return new Student(name, rollNumber, grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Display all the students
    public void displayAllStudents() {
        String query = "SELECT * FROM students";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No students found.");
            } else {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int rollNumber = resultSet.getInt("roll_number");
                    String grade = resultSet.getString("grade");
                    Student student = new Student(name, rollNumber, grade);
                    students.add(student);
                    System.out.println(student);
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
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("\t\t +-------------------------------+ \t\t");
            System.out.println("\t\t |   Student Management System   | \t\t");
            System.out.println("\t\t +-------------------------------+ \t\t");
            System.out.println();
            System.out.println("1. Add New Student Details");
            System.out.println("2. Remove Student Record ");
            System.out.println("3. Update Student Details");
            System.out.println("4. Search Student in the Database");
            System.out.println("5. Display All The Students ");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    Student student = new Student(name, rollNumber, grade);
                    sms.addStudent(student);
                    break;

                case 2:
                    System.out.print("Enter roll number of student to remove: ");
                    int removeRollNumber = scanner.nextInt();
                    scanner.nextLine();
                    Student removeStudent = sms.searchStudent(removeRollNumber);
                    if (removeStudent != null) {
                        sms.removeStudent(removeStudent);
                    } else {
                        System.out.println("\nStudent not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter roll number of student to update: ");
                    int updateRollNumber = scanner.nextInt();
                    scanner.nextLine();
                    Student updateStudent = sms.searchStudent(updateRollNumber);
                    if (updateStudent != null) {
                        System.out.println("\nStudent found: " + updateStudent);
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new grade: ");
                        String newGrade = scanner.nextLine();
                        updateStudent.setName(newName);
                        updateStudent.setGrade(newGrade);
                        sms.updateStudent(updateStudent);
                    } else {
                        System.out.println("\nStudent not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter roll number of student to search: ");
                    int searchRollNumber = scanner.nextInt();
                    scanner.nextLine();
                    Student searchStudent = sms.searchStudent(searchRollNumber);
                    if (searchStudent != null) {
                        System.out.println("\nStudent found: " + searchStudent);
                    } else {
                        System.out.println("\nStudent not found.");
                    }
                    break;

                case 5:
                    sms.displayAllStudents();
                    break;

                case 6:
                    // Close the database connection
                    sms.closeConnection();
                    System.out.println("\nClosing... Have a nice Day :)");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
            System.out.println();
        }
    }
}
