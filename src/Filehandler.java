import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Filehandler {
    static Scanner input= new Scanner(System.in);
public static void addStudent(){

    try {
        RandomAccessFile raf = new RandomAccessFile("student.txt", "rw");
        System.out.print("Enter student Id: ");
        int id = Integer.parseInt(input.nextLine().trim());

        String line;
        while ((line = raf.readLine()) != null) {
            Student st = Student.fromFileString(line);
            if (st != null && st.getId() == id) {
                System.out.println("A student with the ID already exists");
                return;
            }
        }
        System.out.print("Enter name: ");
        String name = input.nextLine();
        if (!isvalidUsername(name)) {
            System.out.println("Invalid name!! Only letter are allowed");
            return;
        }

        System.out.print("Enter program: ");
        String program = input.nextLine().trim();

        System.out.print("Enter Batch: ");
        String batch = input.nextLine().trim();

        System.out.print("Enter password: ");
        String password = input.nextLine().trim();

        System.out.print("Enter CGPA: ");
        double cgpa = Double.parseDouble(input.nextLine().trim());
        if (cgpa <= 0 || cgpa > 4) {
            System.out.println("Invalid CGPA!!");
            cgpa=0.0;
        }

        Student newstudent = new Student(id, name, program, batch, password, cgpa);
        raf.seek(raf.length());
        raf.writeBytes(newstudent.toFileString() + "\n");
        System.out.println("Student added successfully!");
    }catch(NumberFormatException e){
        System.out.println("Invalid input format!!");
    }catch(IOException e){
        System.out.println("Error writing to student file!!");
    }
}
    public static void viewAllStudents() {
        try  {
            RandomAccessFile raf = new RandomAccessFile("student.txt", "r");
            String line;
            while ((line = raf.readLine()) != null) {
                Student s=Student.fromFileString(line);
                if(s!=null){
                    s.displayInfo();
                }
                else System.out.println("Invalid!!");
            }
            raf.close();
        } catch (IOException e) {
            System.out.println("Error reading student file.");
            e.printStackTrace();
        }catch(NumberFormatException e){
            System.out.println("Invalid input format!!");
            e.printStackTrace();
        }
    }
static void searchStudentById(){
    try{
        RandomAccessFile raf= new RandomAccessFile("student.txt","r");
        System.out.print("Enter student id: ");
        int id;
        try{
            id=input.nextInt();
            input.nextLine();

        }catch(Exception ex){
            System.out.println("Invalid ID!!");
            return;
        }
        String line;
        boolean found=false;
        while((line=raf.readLine())!=null){
            line=line.trim();
            if(line.isEmpty()) continue;
            String[] parts =line.split(",");
            if(parts.length<6) continue;
            try{
                int stId=Integer.parseInt(parts[0]);
                if(stId==id){
                    String name=parts[1];
                    String program=parts[2];
                    String batch=parts[3];
                    String password=parts[4];
                    double cgpa=Double.parseDouble(parts[5]);
                    Student st=new Student(stId,name,program,batch,password,cgpa);
                    st.displayInfo();
                    found=true;
                    break;
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid format in Data!!");
            }
        }
        if(!found){
            System.out.println("Student ID "+id+ " not found!");
        }
    }catch(FileNotFoundException e){
        System.out.println("File not found!!");
    }catch(IOException e){
        System.out.println("Error reading file");
    }
}
static void assignCourse(){
    try{
        RandomAccessFile raf=new RandomAccessFile("course.txt","rw");
        raf.seek(raf.length());

        System.out.print("Enter student ID: ");
        int id=input.nextInt();
        input.nextLine();
        System.out.print("Enter number of courses to assign: ");
        int coursecount=input.nextInt();
        input.nextLine();

        for(int i=1;i<=coursecount;i++){
            System.out.print("Enter course name "+i+": ");
            String courseName =input.nextLine();
            Course course =new Course(id,courseName);
            raf.writeBytes(course.toFileString(id,courseName));
        }
        System.out.println("Course assigned successfully");

    }catch(FileNotFoundException ex){
        System.out.println("File not found.");
        ex.printStackTrace();
    }catch(IOException ex){
        System.out.println("Fail to Load!!");
    }

}

static void viewCourseById(){
    try{
        RandomAccessFile raf=new RandomAccessFile("course.txt","r");
        System.out.print("Enter the student ID to view course: ");
        int id=input.nextInt();
        input.nextLine();
         String line;
        boolean f = false;
       while((line=raf.readLine()) != null)
       {
           String[] parts =line.split(",");
           if(parts.length<2){
               continue;
           }
           int sid;
           try{
               sid=Integer.parseInt(parts[0]);
           }catch(NumberFormatException ex){
               continue;
           }
           if(sid == id)
           {
               System.out.println("Course: " + parts[1]);
               f=true;
           }
           /*if(!f)
           {
               System.out.println("This student don't have any course");
           }*/
       }
    }catch(FileNotFoundException ex){
        System.out.println("File not found!!");
        ex.printStackTrace();
    }
    catch(IOException ex){
        System.out.println("Fail to load!!");
    }
}

    public static boolean validateOfficer(String username, String password) {

        if (!isvalidUsername(username)) {
            System.out.println("Invalid username! Only letters are allowed.");
            return false;
        }


        if (!idVlidPassword(password)) {
            System.out.println("Password must be at least 6 characters long.");
            return false;
        }


        try (RandomAccessFile raf = new RandomAccessFile("officers.txt", "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                String storedUsername = parts[0].trim();
                String storedPassword = parts[1].trim();

                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading officers file: " + e.getMessage());
        }

        System.out.println("Invalid username or password.");
        return false;
    }

public static boolean  isvalidUsername(String name){
    for(int i=0;i<name.length();i++){
        char ch=name.charAt(i);
        if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))){
            return false;
        }
    }
    return true;
}

public static boolean idVlidPassword(String password){
    return password.length()>=6;
}


}
