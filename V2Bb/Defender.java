public class Defender extends Player {
    private int defense;

    public Defender(String name, int defense) {
        super(name);
        this.defense = defense;
    }

    @Override
    public void play() {
        System.out.println(name + " is a defender and is defending!");
    }
}
