import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    static Scanner input=new Scanner(System.in);
    public static void main(String[] args) {

        while(true) {
            printHeader("OFFICER MENU");
            System.out.println(" [1] Register Officer (Set Username & Password)");
            System.out.println(" [2] Login");
            System.out.println(" [3] Exit");
            System.out.println("----------------------------------------");
            System.out.print("👉 Enter your choice: ");
            String  choice = input.nextLine();
            switch(choice){
                case "1":{
                    registerOfficer();
                    break;
                }
                case "2":{
                    loginOfficer();
                    break;
                }
                case "3":{
                    System.out.println("\n👋 Exiting... Goodbye!");
                    return;
                }
                default:{
                    System.out.println("⚠️ Invalid Choice! Please try again.");
                }
            }
        }
    }
    public static void registerOfficer() {
        printHeader("OFFICER REGISTRATION");
        System.out.print("👤 Enter username (letters only): ");
        String username = input.nextLine().trim();
        if (!Filehandler.isValidUsername(username)) {
            System.out.println("❌ Invalid username. Only letters are allowed.");
            return;
        }
        System.out.print("🔑 Enter password (min 6 characters): ");
        String password = input.nextLine().trim();
        if (!Filehandler.isValidPassword(password)) {
            System.out.println("❌ Password must be at least 6 characters long.");
            return;
        }
        try (RandomAccessFile raf = new RandomAccessFile("officers.txt", "rw")) {
            raf.setLength(0);
            raf.writeBytes(username + "," + password + "\n");
            System.out.println("✅ Officer registered successfully!");
        } catch (IOException e) {
            System.out.println("⚠️ Error writing to officer file.");
        }
    }

    public static void loginOfficer() {
        printHeader("OFFICER LOGIN");
        System.out.print("👤 Username: ");
        String username = input.nextLine();
        System.out.print("🔑 Password: ");
        String password = input.nextLine();
        if (Filehandler.validateOfficer(username, password)) {
            System.out.println("✅ Login successful!");
            showMenu();
        } else {
            System.out.println("❌ Invalid credentials. Try again!");
        }
    }
    public static void showMenu() {
        while (true) {
            printHeader("MAIN MENU");
            System.out.println(" [1] Add Student");
            System.out.println(" [2] View All Students");
            System.out.println(" [3] Search Student by ID");
            System.out.println(" [4] Assign Course");
            System.out.println(" [5] View Courses by Student ID");
            System.out.println(" [0] Logout / Exit");
            System.out.println("----------------------------------------");
            System.out.print("👉 Enter your choice: ");
            String choice = input.nextLine();
            switch (choice) {
                case "1": Filehandler.addStudent();
                break;
                case "2": Filehandler.viewAllStudents();
                break;
                case "3": Filehandler.searchStudentById();
                break;
                case "4": Filehandler.assignCourse();
                break;
                case "5": Filehandler.viewCourseById();
                break;
                case "0": System.out.println("\n👋 Logging out... Goodbye!");
                System.exit(0);
                default: System.out.println("⚠️ Invalid Choice! Please try again.");
            }
        }
    }
    public static void printHeader(String title) {
        System.out.println("\n========================================");
        System.out.println(" 📌 " + title);
        System.out.println("========================================"); }

}