package HAC.world;

import HAC.Camera;
import HAC.sprite.Sprite;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameMap {
    private GameObject[][] gameBoard; // Mapping for objects.
    private Sprite background; // Default background color

    /**
     * Gamemap represent how many object there is available to put on the map, ... one sprite slot?? //TODO is one x and y.
     * @param width size of the gameboard
     * @param height
     * @param background
     */
    public GameMap(int width, int height, Sprite background) {
        if(width < 0) width = 0;
        if(height < 0) height = 0;

        this.gameBoard = new GameObject[width][height];
        this.background = background;
    }

    public void renderArea(Camera camera, int startX, int startY, int endX, int endY) {
        System.out.println("SX " + startX);
        System.out.println("SY " + startY);
        System.out.println("EX " + endX);
        System.out.println("EY " + endY);

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                renderBlock(camera, x, y);
            }
        }
    }

    private void renderBlock(Camera camera, int x, int y) {
        GraphicsContext gc = camera.getGraphicsContext();

        // TOP LEFT
        if(x == 0 && y == 0) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(0, 0), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
        // TOP
        if(y == 0 && x > 0 && x < gameBoard[0].length) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(1, 0), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
        // TOP RIGHT
        if(x == gameBoard[0].length && y == 0) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(2, 0), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
        // Right
        if(y > 0 && y < getHeight() && x == gameBoard[0].length) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(2, 1), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
        // BOTTOM RIGHT
        if(x == gameBoard[0].length && y == getHeight()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(2, 2), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
        // BOTTOM
        if(x > 0 && x < gameBoard[0].length && y == getHeight()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(1, 2), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
        //BOTTOM LEFT
        if(x == 0 && y == getHeight()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(0, 2), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
        // LEFT
        if(x == 0 && y > 0 && y < getHeight()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(0, 1), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
        // Center
        if(x > 0 && x < gameBoard[0].length && y > 0 && y < getHeight()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(1, 1), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
    }



























    /**
     * Add @GameObject to the board,
     * @param gameObject
     * @param posX
     * @param posY
     * @return if added returns true, false if coordinates is taken or
     */
    public boolean setGameObject(GameObject gameObject, int posX, int posY) {
        for (int x = posX; x < posX + gameObject.getSizeX(); x++) {
            for (int y = posY; y < posY + gameObject.getSizeY(); y++) {
                if (willCollide(x, y)) return false;
            }
        }
        for (int x = posX; x < posX + gameObject.getSizeX(); x++) {
            for (int y = posY; y < posY + gameObject.getSizeY(); y++) {
                this.gameBoard[y][x] = gameObject;
            }
        }

        return true;
    }

    /**
     * If gameobjects collides
     * @param posX position to x
     * @param posY position to y
     * @return .....
     */
    public boolean willCollide(int posX, int posY) {
        if(posX <= -1 || posY <= -1 || posX >= gameBoard[0].length || posY >= getHeight()) return true;
        if(gameBoard[posY][posX] != null) return true;
        return false;
    }

    /**
     * Gets the gameobject
     * @param x the width to x in the game
     * @param y the height  to y in the game
     * @return ....
     */
    public GameObject getObject(int x, int y) {
        if (x < 0 || x < gameBoard[0].length) return null;
        if (y < 0 || y < getHeight()) return null;
        return this.gameBoard[y][x];
    }

    /**
     * Gets the width
     * @return
     */
    public int  getWidth() {
        return this.gameBoard[0].length;
    }

    /**
     * Gets the height
     * @return the length of game
     */
    public int getHeight() {
        return this.gameBoard.length;
    }

    /**
     *
     * @param camera
     */
    public void render(Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();

        gc.getCanvas().setHeight((gameBoard.length +1) * camera.getScale());
        gc.getCanvas().setWidth((gameBoard[0].length +1) * camera.getScale());
        // Make screen black
       // gc.setFill(Color.BLACK);
     //   gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Render map color
        for(int y = 0; y <=  gameBoard.length; y++) {
            for(int x = 0; x <=  gameBoard[0].length; x++) {
                renderBlock(camera, x, y);
            }
        }

        /* Render objects */
        for(int y =  (int)(camera.getPlayerY() - (double)camera.getZoom()/2); y < (int)(camera.getPlayerY() + (double)camera.getZoom()/2); y++) {
            for (int x = (int)(camera.getPlayerX() - (double)camera.getZoom()/2); x < (int)(camera.getPlayerX() + (double)camera.getZoom()/2); x++) {
                GameObject gameObject = getObject(x, y);
                if(gameObject != null) {
                    // Is object
                    gc.setFill(Color.RED);
                    gc.fillRect(camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
                    gc.drawImage(gameObject.getAsset(),camera.scaleX(x), camera.scaleY(y), gameObject.getSizeX() * camera.getScale(), gameObject.getSizeY() * camera.getScale());

                }
            }
        }

        /* Draw grid
        if(grid) {
            gc.setFill(Color.BLACK);
            for(int y = (int)(camera.getPlayerY() - (double)camera.getZoom()/2); y < (int)(camera.getPlayerY() + (double)camera.getZoom()/2); y++) {
                for (int x = (int)(camera.getPlayerX() - (double)camera.getZoom()/2); x < (int)(camera.getPlayerX() - (double)camera.getZoom()/2); x++) {
                    gc.fillRect(camera.scaleX(x), camera.scaleY(y), camera.getScale(), 1);
                    gc.fillRect(camera.scaleX(x), camera.scaleY(y), 1, camera.getScale());
                }
            }
        }*/
    }
}
