# Sokoban Puzzle Game with Enhanced Features

## Overview
This project is a Sokoban-inspired puzzle game developed in Java, featuring enhanced gameplay mechanics and a visually appealing interface. Players solve puzzles by moving boxes onto checkpoints, collecting coins, and avoiding hazards like lava. Portals add a unique twist, teleporting players and boxes to new locations for added complexity.

## Features
- **Puzzle Mechanics**: Push boxes to checkpoints to complete levels.
- **Portals**: Teleport players and boxes for a dynamic puzzle experience.
- **Hazards**: Navigate around lava tiles and other obstacles.
- **Level System**: Multiple levels with increasing difficulty and unique layouts.
- **Reset Options**: Reset levels or handle failures with smooth game state restoration.
- **Custom Menus**: A startup menu for level selection, help, and credits.

## Technical Details
- **Programming Language**: Java
- **Code Lines**: 1,529 lines of clean and object-oriented code
- **Error Handling**: Custom exceptions like `PortalException` ensure robust logic.
- **Graphics**: The interface is built with Java Swing, including animations and styled UI.

## How to Play
1. Use `W`, `A`, `S`, `D` to move the player.
2. Push boxes onto checkpoints to complete the level.
3. Avoid hazards like lava tiles.
4. Use portals strategically to solve puzzles.
5. Press `R` to reset the current level and `ESC` for the pause menu.

## Project Goals
This project was created as a learning experience to explore object-oriented design principles, game mechanics, and interactive UI development in Java.

## Screenshots
**Sequence diagramm:**  ![image](https://github.com/user-attachments/assets/bfe1f64b-f4ab-4dba-965a-602c61195adc)
**Class diagramm:** ![image](https://github.com/user-attachments/assets/b7a1ba09-d280-4a0f-a913-7a04ad2a23e6)

## 🔗 Delegation in Our Project
Delegation is a design principle where an object hands off responsibility for a task to another helper object. This concept is widely used across our game to organize responsibilities into specific classes and methods. Here are key examples of delegation in action:

### Rendering with `GameIO`
The `GameIO` class handles all graphical rendering and input events. Instead of mixing game logic with UI, `GameIO` calls the `Game` class whenever it needs updates to the state (like moving the player or resetting the level).
For instance:
```java
KeyEvent.VK_W, () -> {
    game.moveUp(); // Delegate movement logic to the Game class
    onPlayerMove(); // Update the UI in GameIO
},
```
Here, `GameIO` delegates the logic of movement to the `Game` class while keeping its focus on rendering and input management.

### Portal Handling
In the `move` method of the `Game` class, portal logic is delegated to `getPortalExit`. This method encapsulates all logic related to determining portal exits, ensuring the main `move` method remains clean and focused on player movement:
```java
Point exitPos = getPortalExit(newX, newY, dx, dy);
if (exitPos != null) {
    newX = exitPos.x;
    newY = exitPos.y;
}
```

### Dialogs with `CustomDialog`
We use the `CustomDialog` class to handle all user interactions involving input or alerts. This separates UI-related tasks like showing level titles or error messages from other logic, maintaining a modular codebase:
```java
CustomDialog.alert(e.getMessage(), "Error");
```

## 🧩 Polymorphism in Our Code
Polymorphism allows us to use a single interface or method in different ways, promoting flexibility and code reuse. Here’s how we applied polymorphism:

### Interface: `InformationDisplay`
The `InformationDisplay` interface is implemented by classes like `AboutCreator` to provide a consistent way to retrieve and display information:
```java
public interface InformationDisplay {
    String getInformation();
}
```
Example of polymorphism:
```java
AboutCreator aboutCreator = new AboutCreator();
String creatorInfo = aboutCreator.getInformation();
CustomDialog.alert(creatorInfo, "About the Creator");
```
The `getInformation` method can be implemented differently by other classes to display unique information.

### Game Tile Rendering
The `drawTile` method in `GameIO` uses polymorphism by switching between tile types based on their value in the game field:
```java
switch (tileVal) {
    case 2: // Player
        drawTile(g2d, floorImage, px, py);
        drawTile(g2d, playerImage, px, py);
        break;
    case 3: // Box
        drawTile(g2d, floorImage, px, py);
        drawTile(g2d, boxImage, px, py);
        break;
    default: // Handle unknown tiles
        g2d.setColor(Color.PINK);
        g2d.fillRect(px, py, scale, scale);
}
```
This demonstrates polymorphic behavior where different tile types (walls, boxes, portals, etc.) are rendered appropriately based on their values.

## 🚨 Custom Exceptions
Custom exceptions allow us to handle specific error scenarios in a clean and descriptive way. In our project, we created two custom exceptions to improve error handling:

### `PortalException`
This exception is used to handle invalid portal interactions. For example, if a player tries to use a portal with no valid exit, the game throws this exception:
```java
throw new PortalException("Player stuck after portal! Invalid block: " + gameField[idealY][idealX]);
```
By using this exception, we ensure that portal-related logic is cleanly separated and errors are descriptive.

### `InvalidLevelException`
This exception is thrown when the player selects a level number that doesn’t exist:
```java
throw new InvalidLevelException("Invalid level number: " + lvl);
```
Example usage:
```java
String levelStr = CustomDialog.input("Enter level number (1..7):", "Level Selection");
try {
    int lvl = Integer.parseInt(levelStr);
    if (lvl < 1 || lvl > 7) {
        throw new InvalidLevelException("Invalid level number: " + lvl);
    }
} catch (InvalidLevelException e) {
    CustomDialog.alert(e.getMessage(), "Error");
}
```


