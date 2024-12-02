import java.util.HashMap;

public class Student {
    private String name;
    private HashMap<String, Test> tests;

    public Student(String name) {
        this.name = name;
        this.tests = new HashMap<>();
    }

    public void addTest(String subject, Test test) {
        tests.put(subject, test);
    }

    public float getAverageGrade() {
        float total = 0;
        for (Test test : tests.values()) {
            total += test.calculateGrade();
        }
        return total / tests.size();
    }

    public Test getTest(String subject) {
        return tests.get(subject);
    }

    public String getName() {
        return name;
    }
}
