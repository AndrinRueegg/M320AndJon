import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Heating bedroom = new Heating("Bedroom", 20, 15, 30, 2);
        Heating diningRoom = new Heating("Dining Room", 22, 15, 30, 2);
        Heating bathroom = new Heating("Bathroom", 24, 15, 30, 2);

        boolean running = true;


        System.out.println("Control the temperature with commands: ");
        System.out.println("- Type 'warmer <room>' to increase the temperature (e.g., 'warmer bedroom').");
        System.out.println("- Type 'colder <room>' to decrease the temperature (e.g., 'colder diningroom').");
        System.out.println("- Type 'status <room>' to see the temperature (e.g., 'status bathroom').");
        System.out.println("- Type 'exit' to end the program.");

        while (running) {
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine().toLowerCase();

            if (command.equals("exit")) {
                running = false;
                System.out.println("Exiting program.");
            } else if (command.startsWith("warmer")) {
                if (command.contains("bedroom")) {
                    bedroom.warmer();
                } else if (command.contains("dining room") || command.contains("diningroom")) {
                    diningRoom.warmer();
                } else if (command.contains("bathroom")) {
                    bathroom.warmer();
                } else {
                    System.out.println("Room not recognized.");
                }
            } else if (command.startsWith("colder")) {
                if (command.contains("bedroom")) {
                    bedroom.colder();
                } else if (command.contains("dining room") || command.contains("diningroom")) {
                    diningRoom.colder();
                } else if (command.contains("bathroom")) {
                    bathroom.colder();
                } else {
                    System.out.println("Room not recognized.");
                }
            } else if (command.startsWith("status")) {
                if (command.contains("bedroom")) {
                    bedroom.displayTemperature();
                } else if (command.contains("dining room") || command.contains("diningroom")) {
                    diningRoom.displayTemperature();
                } else if (command.contains("bathroom")) {
                    bathroom.displayTemperature();
                } else {
                    System.out.println("Room not recognized.");
                }
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }
}
