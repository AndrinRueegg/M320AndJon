public class Player {
    protected String name;

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
