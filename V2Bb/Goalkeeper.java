/**
 * Represents a goalkeeper in a football team.
 * <p>
 * Generated on 2025-01-20
 * </p>
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class Goalkeeper extends Player {

    /**
     * Goalkeeper's height in meters.
     */
    private double height;

    /**
     * Constructs a Goalkeeper with a given name and height.
     *
     * @param name   the goalkeeper's name
     * @param height the goalkeeper's height in meters
     */
    public Goalkeeper(String name, double height) {
        super(name);
        this.height = height;
    }

    /**
     * Prints a message indicating that this player is a goalkeeper and is defending the goal.
     */
    @Override
    public void play() {
        System.out.println(name + " is the goalkeeper and defends the goal!");
    }
}
