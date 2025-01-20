/**
 * Represents a forward in a football team.
 * <p>
 * Generated on 2025-01-20
 * </p>
 *
 *
 *
 */
public class Forward extends Player {

    /**
     * Total goals scored by the forward.
     */
    private int goals;

    /**
     * Indicates if the forward is currently jogging.
     */
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

    /**
     * Prints the forward's current action (jogging or attacking).
     */
    @Override
    public void play() {
        if (isJogging) {
            System.out.println(name + " is jogging!");
        } else {
            System.out.println(name + " is attacking!");
        }
    }

    /**
     * Sets the forward to a jogging state.
     */
    public void jogTraining() {
        isJogging = true;
    }

    /**
     * Stops the forward from jogging.
     */
    public void stopJogging() {
        isJogging = false;
    }
}
