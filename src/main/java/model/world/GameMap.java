package main.java.model.world;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.java.model.Camera;
import main.java.model.SpriteSheet;

/**
<<<<<<< HEAD:src/main/java/HAC/world/GameMap.java
 * GameMap handles all of properties for the board, width, height, static objects and background, does not include player and enemies.
 * Currently this board only support static board size.
=======
 * This class represents the game map.
 * Gamemap contains of a count of objects, width, height and the background we get from sprite.
>>>>>>> dev:src/main/java/model/world/GameMap.java
 * @author Axel Bjørnstad - S315322
 */
public class GameMap {

    private GameObject[] gameObjects = new GameObject[1];
    private int width;
    private int height;
    private Image[][] background;

    /**
     * Game map represent how many object there is available to put on the map.
     *
     * @param width      size of the gameboard.
     * @param height     size of the gameboard.
     * @param background we gets from sprite.
     */
    public GameMap(int width, int height, SpriteSheet background) {
        if (width < 0) throw new IllegalStateException("Width should be above 0");
        if (height < 0) throw new IllegalStateException("Height should to be above 0");

        this.width = width;
        this.height = height;
        loadBackground(background);
    }

    /**
     * Allocate background sprites from storage to memory.
     *
     * @param background Sprite with 3x3 sprite setup
     */
    private void loadBackground(SpriteSheet background) {
        this.background = new Image[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.background[i][j] = SwingFXUtils.toFXImage(background.getSprite(i, j), null);
            }
        }
    }



    // TODO: Move this to an own render class?

    /**
     * This method contains the visual on the bord, and states how far camera goes vertical and horizontal.
     * @param camera shows us the visual on the board
     * @param endX horizontal
     * @param endY vertical
     */
    public void renderArea(Camera camera, int startX, int startY, int endX, int endY) {
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                renderBlock(camera, x, y);
            }
        }
    }

    /**
     * Gets the background-block from sprite and makes it visual on the board.
     *
     * @param camera shows us the visual on the board.
     * @param x      is the background in width.
     * @param y      is the background in height.
     */
    private void renderBlock(Camera camera, int x, int y) {
        camera.getGraphicsContext().drawImage(getAppropriateImage(x, y), camera.scale(x), camera.scale(y), camera.getScale(), camera.getScale());
    }

    private Image getAppropriateImage(int x, int y) {

        if(x == 0 && y == 0) return background[0][0]; // TOP LEFT
        if(y == 0 && x > 0 && x < width) return background[1][0]; // TOP
        if(x == width && y == 0) return background[2][0];// TOP RIGHT
        if(y > 0 && y < height && x == width) return background[2][1]; // Right
        if(x == width && y == height) return background[2][2]; // BOTTOM RIGHT
        if(x > 0 && x < width && y == height) return (background[1][2]); // BOTTOM
        if(x == 0 && y == height) return background[0][2]; //BOTTOM LEFT
        if(x == 0 && y > 0 && y < height) return background[0][1]; // LEFT
        if(x > 0 && x < width && y > 0 && y < height) return background[1][1]; // Center

        return null;
    }

    /**
     * This method contains if game objects collides with each other.
     *
     * @param posX position to x.
     * @param posY position to y.
     * @return false if not the statement is true.
     */
    public boolean willCollide(int posX, int posY) {

        if(posX <= -1 || posY <= -1 || posX >= width || posY >= height) return true;
        if(getGameObject(posX, posY) != null) return true;
        return false;
    }

    /**
     * Here we add gameObject into the gameboard.
     * @param gameObject states the position in height and width.
     * @return if added returns true, false if coordinates is taken or
     */
    public boolean addGameObject(GameObject gameObject) {
        int posX = gameObject.getPosX();
        int posY = gameObject.getPosY();

        for (int x = posX; x < posX + gameObject.getSizeX(); x++) {
            for (int y = posY; y < posY + gameObject.getSizeY(); y++) {
                if (willCollide(x, y)) return false;
            }
        }

        GameObject[] result = new GameObject[gameObjects.length+1];
        for (int i = 0; i < gameObjects.length; i++) result[i] = gameObjects[i];

        int re = gameObjects.length -1;
        if(re < 0) re = 0;
        // Copy in old objects

        result[re] = gameObject;

        gameObjects = result;
        return true;

    }

    /**
     * This method gets the gameObject.
     *
     * @param x the width to x in the game.
     * @param y the height  to y in the game.
     */
    private GameObject getGameObject(int x, int y) {
        if (x < 0 || x < width) return null;
        if (y < 0 || y < height) return null;
        for (GameObject gameObject : gameObjects) {
            if (x >= gameObject.getPosX() && x < gameObject.getPosX() + gameObject.getSizeX()) { // Check x coordinates
                if (y >= gameObject.getPosY() && y < gameObject.getPosY() + gameObject.getSizeY()) return gameObject;
            }
        }
        return null;
    }

    /**
     * This method gets the width of gameMap.
     *
     * @return the width to gameMap.
     */
    public int getWidth() {
        return width;
    }

    /**
     * This method gets the height to gameMap.
     *
     * @return the length to gameMap.
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method contains the visual, and sets the height and width on canvas.
     *
     * @param camera shows us the visual on the gameboard.
     */
    public void render(Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();

        gc.getCanvas().setHeight((height + 1) * camera.getScale());
        gc.getCanvas().setWidth((width + 1) * camera.getScale());

        // Render map color
        for (int y = 0; y <= height; y++) {
            for (int x = 0; x <= width; x++) {
                renderBlock(camera, x, y);
            }
        }

        for (GameObject gameObject : gameObjects) {
            if (gameObject != null) gc.drawImage(gameObject.getAsset(), gameObject.getPosX() * camera.getScale(), gameObject.getPosY() * camera.getScale(), gameObject.getSizeX() * camera.getScale(), gameObject.getSizeY() * camera.getScale());
        }
    }

    /**
     * This method draws the objects.
     * This class provides a basic capability for creating objects with draw.
     *
     * @param gameObject is a object in the game.
     * @param camera     shows us the visual on gameboard.
     */
    public void drawObject(GameObject gameObject, Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();

        gc.drawImage(gameObject.getAsset(), camera.scale(gameObject.getPosX()), camera.scale(gameObject.getPosY()), gameObject.getSizeX() * camera.getScale(), gameObject.getSizeY() * camera.getScale());

    }

    /**
     * Gets the size and position to gameobjects.
     *
     * @return size and position to gameobjects.
     */
    public GameObject[] getGameObjects() {
        return gameObjects;
    }

    public Image[][] getBackground() {
        return background;
    }
}
