/**
 * Represents a forward in a football team.
 * <p>
 * Generated on 2025-01-20
 * </p>
 *
 * @author
 *         andrinrueegg,
 *         indianajones
 * @version 1.0
 */
public class Forward extends Player {
    private int goals;
    private boolean isJogging;

    /**
     * Constructs a Forward with a given name and number of goals.
     *
     * @param name  the forward's name
     * @param goals the forward's total goals
     */
    public Forward(String name, int goals) {
        super(name);
        this.goals = goals;
        this.isJogging = false;
    }

    @Override
    public void play() {
        if (isJogging) {
            System.out.println(name + " is jogging!");
        } else {
            System.out.println(name + " is attacking!");
        }
    }

    public void jogTraining() {
        isJogging = true;
    }

    public void stopJogging() {
        isJogging = false;
    }
}
