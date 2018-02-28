package HAC.world;

import HAC.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameMap {
    private GameObject[][] gameBoard;
    private Color backgroundColor; // Default background color
    private boolean grid; // Should draw grid?

    public GameMap(int width, int height) {
        if(width < Camera.ZOOM || height < Camera.ZOOM) throw new IllegalStateException("Width/Height cant be smaller than " + Camera.ZOOM);

        this.gameBoard = new GameObject[width][height];
        backgroundColor = Color.GRAY; // Setting default background color
    }

    public boolean setGameObject(GameObject gameObjects, int posX, int posY) {
        for (int x = posX; x < posX + gameObjects.getSizeX(); x++) {
            for (int y = posY; y < posY + gameObjects.getSizeY(); y++) {
                if (willCollide(x, y)) return false;
            }
        }
        for (int x = posX; x < posX + gameObjects.getSizeX(); x++) {
            for (int y = posY; y < posY + gameObjects.getSizeY(); y++) {
                this.gameBoard[y][x] = gameObjects;
            }
        }

        return true;
    }

    public boolean willCollide(int posX, int posY) {
        if(posX < 0 || posY < 0 || posX >= getWidth() || posY >= getHeight()) return true;
        if(gameBoard[posY][posX] != null) return true;
        return false;
    }

    public GameObject getObject(int x, int y) {
        return this.gameBoard[y][x];
    }

    public int getWidth() {
        return this.gameBoard[0].length;
    }

    public int getHeight() {
        return this.gameBoard.length;
    }

    /**
     * If should draw grid
     * @param grid true for grid
     */
    public void setGrid(boolean grid) {
        this.grid = grid;
    }

    public void render(GraphicsContext gc, Camera camera) {

        // Make screen black
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Render map color
        gc.setFill(backgroundColor);
        for(int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                gc.fillRect(camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
            }
        }

        /* Render objects */
        for(int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                GameObject gameObject = getObject(x, y);
                if(gameObject != null) {
                    // Is object
                    gc.setFill(Color.RED);
                    gc.fillRect(camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());
                   // gc.drawImage(gameObject.getAsset(),x * offset.getSize() + offset.getOffsetX() + POX, y * offset.getSize() + offset.getOffsetY() + POY, gameObject.getSizeX() * offset.getSize(), gameObject.getSizeY() * offset.getSize());

                }
            }
        }

        /* Draw grid */
        if(grid) {
            gc.setFill(Color.BLACK);
            for(int y = 0; y < getHeight(); y++) {
                for (int x = 0; x < getWidth(); x++) {
                    gc.fillRect(camera.scaleX(x), camera.scaleY(y), camera.getScale(), 1);
                    gc.fillRect(camera.scaleX(x), camera.scaleY(y), 1, camera.getScale());
                }
            }
        }
    }
}
