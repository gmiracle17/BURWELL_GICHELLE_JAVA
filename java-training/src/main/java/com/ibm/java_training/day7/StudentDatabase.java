package com.ibm.java_training.day7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Management system for managing student records in a PostgreSQL database via a console interface.
 */
public class StudentDatabase {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection getConnection() throws SQLException { return DriverManager.getConnection(JDBC_URL, USER, PASSWORD); }
    public record StudentRecord(int studentId, String email, String firstName, String lastName, Timestamp dateAdded, Timestamp dateUpdated) {}
    
    /**
     * Checks if an email address is already registered in the database.
     *
     * @param email	The email to check.
     * @return true if the email exists, false otherwise.
     */
    public static boolean emailExists(String email) {
        String sql = "SELECT 1 FROM student WHERE email = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Inserts a new student record into the database.
     *
     * @param email		The student's email.
     * @param password	The student's password.
     * @param firstName	The student's first name.
     * @param lastName	The student's last name.
     */
    public static void addStudent(String email, String password, String firstName, String lastName) {
        String sql = """
                INSERT INTO student
                (email, password, firstname, lastname, dateadded, dateupdated)
                VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
                RETURNING studentid
                """;
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Student added successfully. Assigned Student ID: " + rs.getInt("studentid"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Searches for students by matching a keyword against email, first name, or last name.
     *
     * @param term 	The search term keyword.
     * @return A list of matching student records.
     */
    public static List<StudentRecord> searchStudents(String term) {
        String sql = """
                SELECT studentid, email, firstname, lastname, dateadded, dateupdated 
                FROM student 
                WHERE email ILIKE ? OR firstname ILIKE ? OR lastname ILIKE ?
                """;
        List<StudentRecord> rawResults = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            String likeTerm = "%" + term + "%";
            ps.setString(1, likeTerm);
            ps.setString(2, likeTerm);
            ps.setString(3, likeTerm);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rawResults.add(new StudentRecord(
                            rs.getInt("studentid"),
                            rs.getString("email"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getTimestamp("dateadded"),
                            rs.getTimestamp("dateupdated")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rawResults.stream().toList();
    }

    /**
     * Retrieves and prints details for all registered students.
     */
    public static void viewAllStudents() {
        String sql = "SELECT studentid, email, firstname, lastname, dateadded, dateupdated FROM student";
        List<StudentRecord> students = new ArrayList<>();
        
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                students.add(new StudentRecord(
                        rs.getInt("studentid"),
                        rs.getString("email"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getTimestamp("dateadded"),
                        rs.getTimestamp("dateupdated")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (students.isEmpty()) {
            System.out.println("No Students in Directory");
        } else {
            students.stream().forEach(student -> {
                System.out.println("----------------------------");
                System.out.println("Student ID : " + student.studentId());
                System.out.println("Email      : " + student.email());
                System.out.println("First Name : " + student.firstName());
                System.out.println("Last Name  : " + student.lastName());
            });
        }
    }

    /**
     * Verifies if the provided password matches the student ID.
     *
     * @param studentId	The unique student identifier.
     * @param password	The password string to test.
     * @return true if credentials match, false otherwise.
     */
    public static boolean verifyPassword(int studentId, String password) {
        String sql = "SELECT password FROM student WHERE studentid = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getString("password").equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates a student's password and sets the update timestamp to current.
     *
     * @param studentId		The targeted student identifier.
     * @param newPassword	The new password to save.
     */
    public static void updatePassword(int studentId, String newPassword) {
        String sql = "UPDATE student SET password = ?, dateupdated = CURRENT_TIMESTAMP WHERE studentid = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, newPassword);
            ps.setInt(2, studentId);
            int rows = ps.executeUpdate();
            if (rows > 0) { 
                System.out.println("Password updated successfully."); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a student record using their primary ID.
     *
     * @param studentId	The ID of the student to delete.
     */
    public static void deleteStudent(int studentId) {
        String sql = "DELETE FROM student WHERE studentid = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, studentId);
            int rows = ps.executeUpdate();
            if (rows > 0) { 
                System.out.println("Student deleted successfully."); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prompts the user for an email and validates that it contains an '@' symbol and is unique.
     *
     * @param scanner	The active console input reader.
     * @param prompt	The message to display to the user.
     * @return A verified, unique email address string.
     */
    private static String verifyEmail(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.contains("@")) {
                System.out.println("Invalid Input");
                continue;
            }
            if (emailExists(input)) {
                System.out.println("Email already registered. Please use a different email.");
                continue;
            }
            return input;
        }
    }

    /**
     * Prompts the user for a numeric menu choice within specified bounds.
     *
     * @param scanner	The active console input reader.
     * @param prompt	The text instructions to show.
     * @param min		The minimum acceptable integer.
     * @param max		The maximum acceptable integer.
     * @return A verified menu option integer.
     */
    private static int verifyChoice(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String rawInput = scanner.nextLine().trim();
            try {
                int parsed = Integer.parseInt(rawInput);
                if (parsed >= min && parsed <= max) {
                    return parsed;
                }
            } catch (NumberFormatException e) {}
            System.out.println("Invalid Input");
        }
    }

    /**
     * Prompts the user to select an entry ID from a list of filtered student matches.
     *
     * @param scanner	The active console input reader.
     * @param prompt  	The input instructions display text.
     * @param matches 	The collection of valid student matches to choose from.
     * @return A confirmed matching student ID integer.
     */
    private static int verifyStudentIDChoice(Scanner scanner, String prompt, List<StudentRecord> matches) {
        while (true) {
            System.out.print(prompt);
            String rawInput = scanner.nextLine().trim();
            try {
                int parsedId = Integer.parseInt(rawInput);
                boolean exists = matches.stream().anyMatch(s -> s.studentId() == parsedId);
                if (exists) { return parsedId; }
            } catch (NumberFormatException e) {}
            System.out.println("Invalid Input");
        }
    }

    /**
     * Orchestrates interactive inputs to add a new student record.
     *
     * @param scanner	The active console input reader.
     */
    private static void handleAddStudent(Scanner scanner) {
        String email = verifyEmail(scanner, "Enter Email: ");
        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = scanner.nextLine();
            System.out.print("Confirm Password: ");
            String confirmPassword = scanner.nextLine();
            
            if (password.equals(confirmPassword)) { break; }
            System.out.println("Passwords do not match. Please try again.");
        }
        
        System.out.print("Enter First Name: "); 
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: "); 
        String lastName = scanner.nextLine();
        
        addStudent(email, password, firstName, lastName);
    }

    /**
     * Orchestrates steps to search, select, and view an individual student profile.
     *
     * @param scanner	The active console input reader.
     */
    private static void handleViewOneStudent(Scanner scanner) {
        System.out.print("Search by Email, First Name, or Last Name: "); 
        String searchTerm = scanner.nextLine();
        List<StudentRecord> matches = searchStudents(searchTerm);
        if (matches.isEmpty()) {
            System.out.println("No student found");
            return;
        }
        System.out.println("\nMatching students:");
        matches.stream().forEach(student -> 
            System.out.println("[" + student.studentId() + "] " + student.firstName() + " " + s.lastName() + " - " + s.email())
        );
        
        int chosenId = verifyStudentIDChoice(scanner, "Enter Student ID to view: ", matches);
        
        StudentRecord chosen = matches.stream()
                .filter(s -> s.studentId() == chosenId)
                .findFirst()
                .orElse(null);
        
        if (chosen != null) {
            System.out.println("\nStudent ID   : " + chosen.studentId());
            System.out.println("Email        : " + chosen.email());
            System.out.println("First Name   : " + chosen.firstName());
            System.out.println("Last Name    : " + chosen.lastName());
            System.out.println("Date Added   : " + chosen.dateAdded());
            System.out.println("Date Updated : " + chosen.dateUpdated());
        }
    }

    /**
     * Orchestrates security steps and workflows required to change a student password.
     *
     * @param scanner	The active console input reader.
     */
    private static void handleUpdatePassword(Scanner scanner) {
        System.out.print("Enter Email: "); 
        String updateEmailInput = scanner.nextLine().trim();
        
        List<StudentRecord> updateMatches = searchStudents(updateEmailInput);
        StudentRecord studentToUpdate = updateMatches.stream()
                .filter(student -> student.email().equalsIgnoreCase(updateEmailInput))
                .findFirst()
                .orElse(null);
        
        if (studentToUpdate == null) {
            System.out.println("No student found with that email.");
            return;
        }
        
        System.out.print("Enter Old Password: "); 
        String oldPasswordInput = scanner.nextLine();
        if (!verifyPassword(studentToUpdate.studentId(), oldPasswordInput)) {
            System.out.println("Incorrect old password. Update cancelled.");
            return;
        }
        
        String newPassword;
        while (true) {
            System.out.print("Enter New Password: "); 
            newPassword = scanner.nextLine();
            
            if (newPassword.equals(oldPasswordInput)) {
                System.out.println("You entered your old password. Please type a different new password.");
                continue;
            }
            
            System.out.print("Confirm New Password: "); 
            String confirmPassword = scanner.nextLine();
            
            if (!newPassword.equals(confirmPassword)) {
                System.out.println("New passwords do not match. Please try again.");
                continue;
            }
            break;
        }
        
        updatePassword(studentToUpdate.studentId(), newPassword);
    }

    /**
     * Orchestrates interactive inputs and verification needed to delete a student record.
     *
     * @param scanner The active console input reader.
     */
    private static void handleDeleteStudent(Scanner scanner) {
        System.out.print("Enter Email: "); 
        String deleteEmailInput = scanner.nextLine().trim();
        
        List<StudentRecord> deleteMatches = searchStudents(deleteEmailInput);
        StudentRecord studentToDelete = deleteMatches.stream()
                .filter(student -> student.email().equalsIgnoreCase(deleteEmailInput))
                .findFirst()
                .orElse(null);
        
        if (studentToDelete == null) {
            System.out.println("No student found with that email.");
            return;
        }
        
        System.out.print("Enter Password to confirm deletion: "); 
        String delPassword = scanner.nextLine();
        if (!verifyPassword(studentToDelete.studentId(), delPassword)) {
            System.out.println("Incorrect password. Deletion cancelled.");
            return;
        }
        deleteStudent(studentToDelete.studentId());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nSTUDENT MENU");
            System.out.println("[1] Add");
            System.out.println("[2] View One Student");
            System.out.println("[3] View All Students");
            System.out.println("[4] Update Password");
            System.out.println("[5] Delete");
            System.out.println("[6] Quit");

            choice = verifyChoice(scanner, "\nEnter choice: ", 1, 6);

            switch (choice) {
                case 1 -> handleAddStudent(scanner);
                case 2 -> handleViewOneStudent(scanner);
                case 3 -> viewAllStudents();
                case 4 -> handleUpdatePassword(scanner);
                case 5 -> handleDeleteStudent(scanner);
                case 6 -> System.out.println("Thank you for using Student Menu. Goodbye!");
                default -> System.out.println("Invalid Selection");
            }
            
        } while (choice != 6);
        
        scanner.close();
    }
}