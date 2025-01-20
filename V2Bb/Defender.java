/**
 * Represents a defender in a football team.
 * <p>
 * Generated on 2025-01-20
 * </p>
 *
 *
 *
 */
public class Defender extends Player {

    /**
     * A numeric value indicating the defender's skill level.
     */
    private int defense;

    /**
     * Constructs a Defender with a given name and defense value.
     *
     * @param name    the defender's name
     * @param defense the defender's defensive skill
     */
    public Defender(String name, int defense) {
        super(name);
        this.defense = defense;
    }

    /**
     * Prints a message indicating that this player is defending.
     */
    @Override
    public void play() {
        System.out.println(name + " is a defender and is defending!");
    }
}
