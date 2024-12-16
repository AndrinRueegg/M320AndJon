import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static Team team;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        team = new Team();

        System.out.println("Welcome to the Football Team Manager!");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create player");
            System.out.println("2. Make a forward jog");
            System.out.println("3. Start game");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createPlayer();
                    break;
                case 2:
                    makeForwardJog();
                    break;
                case 3:
                    team.startGame();
                    break;
                case 4:
                    System.out.println("Program terminated.");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void createPlayer() {
        System.out.println("Enter the player's name:");
        String name = scanner.nextLine();

        System.out.println("Choose the position (1=Goalkeeper, 2=Forward, 3=Defender):");
        int position = scanner.nextInt();
        scanner.nextLine();

        switch (position) {
            case 1:
                System.out.println("Enter the height of the goalkeeper:");
                double height = scanner.nextDouble();
                team.addPlayer(new Goalkeeper(name, height));
                break;
            case 2:
                System.out.println("Enter the number of goals for the forward:");
                int goals = scanner.nextInt();
                team.addPlayer(new Forward(name, goals));
                break;
            case 3:
                System.out.println("Enter the defense values for the defender:");
                int defense = scanner.nextInt();
                team.addPlayer(new Defender(name, defense));
                break;
            default:
                System.out.println("Invalid position.");
        }
    }

    private static void makeForwardJog() {

        ArrayList<Forward> forwards = team.getForwards();
        if (forwards.isEmpty()) {
            System.out.println("No forwards available in the team.");
            return;
        }


        System.out.println("Select a forward to jog:");
        for (int i = 0; i < forwards.size(); i++) {
            System.out.println((i + 1) + ". " + forwards.get(i).name);
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > forwards.size()) {
            System.out.println("Invalid choice.");
        } else {

            Forward selectedForward = forwards.get(choice - 1);
            selectedForward.jogTraining();
            System.out.println(selectedForward.name + " is now jogging!");
        }
    }
}
