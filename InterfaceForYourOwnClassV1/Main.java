import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Push an element");
            System.out.println("2. Pop an element");
            System.out.println("3. View the top element");
            System.out.println("4. Iterate over stack");
            System.out.println("5. View stack size");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    System.out.print("Enter an element to push onto the stack: ");
                    int pushElement = scanner.nextInt();
                    stack.push(pushElement);
                    System.out.println(pushElement + " has been pushed onto the stack.");
                    break;

                case 2:

                    if (!stack.isEmpty()) {
                        int poppedElement = stack.pop();
                        System.out.println(poppedElement + " has been popped from the stack.");
                    } else {
                        System.out.println("Stack is empty.");
                    }
                    break;

                case 3:

                    if (!stack.isEmpty()) {
                        System.out.println("Top element: " + stack.peek());
                    } else {
                        System.out.println("Stack is empty.");
                    }
                    break;

                case 4:

                    System.out.println("Iterating over stack:");
                    if (stack.isEmpty()) {
                        System.out.println("The stack is empty.");
                    } else {
                        for (Integer element : stack) {
                            System.out.println(element);
                        }
                    }
                    break;

                case 5:

                    System.out.println("Stack size: " + stack.size());
                    break;

                case 6:

                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }

        scanner.close();
    }
}
