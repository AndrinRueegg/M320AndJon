/**
 * The Game class represents the core logic for the Sokoban game, including level management,
 * player movements, and interactions with game elements such as boxes, coins, lava, and portals.
 */
import javax.swing.JOptionPane;
import java.awt.Point;
import java.util.*;

public class Game {

    /**
     * The current state of the game field.
     * This is a 2D array where each element represents a tile in the game.
     */
    private int[][] gameField;

    /**
     * The initial state of the game field.
     * Used to reset the field to its original configuration.
     */
    private int[][] initialGameField;

    /**
     * List of points representing box positions in the game.
     */
    private List<Point> boxes;

    /**
     * List of initial box positions for resetting the game.
     */
    private List<Point> initialBoxes;

    /**
     * List of checkpoint positions where boxes must be placed.
     */
    private List<Point> checkpoints;

    /**
     * List of coin positions in the game.
     */
    private List<Point> coins;

    /**
     * List of initial coin positions for resetting the game.
     */
    private List<Point> initialCoins;

    /**
     * List of lava tile positions that cause the player to reset on contact.
     */
    private List<Point> lava;

    /**
     * List of purple portal positions.
     */
    private List<Point> portalsPurple;

    /**
     * List of green portal positions.
     */
    private List<Point> portalsGreen;

    /**
     * The index of the current level being played.
     */
    private int currentLevel = 0;

    /**
     * Counter for the number of coins collected by the player in the current level.
     */
    public int coinCount = 0;

    /**
     * A collection of predefined levels, where each level is a 2D array.
     * The values in the array represent different tile types (e.g., floor, walls, boxes).
     */

    private int[][][] levels = new int[][][] {

            new int[][] {
                    {0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,1,1,1,1,1,1,1,1,1,0,0},
                    {0,1,2,5,3,0,0,0,4,1,0,0},
                    {0,1,1,1,1,1,1,1,1,1,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0}
            },

            new int[][] {
                    {0,1,1,1,1,1,1,1,1,1,1,0},
                    {0,1,1,1,1,1,1,1,1,1,1,0},
                    {0,1,1,1,1,0,0,0,1,1,1,0},
                    {0,1,0,4,2,3,0,0,1,1,1,0},
                    {0,1,5,1,1,0,3,4,1,1,1,0},
                    {0,1,0,4,1,1,3,0,1,1,1,0},
                    {0,1,0,0,1,0,4,0,1,1,1,0},
                    {0,1,0,3,4,3,3,3,4,1,1,0},
                    {0,1,0,0,0,0,4,0,0,1,1,0},
                    {0,1,1,1,1,0,0,0,0,1,1,0},
                    {0,1,1,1,1,1,1,1,1,1,1,0}
            },
            new int[][] {
                    {0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,1,1,1,1,1,0,0,0},
                    {0,0,1,1,1,0,0,0,1,0,0,0},
                    {0,0,1,4,2,3,0,0,1,0,0,0},
                    {0,0,1,1,1,0,3,4,1,0,0,0},
                    {0,0,1,4,1,1,3,0,1,0,0,0},
                    {0,0,1,0,1,0,4,0,1,1,0,0},
                    {0,0,1,3,4,3,3,3,4,1,0,0},
                    {0,0,1,0,0,0,4,0,0,1,0,0},
                    {0,0,1,1,1,1,1,1,1,1,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0}
            },

            new int[][] {
                    {1,1,0,1,1,1,1,1,1,1,1,1},
                    {1,1,2,1,1,1,1,1,1,1,1,1},
                    {1,1,0,1,1,0,0,1,1,1,1,1},
                    {1,1,3,1,1,0,0,0,0,0,0,1},
                    {1,1,5,1,1,0,1,1,1,0,0,1},
                    {1,1,0,1,0,0,1,4,1,0,0,1},
                    {1,1,0,1,0,0,0,0,1,0,1,1},
                    {1,1,0,1,1,1,0,0,1,0,1,1},
                    {1,0,0,1,1,1,1,1,1,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,1,1},
                    {1,1,1,1,1,1,1,1,0,0,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1}
            },


            new int[][] {
                    {1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,2,0,0,0,0,0,0,0,0,0,1},
                    {1,6,6,6,6,6,6,6,6,6,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,6,6,6,6,6,6,6,6,6,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1},
                    {1,6,6,6,6,6,6,6,6,6,0,1},
                    {1,6,6,6,6,0,0,6,6,6,0,1},
                    {1,5,0,0,0,3,0,0,0,0,0,1},
                    {1,6,6,6,6,0,6,6,6,6,6,1},
                    {1,6,6,6,6,4,6,6,6,6,6,1},
                    {1,6,6,6,6,6,6,6,6,6,6,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1}
            },


            new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 7, 0, 3, 0, 1, 0, 0, 8, 0, 0, 1, 0, 0, 8, 0, 0, 1, 0, 0, 0, 8, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 7, 1, 0, 0, 0, 0, 7, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 7, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 8, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 8, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 8, 0, 0, 1, 0, 7, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 7, 0, 1, 7, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 5, 0, 0, 0, 0, 5, 1},
                    {1, 0, 8, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 8, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 5, 1},
                    {1, 7, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 5, 0, 0, 0, 0, 4, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            },
            new int[][] {
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1},
                    {1,7,1,1,1,1,1,1,1,1,1,7,1,1,1,1,1,1,7,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,1},
                    {1,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,1},
                    {1,8,1,1,1,1,1,1,1,1,1,8,1,1,1,1,1,1,8,1},
                    {1,0,6,6,6,6,6,6,6,6,6,0,6,6,6,6,6,6,0,1},
                    {1,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,5,1},
                    {1,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,3,1},
                    {1,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,4,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            }
    };



    /**
     * Constructor for the Game class.
     *
     * @param levelIndex The index of the level to load.
     * @throws IllegalArgumentException if the provided level index is invalid.
     */
    public Game(int levelIndex) {
        // Validate that the level index is within bounds.
        if (levelIndex < 0 || levelIndex >= levels.length) {
            throw new IllegalArgumentException("Invalid level: " + levelIndex);
        }
        // Set the current level.
        this.currentLevel = levelIndex;

        // Load the specified level.
        loadLevel(levelIndex);
    }

    /**
     * Loads a level and initializes its elements.
     *
     * @param idx The index of the level to load.
     */
    private void loadLevel(int idx) {
        // Clone the game field for the current level.
        gameField = clone2D(levels[idx]);
        // Clone the initial field for resetting purposes.
        initialGameField = clone2D(levels[idx]);

        // Identify and store the positions of key elements on the game field.
        checkpoints = findTiles(4); // Checkpoints where boxes must be placed.
        boxes = findTiles(3); // Box positions.
        coins = findTiles(5); // Coin positions.
        lava = findTiles(6); // Lava positions.
        portalsPurple = findTiles(7); // Purple portal positions.
        portalsGreen = findTiles(8); // Green portal positions.

        // Save the initial positions of boxes and coins for resets.
        initialBoxes = new ArrayList<>(boxes);
        initialCoins = new ArrayList<>(coins);

        // Reset the coin count for the new level.
        coinCount = 0;
    }

    /**
     * Moves the player up by one tile.
     */
    public void moveUp() {
        move(0, -1);
    }

    /**
     * Moves the player down by one tile.
     */
    public void moveDown() {
        move(0, 1);
    }

    /**
     * Moves the player left by one tile.
     */
    public void moveLeft() {
        move(-1, 0);
    }

    /**
     * Moves the player right by one tile.
     */
    public void moveRight() {
        move(1, 0);
    }

    /**
     * Handles the logic for moving the player.
     *
     * @param dx The change in the x-coordinate.
     * @param dy The change in the y-coordinate.
     */
    private void move(int dx, int dy) {
        // Find the player's current position on the game field.
        Point playerPos = findPlayer();
        if (playerPos == null) return; // If the player is not found, exit.

        // Calculate the new position for the player.
        int newX = playerPos.x + dx;
        int newY = playerPos.y + dy;

        // Ensure the new position is within bounds.
        if (!inBounds(newX, newY)) return;

        // Get the tile value at the new position.
        int tileVal = gameField[newY][newX];
        if (tileVal == 1) return; // Prevent movement into walls.

        // Create a Point object for the new position.
        Point nextPos = new Point(newX, newY);

        // Handle interaction with boxes.
        if (boxes.contains(nextPos)) {
            // Attempt to push the box. If unsuccessful, exit.
            if (!pushBox(nextPos, dx, dy)) {
                return;
            }
        }

        // Handle interaction with lava.
        if (lava.contains(nextPos)) {
            // Reset the level if the player steps on lava.
            resetAfterDeath();
            return;
        }

        // Restore the tile the player is leaving.
        preserveTileAfterLeaving(playerPos);

        // Handle interaction with portals.
        if (tileVal == 7 || tileVal == 8) {
            try {
                // Attempt to teleport the player through the portal.
                Point exitPos = getPortalExit(newX, newY, dx, dy);
                if (exitPos != null) {
                    if (lava.contains(exitPos)) {
                        resetAfterDeath();
                        return;
                    }
                    // Update the player's position to the portal exit.
                    newX = exitPos.x;
                    newY = exitPos.y;
                }
            } catch (PortalException e) {
                // Handle portal-related errors and reset the level.
                JOptionPane.showMessageDialog(null, e.getMessage(), "Portal Error", JOptionPane.ERROR_MESSAGE);
                resetAfterDeath();
                return;
            }
        }

        // Update the player's position on the game field.
        gameField[newY][newX] = 2;

        // Handle coin collection.
        if (coins.contains(new Point(newX, newY))) {
            // Remove the coin from the game field and update the coin count.
            coins.remove(new Point(newX, newY));
            coinCount++;
        }

        // Check if the level is completed.
        checkCompletion();
    }

    /**
     * Pushes a box in the specified direction.
     *
     * @param boxPos The current position of the box.
     * @param dx     The change in the x-coordinate.
     * @param dy     The change in the y-coordinate.
     * @return True if the box was successfully pushed; otherwise, false.
     */
    private boolean pushBox(Point boxPos, int dx, int dy) {
        // Calculate the new position for the box.
        int pushX = boxPos.x + dx;
        int pushY = boxPos.y + dy;

        // Ensure the new position is within bounds.
        if (!inBounds(pushX, pushY)) return false;

        // Get the tile value at the new position.
        int pushTile = gameField[pushY][pushX];

        // Prevent pushing the box into walls, lava, or other boxes.
        if (pushTile == 1 || pushTile == 6 || boxes.contains(new Point(pushX, pushY))) {
            return false;
        }

        // Remove the box from its current position.
        boxes.remove(boxPos);

        // Handle interaction with portals for the box.
        if (pushTile == 7 || pushTile == 8) {
            Point exitPos = getPortalExit(pushX, pushY, dx, dy);
            if (exitPos == null) {
                // If the portal exit is invalid, restore the box to its original position.
                boxes.add(boxPos);
                return false;
            }
            // Move the box to the portal exit.
            boxes.add(exitPos);
            gameField[exitPos.y][exitPos.x] = 3;
        } else {
            // Move the box to the new position.
            boxes.add(new Point(pushX, pushY));
            gameField[pushY][pushX] = 3;
        }

        // Restore the tile the box left.
        preserveTileAfterLeaving(boxPos);
        return true;
    }

    /**
     * Determines the exit position of a portal.
     *
     * @param pX The x-coordinate of the portal.
     * @param pY The y-coordinate of the portal.
     * @param dx The change in the x-coordinate.
     * @param dy The change in the y-coordinate.
     * @return The exit position of the portal as a Point.
     * @throws PortalException if the portal exit is blocked.
     */
    private Point getPortalExit(int pX, int pY, int dx, int dy) {
        // Determine the type of portal and its corresponding exit list.
        int tile = gameField[pY][pX];
        List<Point> fromList = (tile == 7) ? portalsPurple : portalsGreen;
        List<Point> toList = (tile == 7) ? portalsGreen : portalsPurple;

        // Find the index of the portal in the list.
        int idx = fromList.indexOf(new Point(pX, pY));
        if (idx < 0 || idx >= toList.size()) {
            return null; // If the portal has no corresponding exit, return null.
        }

        // Determine the exit position of the portal.
        Point otherPortal = toList.get(idx);
        int idealX = otherPortal.x + dx;
        int idealY = otherPortal.y + dy;

        // Check if the exit position is a valid free tile.
        if (!isFreeTile(idealX, idealY)) {
            throw new PortalException("Player stuck after portal! Invalid block: " + gameField[idealY][idealX]);
        }

        return new Point(idealX, idealY);
    }
    /**
     * Finds the nearest free tile from a starting position within a maximum distance.
     *
     * @param startX   The x-coordinate of the starting position.
     * @param startY   The y-coordinate of the starting position.
     * @param maxDist  The maximum distance to search for a free tile.
     * @return A Point representing the nearest free tile, or null if none is found.
     */
    private Point findNearestFreeTile(int startX, int startY, int maxDist) {
        // Use a queue to perform a breadth-first search (BFS).
        Queue<Point> queue = new LinkedList<>();
        // Keep track of visited tiles to avoid processing the same tile multiple times.
        Set<Point> visited = new HashSet<>();

        // Initialize the starting point.
        Point start = new Point(startX, startY);
        queue.add(start);
        visited.add(start);

        // Perform BFS up to the specified maximum distance.
        int distance = 0;
        while (!queue.isEmpty() && distance <= maxDist) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll(); // Dequeue the current tile.
                if (isFreeTile(cur.x, cur.y)) {
                    return cur; // Return the tile if it is free.
                }
                // Check neighboring tiles in all four cardinal directions.
                for (int[] d : new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                    int nx = cur.x + d[0];
                    int ny = cur.y + d[1];
                    Point np = new Point(nx, ny);
                    // Add the neighboring tile to the queue if it is within bounds and not visited.
                    if (!visited.contains(np) && inBounds(nx, ny)) {
                        visited.add(np);
                        queue.add(np);
                    }
                }
            }
            distance++; // Increment the distance after processing all tiles at the current level.
        }
        return null; // Return null if no free tile is found within the maximum distance.
    }

    /**
     * Checks whether a specific tile is free (i.e., walkable).
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return True if the tile is free, otherwise false.
     */
    private boolean isFreeTile(int x, int y) {
        // Ensure the tile is within bounds.
        if (!inBounds(x, y)) return false;
        // Check if the tile is not occupied by a box.
        if (boxes.contains(new Point(x, y))) return false;
        // Check if the tile is walkable (value of 0).
        int tv = gameField[y][x];
        return (tv == 0);
    }

    /**
     * Restores the original state of a tile after a player or box leaves it.
     *
     * @param oldPos The position of the tile being restored.
     */
    private void preserveTileAfterLeaving(Point oldPos) {
        // Get the original value of the tile from the initial game field.
        int origVal = initialGameField[oldPos.y][oldPos.x];
        // Restore the tile to its original value.
        if (origVal == 7 || origVal == 8) {
            gameField[oldPos.y][oldPos.x] = origVal; // Restore portal tiles.
        } else if (origVal == 4) {
            gameField[oldPos.y][oldPos.x] = 4; // Restore checkpoint tiles.
        } else if (origVal == 1) {
            gameField[oldPos.y][oldPos.x] = 1; // Restore wall tiles.
        } else {
            gameField[oldPos.y][oldPos.x] = 0; // Restore floor tiles.
        }
    }

    /**
     * Checks whether the level is complete by verifying if all boxes are on checkpoints.
     */
    private void checkCompletion() {
        for (Point b : boxes) {
            if (!checkpoints.contains(b)) {
                return; // If any box is not on a checkpoint, the level is not complete.
            }
        }
        // Update the total coin count and reset the current level's coin count.
        StartUpMenu.totalCoins += coinCount;
        coinCount = 0;
        // Load the next level.
        loadNextLevel();
    }

    /**
     * Loads the next level if available. If the player has completed all levels, display a congratulatory message.
     */
    public void loadNextLevel() {
        if (currentLevel < levels.length - 1) {
            currentLevel++; // Increment the current level index.
            loadLevel(currentLevel); // Load the next level.
        } else {
            // Display a congratulatory message if all levels are completed.
            JOptionPane.showMessageDialog(null,
                    "Congratulations! You've completed all levels!",
                    "Game Complete",
                    JOptionPane.INFORMATION_MESSAGE);

            // Return to the start menu.
            javax.swing.SwingUtilities.invokeLater(() -> {
                new StartUpMenu();
            });
        }
    }

    /**
     * Resets the game field and elements to their state at the start of the level after the player dies.
     */
    private void resetAfterDeath() {
        // Save the current state of coins.
        List<Point> savedCoins = new ArrayList<>(coins);
        int savedCount = coinCount;

        // Reset the game field to its initial state.
        gameField = clone2D(initialGameField);

        // Reset the boxes to their initial positions.
        boxes = new ArrayList<>(initialBoxes);
        for (Point b : boxes) {
            gameField[b.y][b.x] = 3; // Mark box positions on the game field.
        }

        // Restore the saved coin state.
        coins = savedCoins;
        coinCount = savedCount;

        // Ensure coins not collected are marked appropriately.
        for (Point c : initialCoins) {
            if (!coins.contains(c)) {
                gameField[c.y][c.x] = 0;
            }
        }

        // Restore the player's original position.
        Point orig = findTileInArray(initialGameField, 2);
        if (orig != null) {
            gameField[orig.y][orig.x] = 2;
        }
    }

    /**
     * Resets the game field and all elements to their initial states.
     */
    public void resetField() {
        gameField = clone2D(initialGameField); // Reset the game field.
        boxes = new ArrayList<>(initialBoxes); // Reset the box positions.
        coins = new ArrayList<>(initialCoins); // Reset the coin positions.
        coinCount = 0; // Reset the coin count.
    }

    /**
     * Finds the player's current position on the game field.
     *
     * @return A Point representing the player's position, or null if not found.
     */
    public Point findPlayer() {
        return findTileInArray(gameField, 2); // Find the tile with the value 2 (player).
    }

    /**
     * Finds the position of the first tile with a specific value in a 2D array.
     *
     * @param arr     The 2D array to search.
     * @param tileVal The value of the tile to find.
     * @return A Point representing the position of the tile, or null if not found.
     */
    private Point findTileInArray(int[][] arr, int tileVal) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                if (arr[y][x] == tileVal) {
                    return new Point(x, y); // Return the position if the tile matches the value.
                }
            }
        }
        return null; // Return null if no matching tile is found.
    }

    /**
     * Finds all tiles with a specific value in the game field.
     *
     * @param tileValue The value of the tiles to find.
     * @return A list of Points representing the positions of the tiles.
     */
    private List<Point> findTiles(int tileValue) {
        List<Point> list = new ArrayList<>();
        for (int y = 0; y < gameField.length; y++) {
            for (int x = 0; x < gameField[0].length; x++) {
                if (gameField[y][x] == tileValue) {
                    list.add(new Point(x, y)); // Add the position to the list if the tile matches the value.
                }
            }
        }
        return list; // Return the list of matching tile positions.
    }

    /**
     * Checks if a tile is within the bounds of the game field.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return True if the tile is within bounds, otherwise false.
     */
    private boolean inBounds(int x, int y) {
        return (x >= 0 && x < getRowCount() && y >= 0 && y < getColCount());
    }

    /**
     * Returns the current state of the game field.
     *
     * @return A 2D array representing the game field.
     */
    public int[][] getField() {
        return gameField;
    }

    /**
     * Gets the number of rows in the game field.
     *
     * @return The number of rows in the game field.
     */
    public int getRowCount() {
        return gameField[0].length;
    }

    /**
     * Gets the number of columns in the game field.
     *
     * @return The number of columns in the game field.
     */
    public int getColCount() {
        return gameField.length;
    }

    /**
     * Clones a 2D array to create a deep copy.
     *
     * @param src The 2D array to clone.
     * @return A new 2D array that is a deep copy of the source array.
     */
    private int[][] clone2D(int[][] src) {
        return Arrays.stream(src).map(int[]::clone).toArray(int[][]::new);
    }
}