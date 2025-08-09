public class Course {
    private int id;
    private String course;
    Course(int id,String course){
        if(id>0){
            this.id=id;
        }
        else{
            System.out.println("Invalid ID!!");
        }
        this.course=course;
    }
    String toFileString (int id,String course){
        return id+","+course+"\n";
    }
}
