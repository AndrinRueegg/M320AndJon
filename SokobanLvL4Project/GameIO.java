/**
 * GameIO is responsible for rendering the Sokoban game interface.
 * It extends JFrame to provide a graphical window for the game.
 * This class manages the game's visuals, input handling, and player interaction.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * The main class for the Sokoban game's GUI and input management.
 */
public class GameIO extends JFrame {

    // The game instance that holds the game state.
    private Game game;

    // BufferedImages for different game elements.
    private BufferedImage boxImage;  // Image for the box element in the game.
    private BufferedImage floorImage; // Image for the floor tiles.
    private BufferedImage endPoint; // Image for the end point or target location.
    private BufferedImage wallImage; // Image for the wall tiles.
    private BufferedImage playerImage; // Image for the player character.
    private BufferedImage coinImage; // Image for the collectible coins.
    private BufferedImage lavaImage; // Image for lava tiles.
    private BufferedImage portalPurple; // Image for purple portal.
    private BufferedImage portalGreen; // Image for green portal.

    // Dimensions of the viewport.
    private int viewportWidth; // Number of tiles visible horizontally.
    private int viewportHeight; // Number of tiles visible vertically.

    // Offsets to determine the starting position of the viewport in the game world.
    private int offsetX = 0; // Horizontal offset.
    private int offsetY = 0; // Vertical offset.

    // BufferedImage used as the display area for rendering.
    private BufferedImage displayArea;

    // Scale determines the size of each tile on the screen.
    private int scale;

    // Boolean to track whether the game is paused.
    private boolean paused = false;

    // Map linking key codes to game actions.
    private final Map<Integer, Runnable> inputMap;

    /**
     * Constructor for the GameIO class.
     *
     * @param game       The game instance containing the game state.
     * @param scale      The scale factor for rendering the tiles.
     * @param cellMargin The margin between cells (not currently used).
     */
    public GameIO(Game game, int scale, double cellMargin) {
        // Initialize the game instance.
        this.game = game;

        // Set the scale for rendering.
        this.scale = scale;

        // Load images for the game elements.
        try {
            boxImage = ImageIO.read(new File("box.png")); // Load box image.
            floorImage = ImageIO.read(new File("floor.png")); // Load floor image.
            endPoint = ImageIO.read(new File("box_target.png")); // Load target image.
            wallImage = ImageIO.read(new File("wall1.png")); // Load wall image.
            playerImage = ImageIO.read(new File("player.png")); // Load player image.
            coinImage = ImageIO.read(new File("coin.png")); // Load coin image.
            lavaImage = ImageIO.read(new File("lavav.png")); // Load lava image.
            portalPurple = ImageIO.read(new File("PortalPurple.png")); // Load purple portal image.
            portalGreen = ImageIO.read(new File("PortalGreen.png")); // Load green portal image.
        } catch (IOException e) {
            // Print the stack trace if image loading fails.
            e.printStackTrace();
        }

        // Map key presses to game actions.
        inputMap = Map.of(
                KeyEvent.VK_W, () -> {
                    game.moveUp(); // Move up when 'W' is pressed.
                    onPlayerMove(); // Update the game state.
                },
                KeyEvent.VK_S, () -> {
                    game.moveDown(); // Move down when 'S' is pressed.
                    onPlayerMove(); // Update the game state.
                },
                KeyEvent.VK_A, () -> {
                    game.moveLeft(); // Move left when 'A' is pressed.
                    onPlayerMove(); // Update the game state.
                },
                KeyEvent.VK_D, () -> {
                    game.moveRight(); // Move right when 'D' is pressed.
                    onPlayerMove(); // Update the game state.
                },
                KeyEvent.VK_R, () -> {
                    game.resetField(); // Reset the game field when 'R' is pressed.
                    onPlayerMove(); // Update the game state.
                }
        );

        // Initialize the GUI components.
        initGUI();

        // Center the camera on the player's initial position.
        centerCameraOnPlayer();

        // Update the display area.
        updateOutput();
    }

    /**
     * Initializes the GUI components for the game window.
     */
    private void initGUI() {
        // Get the screen dimensions.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = (int) screenSize.getWidth(); // Width of the screen.
        int windowHeight = (int) screenSize.getHeight(); // Height of the screen.

        // Set the scale for larger tiles (adjustable based on screen resolution).
        this.scale = 128;

        // Calculate the number of tiles visible in the viewport.
        viewportWidth = windowWidth / scale;
        viewportHeight = windowHeight / scale;

        // Initialize the BufferedImage for the display area.
        displayArea = new BufferedImage(viewportWidth * scale, viewportHeight * scale, BufferedImage.TYPE_INT_ARGB);

        // Create a JLabel to display the BufferedImage.
        JLabel displayLabel = new JLabel(new ImageIcon(displayArea));
        getContentPane().add(displayLabel, BorderLayout.CENTER); // Add the label to the frame.

        // Set the frame properties.
        setTitle("Sokoban (With Portals!)"); // Set the title of the game window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed.
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to full screen.
        setUndecorated(true); // Remove the window decorations (e.g., title bar).
        setVisible(true); // Make the window visible.

        // Add a KeyListener to handle key presses.
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // If the game is paused, handle the ESC key to resume.
                if (paused) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        // Resume the game when ESC is pressed.
                    }
                } else {
                    // If the game is not paused, handle input commands.
                    if (inputMap.containsKey(e.getKeyCode())) {
                        inputMap.get(e.getKeyCode()).run(); // Execute the corresponding action.
                    }
                }
                // Handle the ESC key to show the pause menu.
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    showPauseMenu();
                }
            }
        });
    }

    /**
     * Called whenever the player moves. Updates the camera and display.
     */
    private void onPlayerMove() {
        // Center the camera on the player's current position.
        centerCameraOnPlayer();

        // Update the graphical output.
        updateOutput();
    }

    /**
     * Centers the camera on the player's position.
     */
    private void centerCameraOnPlayer() {
        // Get the player's current position.
        Point p = game.findPlayer();

        // If the player is not found, return early.
        if (p == null) return;

        // Calculate the desired offsets to center the player in the viewport.
        int desiredX = p.x - viewportWidth / 2;
        int desiredY = p.y - viewportHeight / 2;

        // Calculate the maximum offsets to prevent going out of bounds.
        int maxX = game.getRowCount() - viewportWidth;
        int maxY = game.getColCount() - viewportHeight;

        // Clamp the offsets to ensure they stay within bounds.
        if (desiredX < 0) desiredX = 0;
        if (desiredY < 0) desiredY = 0;
        if (desiredX > maxX) desiredX = maxX;
        if (desiredY > maxY) desiredY = maxY;

        // Update the camera offsets.
        offsetX = desiredX;
        offsetY = desiredY;
    }

    /**
     * Updates the graphical output by rendering the game field.
     */
    private void updateOutput() {
        // Get the graphics context for the display area.
        Graphics2D g2d = (Graphics2D) displayArea.getGraphics();

        // Clear the display area with a black background.
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, displayArea.getWidth(), displayArea.getHeight());

        // Iterate over the tiles in the viewport.
        for (int screenY = 0; screenY < viewportHeight; screenY++) {
            for (int screenX = 0; screenX < viewportWidth; screenX++) {
                // Calculate the corresponding world coordinates.
                int worldX = offsetX + screenX;
                int worldY = offsetY + screenY;

                // Skip tiles outside the game field bounds.
                if (worldY < 0 || worldY >= game.getColCount() ||
                        worldX < 0 || worldX >= game.getRowCount()) {
                    continue;
                }

                // Calculate the pixel coordinates for rendering.
                int px = screenX * scale;
                int py = screenY * scale;

                // Get the value of the current tile.
                int tileVal = game.getField()[worldY][worldX];

                // Render the tile based on its value.
                switch (tileVal) {
                    case 0:
                        drawTile(g2d, floorImage, px, py); // Draw floor.
                        break;
                    case 1:
                        drawTile(g2d, wallImage, px, py); // Draw wall.
                        break;
                    case 2:
                        drawTile(g2d, floorImage, px, py); // Draw floor.
                        drawTile(g2d, playerImage, px, py); // Draw player.
                        break;
                    case 3:
                        drawTile(g2d, floorImage, px, py); // Draw floor.
                        drawTile(g2d, boxImage, px, py); // Draw box.
                        break;
                    case 4:
                        drawTile(g2d, endPoint, px, py); // Draw target.
                        break;
                    case 5:
                        drawTile(g2d, floorImage, px, py); // Draw floor.
                        drawTile(g2d, coinImage, px, py); // Draw coin.
                        break;
                    case 6:
                        drawTile(g2d, lavaImage, px, py); // Draw lava.
                        break;
                    case 7:
                        drawTile(g2d, floorImage, px, py); // Draw floor.
                        drawTile(g2d, portalPurple, px, py); // Draw purple portal.
                        break;
                    case 8:
                        drawTile(g2d, floorImage, px, py); // Draw floor.
                        drawTile(g2d, portalGreen, px, py); // Draw green portal.
                        break;
                    default:
                        g2d.setColor(Color.PINK); // Draw unknown tile in pink.
                        g2d.fillRect(px, py, scale, scale);
                }
            }
        }

        // Repaint the frame to display the updated graphics.
        repaint();
    }

    /**
     * Draws a single tile on the display area.
     *
     * @param g2d The graphics context for rendering.
     * @param img The image to draw (or null for a black tile).
     * @param x   The x-coordinate for rendering.
     * @param y   The y-coordinate for rendering.
     */
    private void drawTile(Graphics2D g2d, Image img, int x, int y) {
        // If the image is not null, draw it scaled.
        if (img != null) {
            g2d.drawImage(img, x, y, scale, scale, null);
        } else {
            // Draw a black tile if the image is null.
            g2d.setColor(Color.BLACK);
            g2d.fillRect(x, y, scale, scale);
        }
    }

    /**
     * Displays the pause menu with options to continue or return to the main menu.
     */
    private void showPauseMenu() {
        // Set the paused state to true.
        paused = true;

        // Options for the pause menu dialog.
        String[] options = {"Continue", "Main Menu"};

        // Show a dialog with the pause menu options.
        int choice = JOptionPane.showOptionDialog(
                this,
                "Paused",
                "Pause Menu",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        // Handle the user's choice.
        if (choice == 0) {
            // Resume the game.
            paused = false;
        } else {
            // Update the total coins and return to the main menu.
            StartUpMenu.totalCoins += game.coinCount;
            dispose(); // Close the current window.
            new StartUpMenu(); // Open the main menu.
        }
    }
}

/**
 * Interface for renderable game components.
 */
interface Renderable {
    /**
     * Renders the component using the given Graphics2D context.
     *
     * @param g The Graphics2D context for rendering.
     */
    void render(Graphics2D g);
}

/**
 * Class for tracking game statistics, such as total coins and deaths.
 */
class GameStats {
    private int totalCoins; // The total number of coins collected.
    private int totalDeaths; // The total number of player deaths.

    /**
     * Adds coins to the total.
     *
     * @param coins The number of coins to add.
     */
    public void addCoins(int coins) {
        this.totalCoins += coins;
    }

    /**
     * Increments the death count by one.
     */
    public void incrementDeaths() {
        this.totalDeaths++;
    }

    /**
     * Gets the total number of coins collected.
     *
     * @return The total number of coins.
     */
    public int getTotalCoins() {
        return totalCoins;
    }

    /**
     * Gets the total number of deaths.
     *
     * @return The total number of deaths.
     */
    public int getTotalDeaths() {
        return totalDeaths;
    }
}

/**
 * Custom exception for invalid game operations.
 */
class InvalidGameOperationException extends RuntimeException {
    /**
     * Constructor for InvalidGameOperationException.
     *
     * @param message The error message.
     */
    public InvalidGameOperationException(String message) {
        super(message);
    }
}
