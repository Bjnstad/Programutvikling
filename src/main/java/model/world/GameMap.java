package main.java.model.world;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.java.model.Camera;
import main.java.model.object.MapObject;
import main.java.model.filehandler.SpriteSheet;

/**
 * GameMap handles all of properties for the board, width, height, static objects and background, does not include player and enemies.
 * Currently this board only support static board size.
 * @author Axel Bjørnstad - s315322
 */
public class GameMap {
    private int width;
    private int height;
    private Image[][] background;
    private String backgroundFileName;

    /**
     * Game map represent how many object there is available to put on the map.
     * @param width      size of the gameboard.
     * @param height     size of the gameboard.
     * @param background we gets from sprite.
     */
    public GameMap(int width, int height, SpriteSheet background) {
        if (width < 0) throw new IllegalStateException("Width should be above 0");
        if (height < 0) throw new IllegalStateException("Height should to be above 0");

        this.width = width;
        this.height = height;
        this.backgroundFileName = background.getFilename();
        loadBackground(background);
    }

    /**
     * Allocate background sprites from storage to memory.
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
    public void renderArea(Camera camera, double startX, double startY, double endX, double endY) {
        for (double x = startX; x <= endX; x++) {
            for (double y = startY; y <= endY; y++) {
                renderBlock(camera, (int)x, (int)y);
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
    }

    /**
     * This method draws the objects.
     * This class provides a basic capability for creating objects with draw.
     *
     * @param mapObject is a object in the game.
     * @param camera     shows us the visual on gameboard.
     */
    public void drawObject(MapObject mapObject, Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();
        gc.drawImage(mapObject.getAsset(), camera.scale(mapObject.getPosX()), camera.scale(mapObject.getPosY()), mapObject.getSizeX() * camera.getScale(), mapObject.getSizeY() * camera.getScale());
    }

    public Image[][] getBackground() {
        return background;
    }

    public String getBackgroundFileName(){
        return backgroundFileName;
    }
}
