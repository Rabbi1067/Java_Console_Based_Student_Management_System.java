import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    static Scanner input=new Scanner(System.in);
    public static void main(String[] args) {

        while(true) {
            System.out.println("====Officer Menu====");
            System.out.println("1.Set username & Password");
            System.out.println("2.Login");
            System.out.println("3. Exit");
            System.out.print("Choice Option: ");
            String  choice =input.nextLine();
            //input.nextLine();
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
                    System.out.println("Exiting .....");
                    return;
                }
                default:{
                    System.out.println("Invalid Choice!!");
                }
            }
        }
    }
    public static void registerOfficer() {
        System.out.println("=== Officer Registration ===");

        System.out.print("Enter username (letters only): ");
        String username = input.nextLine().trim();

        if (!Filehandler.isvalidUsername(username)) {
            System.out.println("Invalid username. Only letters are allowed.");
            return;
        }

        System.out.print("Enter password (min 6 characters): ");
        String password = input.nextLine().trim();

        if (!Filehandler.idVlidPassword(password)) {
            System.out.println("Password must be at least 6 characters long.");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile("officers.txt", "rw")) {
            raf.setLength(0);
            raf.writeBytes(username + "," + password + "\n");
            System.out.println("Officer registered (and data overwritten) successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to officer file ");
        }
    }

    public static void loginOfficer() {
        System.out.println("==== Officer Login ====");
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();

        if (Filehandler.validateOfficer(username,password)) {
            System.out.println("Login successful!!!");
            showMenu();

        } else {
            System.out.println("Invalid. Try again!!");
        }
    }
    public static void showMenu(){
        while(true){
        System.out.println("====MENU====");
        System.out.println("1.Add Student");
        System.out.println("2. view All Students");
        System.out.println("3. Search Student ID");
        System.out.println("4. Assign Course");
        System.out.println("5.View course by Student ID:");
        System.out.println("0. Exit");

        System.out.print("Choice One: ");
        String  choice =input.nextLine();
        //input.nextLine();

        switch(choice){
            case "1":{
                Filehandler.addStudent();
                break;
            }
            case "2":{
                Filehandler.viewAllStudents();
                break;
            }
            case "3":{
                Filehandler.searchStudentById();
                break;
            }
            case "4":{
                Filehandler.assignCourse();
                break;
            }
            case "5":{
                Filehandler.viewCourseById();
                break;
            }
            case "0":{
                System.out.println("END!!");
                System.exit(0);
            }
            default:{
                System.out.println("Invalid Choice!! Try again..");
            }
        }

        }

    }

}