package HAC.world;

import HAC.Camera;
import HAC.sprite.Sprite;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class GameMap {
    private GameObject[] gameObjects;
    private int width;
    private int height;
    private Image[][] background;

    /**
     * Gamemap represent how many object there is available to put on the map, ... one sprite slot?? //TODO is one x and y.
     * @param width size of the gameboard
     * @param height
     * @param background
     */
    public GameMap(int width, int height, Sprite background) {
        if(width < 0) width = 0;
        if(height < 0) height = 0;
        this.width = width;
        this.height = height;

        this.gameObjects = new GameObject[1];
        // Import background
        this.background = new Image[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.background[i][j] = SwingFXUtils.toFXImage(background.getSprite(i, j), null);
            }
        }
    }

    public void renderArea(Camera camera, int startX, int startY, int endX, int endY) {
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                renderBlock(camera, x, y);
            }
        }
    }

    private void renderBlock(Camera camera, int x, int y) {
        GraphicsContext gc = camera.getGraphicsContext();
        Double size = camera.getScale();

        if(x == 0 && y == 0) gc.drawImage(background[0][0], camera.scaleX(x), camera.scaleY(y), size, size); // TOP LEFT
        if(y == 0 && x > 0 && x < width) gc.drawImage(background[1][0], camera.scaleX(x), camera.scaleY(y), size, size); // TOP
        if(x == width && y == 0) gc.drawImage(background[2][0], camera.scaleX(x), camera.scaleY(y), size, size);// TOP RIGHT
        if(y > 0 && y < height && x == width) gc.drawImage(background[2][1], camera.scaleX(x), camera.scaleY(y), size, size); // Right
        if(x == width && y == height) gc.drawImage(background[2][2], camera.scaleX(x), camera.scaleY(y), size, size); // BOTTOM RIGHT
        if(x > 0 && x < width && y == height) gc.drawImage(background[1][2], camera.scaleX(x), camera.scaleY(y), size, size); // BOTTOM
        if(x == 0 && y == height) gc.drawImage(background[0][2], camera.scaleX(x), camera.scaleY(y), size, size); //BOTTOM LEFT
        if(x == 0 && y > 0 && y < height) gc.drawImage(background[0][1], camera.scaleX(x), camera.scaleY(y), size, size); // LEFT
        if(x > 0 && x < width && y > 0 && y < height) gc.drawImage(background[1][1], camera.scaleX(x), camera.scaleY(y), size, size); // Center
    }

    /**
     * If gameobjects collides
     * @param posX position to x
     * @param posY position to y
     * @return .....
     */
    public boolean willCollide(int posX, int posY) {
        if(posX <= -1 || posY <= -1 || posX >= width || posY >= height) return true;
        if(getGameObject(posX, posY) != null) return true;
        return false;
    }


    /**
     * Add @GameObject to the board,
     * @param gameObject
     *
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

        GameObject[] result = new GameObject[this.gameObjects.length];

        // Copy in old objects
        for (int i = 0; i < gameObjects.length; i++) result[i] = gameObjects[i];

        result[result.length -1] = gameObject;
        return true;
    }



    /**
     * Gets the gameobject
     * @param x the width to x in the game
     * @param y the height  to y in the game
     * @return ....
     */
    public GameObject getGameObject(int x, int y) {
        if (x < 0 || x < width) return null;
        if (y < 0 || y < height) return null;
        for (GameObject gameObject : gameObjects) {
            if(x >= gameObject.getPosX() && x < gameObject.getPosX() + gameObject.getSizeX()) { // Check x coordinates
                if(y >= gameObject.getPosY() && y < gameObject.getPosY() + gameObject.getSizeY()) return gameObject;
            }
        }

        return null;
    }

    /**
     * Gets the width
     * @return
     */
    public int  getWidth() {
        return width;
    }

    /**
     * Gets the height
     * @return the length of game
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param camera
     */
    public void render(Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();

        gc.getCanvas().setHeight((height +1) * camera.getScale());
        gc.getCanvas().setWidth((width +1) * camera.getScale());

        // Render map color
        for(int y = 0; y <=  height; y++) {
            for(int x = 0; x <=  width; x++) {
                renderBlock(camera, x, y);
            }
        }

        for (GameObject gameObject : gameObjects) {
            if(gameObject != null) gc.drawImage(gameObject.getAsset(), gameObject.getPosX(), gameObject.getPosY(), gameObject.getSizeX(), gameObject.getSizeY());

        }
    }

    public void drawObject(GameObject gameObject, Camera camera){
        GraphicsContext gc = camera.getGraphicsContext();

        gc.drawImage(gameObject.getAsset(),camera.scaleX(gameObject.getPosX()), camera.scaleY(gameObject.getPosY()), gameObject.getSizeX() * camera.getScale(), gameObject.getSizeY() * camera.getScale());

    }
}
