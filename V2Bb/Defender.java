/**
 * Represents a defender in a football team.
 * <p>
 * Generated on 2025-01-20
 * </p>
 *
 *
 * @author
 *         andrinrueegg,
 *         indianajones
 * @version 1.0
 */
public class Defender extends Player {
    private int defense;

    /**
     * Constructs a Defender with a given name and defense value.
     *
     * @param name    the defender's name
     * @param defense the defender's defensive skill level
     */
    public Defender(String name, int defense) {
        super(name);
        this.defense = defense;
    }

    @Override
    public void play() {
        System.out.println(name + " is a defender and is defending!");
    }
}
