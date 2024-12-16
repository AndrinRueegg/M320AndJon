import java.util.ArrayList;

public class Team {
    private ArrayList<Player> playerList;

    public Team() {
        playerList = new ArrayList<>();
    }

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
