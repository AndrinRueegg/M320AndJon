import java.util.ArrayList;

/**
 * Manages a list of players and handles team activities.
 * <p>
 * Generated on 2025-01-20
 * </p>
 *
 *
 *
 */
public class Team {

    /**
     * List of players in the team.
     */
    private ArrayList<Player> playerList;

    /**
     * Creates an empty team.
     */
    public Team() {
        playerList = new ArrayList<>();
    }

    /**
     * Adds a player to the team.
     *
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        playerList.add(player);
    }

    /**
     * Starts the game by calling the play() method on each player.
     */
    public void startGame() {
        for (Player player : playerList) {
            player.play();
        }
    }

    /**
     * Gets all forwards in the team.
     *
     * @return a list of Forward objects
     */
    public ArrayList<Forward> getForwards() {
        ArrayList<Forward> forwards = new ArrayList<>();
        for (Player player : playerList) {
            if (player instanceof Forward) {
                forwards.add((Forward) player);
            }
        }
        return forwards;
    }
}
