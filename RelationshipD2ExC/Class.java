import java.util.ArrayList;

public class Class {
    private ArrayList<Student> students;

    public Class() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }


    public float getClassAverage() {
        if (students.isEmpty()) {
            return 0;
        }
        float total = 0;
        for (Student student : students) {
            total += student.getAverageGrade();
        }
        return total / students.size();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
