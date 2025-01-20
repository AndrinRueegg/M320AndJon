/**
 * Represents a generic player in a football team.
 * <p>
 * Generated on 2025-01-20
 * </p>
 *
 * @author
 *         andrinrueegg,
 *         indianajones
 * @version 1.0
 */
public class Player {
    protected String name;

    /**
     * Constructs a Player with the specified name.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    public void play() {
        System.out.println(name + " is playing!");
    }

    public void showName() {
        System.out.println("Player Name: " + name);
    }
}
