public class Forward extends Player {
    private int goals;
    private boolean isJogging;

    public Forward(String name, int goals) {
        super(name);
        this.goals = goals;
        this.isJogging = false;
    }

    @Override
    public void play() {
        if (isJogging) {
            System.out.println(name + " is jogging!");
        } else {
            System.out.println(name + " is attacking!");
        }
    }

    public void jogTraining() {
        isJogging = true;
    }

    public void stopJogging() {
        isJogging = false;
    }
}
