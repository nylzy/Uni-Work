import java.util.ArrayList;

public class ClassDatabase {

    ArrayList<String> courses;
    ArrayList<String> students; 

    public ClassDatabase(){
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addCourseStudent(String student, String course) {
        courses.add(course);
        students.add(student);
    }

    public int countStudents(String course){   
        int counter = 0;
        for (int i = 0; i < courses.size(); i++) {
            if ((courses.get(i)).equals(course)) {
                counter += 1;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        ClassDatabase db = new ClassDatabase();
        db.addCourseStudent("Alan Turing", "CITS2005");
        db.addCourseStudent("Alan Turing", "CITS2200");
        db.addCourseStudent("Max", "CITS9999");
        db.addCourseStudent("Gozz", "CITS9999");
        db.addCourseStudent("Jane Doe", "CITS2005");
        System.out.println(db.countStudents("CITS2005"));
        System.out.println(db.countStudents("CITS2200"));
        System.out.println(db.countStudents("CITS9999"));
    }

}

/* reflection
- always specify types, even for the i in for loop, is 'int i'
- declaring is just saying what type a variable is
- initialising is actually creating a value for it
- need to use '.equals()' not just '==' for string comparison. '==' will only compare memory location not logical content
    - this occurs as Java string variables do not store the text directly, they store a reference.
*/