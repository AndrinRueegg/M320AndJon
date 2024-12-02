import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Class schoolClass = new Class();

        System.out.println("Enter the number of students (minimum 3): ");
        int studentCount = scanner.nextInt();

        for (int i = 0; i < studentCount; i++) {
            System.out.println("Enter the name of student " + (i + 1) + ": ");
            scanner.nextLine();
            String name = scanner.nextLine();
            Student student = new Student(name);


            System.out.println("Enter Math test points and total points (format: points totalPoints): ");
            int mathPoints = scanner.nextInt();
            int mathTotalPoints = scanner.nextInt();
            student.addTest("Math", new Test(mathPoints, mathTotalPoints));


            System.out.println("Enter English test points and total points (format: points totalPoints): ");
            int englishPoints = scanner.nextInt();
            int englishTotalPoints = scanner.nextInt();
            student.addTest("English", new Test(englishPoints, englishTotalPoints));


            System.out.println("Enter History test points and total points (format: points totalPoints): ");
            int historyPoints = scanner.nextInt();
            int historyTotalPoints = scanner.nextInt();
            student.addTest("History", new Test(historyPoints, historyTotalPoints));

            schoolClass.addStudent(student);
        }

        System.out.println("\nClass Average Grade: " + schoolClass.getClassAverage());
        for (Student student : schoolClass.getStudents()) {
            System.out.println("Student: " + student.getName() + ", Average Grade: " + student.getAverageGrade()); // Corrected method name
        }

        scanner.close();
    }
}
