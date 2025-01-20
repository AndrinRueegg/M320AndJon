import java.util.ArrayList;

/**
 * Manages a list of players and handles team activities.
 * <p>
 * Generated on 2025-01-20
 * </p>
 *
 *
 *
 * @author
 *         andrinrueegg,
 *         indianajones
 * @version 1.0
 */
public class Team {
    private ArrayList<Player> playerList;

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

    public void startGame() {
        for (Player player : playerList) {
            player.play();
        }
    }

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
