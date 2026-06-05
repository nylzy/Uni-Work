public class StudentManager {
    private List<String> students = new ArrayList<>();

    public void addStudent(String name) {
        students.add(name); 
    }
    
    public void removeStudent(String name) { 
        students.remove(name); 
    }

    public void saveToFile(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (String s : students) { writer.write(s + "\n"); }
        writer.close();
    }

    public void printStudents() {
        for (String s : students) { System.out.println(s); }
    }
}