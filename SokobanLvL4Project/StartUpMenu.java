/**
 * The StartUpMenu class represents the main menu of the Sokoban game.
 * It allows players to select various options such as starting a game, selecting levels,
 * viewing help information, and exiting the application.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * StartUpMenu handles the graphical user interface (GUI) for the main menu of the game.
 */
public class StartUpMenu extends JFrame {

    /**
     * Static field to track the total number of coins collected by the player.
     */
    public static int totalCoins = 0;

    /**
     * Array of menu options displayed to the user.
     */
    private String[] options = {
            "Start Game (Level 1)",
            "Select Level",
            "Help",
            "About Creator",
            "Exit"
    };

    /**
     * Index of the currently selected menu option.
     */
    private int currentSelection = 0;

    /**
     * Constructor for the StartUpMenu class.
     * Initializes the menu and sets up the GUI components.
     */
    public StartUpMenu() {
        // Set the default close operation to exit the application when the window is closed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Maximize the window to fit the screen.
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Remove window decorations such as the title bar.
        setUndecorated(true);

        // Set the title of the window.
        setTitle("Sokoban Startup Menu");

        // Add a KeyListener to handle keyboard input.
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Handle navigation and selection based on key presses.
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        // Move the selection up, wrapping around if necessary.
                        currentSelection = (currentSelection - 1 + options.length) % options.length;
                        repaintSelection();
                        break;

                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        // Move the selection down, wrapping around if necessary.
                        currentSelection = (currentSelection + 1) % options.length;
                        repaintSelection();
                        break;

                    case KeyEvent.VK_ENTER:
                        // Handle the selected menu option.
                        handleSelection();
                        break;

                    default:
                        // Ignore other key presses.
                        break;
                }
            }
        });

        // Make the window visible.
        setVisible(true);
    }

    /**
     * Handles the action associated with the currently selected menu option.
     */
    private void handleSelection() {
        switch (currentSelection) {
            case 0: // Start the game from Level 1.
                new GameIO(new Game(0), 64, 1.0);
                LevelInfo.displayLevelTitle(1);
                dispose(); // Close the menu window.
                break;

            case 1: // Prompt the user to select a level.
                String levelStr = CustomDialog.input("Enter level number (1..7):", "Level Selection");
                if (levelStr != null) {
                    try {
                        int lvl = Integer.parseInt(levelStr);
                        if (lvl < 1 || lvl > 7) {
                            throw new InvalidLevelException("Invalid level number: " + lvl);
                        } else {
                            new GameIO(new Game(lvl - 1), 64, 1.0);
                            LevelInfo.displayLevelTitle(lvl);
                            dispose();
                        }
                    } catch (InvalidLevelException e) {
                        CustomDialog.alert(e.getMessage(), "Error");
                    } catch (Exception e) {
                        CustomDialog.alert("Invalid input!", "Error");
                    }
                }
                break;

            case 2: // Display the help information.
                displayHelp();
                break;

            case 3: // Display information about the creator.
                AboutCreator aboutCreator = new AboutCreator();
                CustomDialog.alert(aboutCreator.getInformation(), "About the Creator");
                break;

            case 4: // Exit the application.
                dispose();
                System.exit(0);
                break;
        }
    }

    /**
     * Displays the help information for the game.
     */
    private void displayHelp() {
        String helpMessage = """
        <html>
        <div style='font-size:14px;'>
        Sokoban Game Help:<br>
        1. Use W, A, S, D to move the player.<br>
        2. Push boxes to checkpoints.<br>
        3. Avoid lava tiles.<br>
        4. Collect coins to increase your score.<br>
        5. Use portals to teleport.<br>
        6. Press R to reset the current level.<br>
        7. Press ESC to access the pause menu.
        </div>
        </html>
         """;
        CustomDialog.alert(helpMessage, "Help");
    }

    /**
     * Paints the main menu and highlights the selected option.
     *
     * @param g The Graphics object used for painting.
     */
    @Override
    public void paint(Graphics g) {
        // Call the superclass's paint method to ensure proper rendering.
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smoother graphics.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get the dimensions of the window.
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        // Draw the background with a dark color.
        g2d.setColor(new Color(30, 30, 30));
        g2d.fillRect(0, 0, screenWidth, screenHeight);

        // Render a grid-like pattern with dynamic colors.
        for (int i = 0; i < screenWidth; i += 40) {
            for (int j = 0; j < screenHeight; j += 40) {
                int red = Math.abs((i + j) % 255);
                int green = Math.abs((i * j) % 255);
                int blue = Math.abs((i - j + 255) % 255);
                g2d.setColor(new Color(red, green, blue, 50));
                g2d.fillRect(i, j, 40, 40);
            }
        }

        // Draw the title of the menu.
        Font titleFont = new Font("Arial", Font.BOLD, 80);
        g2d.setFont(titleFont);
        g2d.setColor(new Color(220, 220, 220));
        String title = "Sokoban Startup Menu";
        int titleWidth = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, (screenWidth - titleWidth) / 2, screenHeight / 4);
    }

    /**
     * Repaints the menu to reflect the currently selected option.
     */
    private void repaintSelection() {
        Graphics g = getGraphics();

        // Ensure the Graphics object is not null.
        if (g == null) return;

        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smooth rendering.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get the screen dimensions.
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        Font optionFont = new Font("Arial", Font.PLAIN, 50);
        g2d.setFont(optionFont);
        int startY = screenHeight / 3;
        int buttonHeight = 90;
        int buttonSpacing = 30;

        // Calculate the maximum width of all menu buttons.
        int maxButtonWidth = 0;
        for (String option : options) {
            int width = g2d.getFontMetrics().stringWidth(option) + 60;
            if (width > maxButtonWidth) {
                maxButtonWidth = width;
            }
        }

        // Render each menu option.
        for (int i = 0; i < options.length; i++) {
            if (i == currentSelection) {
                // Highlight the selected option with a blue color.
                g2d.setColor(new Color(50, 150, 250));
            } else {
                // Use a gray color for unselected options.
                g2d.setColor(new Color(80, 80, 80));
            }

            // Draw the button background.
            g2d.fillRoundRect(
                    (screenWidth - maxButtonWidth) / 2,
                    startY + i * (buttonHeight + buttonSpacing) - buttonHeight / 2,
                    maxButtonWidth,
                    buttonHeight,
                    40,
                    40
            );

            // Draw the button text.
            g2d.setColor(i == currentSelection ? Color.WHITE : Color.LIGHT_GRAY);
            String option = options[i];
            int optionWidth = g2d.getFontMetrics().stringWidth(option);
            g2d.drawString(
                    option,
                    (screenWidth - optionWidth) / 2,
                    startY + i * (buttonHeight + buttonSpacing) + buttonHeight / 4
            );
        }

        // Display the total number of coins collected.
        g2d.setFont(new Font("Arial", Font.PLAIN, 25));
        g2d.setColor(new Color(180, 180, 180));
        g2d.drawString("Total Coins: " + totalCoins, 20, screenHeight - 20);
    }
}

/**
 * Provides information about level titles and displays level titles to the user.
 */
class LevelInfo {

    /**
     * Array of titles for each level in the game.
     */
    private static final String[] LEVEL_TITLES = {
            "Level 1: Beginning",
            "Level 2: Challenge",
            "Level 3: Challenge 2",
            "Level 4: Easy Maze",
            "Level 5: Lava Zone",
            "Level 6: Portal Room",
            "Level 7: The Right Path"
    };

    /**
     * Displays the title of the specified level.
     *
     * @param level The level number to display.
     */
    public static void displayLevelTitle(int level) {
        if (level < 1 || level > LEVEL_TITLES.length) return;
        String title = LEVEL_TITLES[level - 1];
        CustomDialog.alert(title, "Level Start");
    }
}

/**
 * Custom exception class for handling invalid level numbers.
 */
class InvalidLevelException extends RuntimeException {

    /**
     * Constructor for the InvalidLevelException.
     *
     * @param message The error message.
     */
    public InvalidLevelException(String message) {
        super(message);
    }
}

/**
 * Utility class for creating and displaying custom dialog boxes.
 */
class CustomDialog {

    /**
     * Displays an alert dialog with a specified message and title.
     *
     * @param message The message to display.
     * @param title   The title of the dialog.
     */
    public static void alert(String message, String title) {
        JOptionPane.showMessageDialog(
                null,
                createStyledMessage(message),
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Displays an input dialog with a specified message and title.
     *
     * @param message The message to display.
     * @param title   The title of the dialog.
     * @return The user's input, or null if canceled.
     */
    public static String input(String message, String title) {
        return (String) JOptionPane.showInputDialog(
                null,
                createStyledMessage(message),
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null
        );
    }

    /**
     * Creates a styled JLabel for use in dialog boxes.
     *
     * @param message The message to style.
     * @return A JLabel with the styled message.
     */
    private static JLabel createStyledMessage(String message) {
        JLabel label = new JLabel("<html><div style='font-size:14px;'>" + message + "</div></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
}
