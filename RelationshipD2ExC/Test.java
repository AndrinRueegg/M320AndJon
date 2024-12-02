public class Test {
    private int points;
    private final int totalPoints;

    public Test(int points, int totalPoints) {
        this.points = points;
        this.totalPoints = totalPoints;
    }

    public float calculateGrade() {
        return (points * 5.0f / totalPoints) + 1;
    }
}
