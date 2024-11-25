import java.util.Random;
import java.util.Scanner;

public class WordGuesser {
    private String wordToGuess;
    private int attemptsLeft;
    private final String[] words = {
            "java", "programming", "keyboard", "mouse", "monitor",
            "developer", "software", "hardware", "algorithm", "debugging"
    };

    public WordGuesser() {
        this.wordToGuess = getRandomWord();
        this.attemptsLeft = 5;
    }

    private String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Word Guesser game!");
        System.out.println("Try to guess the word. You have 3 chances.");
        System.out.println("Type 'exit' to quit the game.\n");

        while (attemptsLeft > 0) {
            System.out.print("Enter your guess: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Game Over. Thank you for playing!");
                break;
            }

            if (input.equals(wordToGuess)) {
                System.out.println("Correct! You've guessed the word!");
                break;
            } else {
                attemptsLeft--;
                System.out.println("Wrong guess. Attempts left: " + attemptsLeft);

                if (attemptsLeft > 0) {
                    giveHint();
                }
            }

            if (attemptsLeft == 0) {
                System.out.println("No more attempts left. The correct word was: " + wordToGuess);
            }
        }
    }

    private void giveHint() {
        System.out.println("Hint: The word starts with '" + wordToGuess.charAt(0) + "' and is " + wordToGuess.length() + " letters long.");
    }
}
e