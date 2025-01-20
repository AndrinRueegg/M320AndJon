/**
 * Represents a goalkeeper in a football team.
 * <p>
 * Generated on 2025-01-20
 * </p>
 *
 * @author
 *         andrinrueegg,
 *         indianajones
 * @version 1.0
 */
public class Goalkeeper extends Player {
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

    @Override
    public void play() {
        System.out.println(name + " is the goalkeeper and defends the goal!");
    }
}
