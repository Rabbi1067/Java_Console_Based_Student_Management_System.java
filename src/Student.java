import java.io.FileNotFoundException;

public class Student {
    private int id;
    private String name;
    private String program;
    private String batch;
    private String password;
    private double cgpa;
    public Student(int id,String name,String program,String batch,String password,double cgpa){
        this.id=id;
        this.name=name;
        this.program=program;
        this.batch=batch;
        this.password=password;
        this.cgpa=cgpa;
    }
    public int getId(){
        return id;
    }
    public String toFileString(){
        return id+","+name+","+program+","+batch+","+password+","+cgpa;
    }
    public static Student fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length < 6) return null;

            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String program = parts[2].trim();
            String batch = parts[3].trim();
            String password = parts[4].trim(); // password as String
            double cgpa = Double.parseDouble(parts[5].trim());

            return new Student(id, name, program, batch, password, cgpa);
        } catch (Exception e) {
            return null;
        }
    }
    public void displayInfo(){
        System.out.println("----------------------------");
        System.out.println("ID: "+id);
        System.out.println("Name: "+name);
        System.out.println("Program: "+program);
        System.out.println("batch: "+batch);
        System.out.println("Password: "+password);
        System.out.println("CGPA: "+cgpa);
        System.out.println("----------------------------");
    }
}
