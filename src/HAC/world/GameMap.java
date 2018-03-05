package HAC.world;

import HAC.Camera;
import HAC.sprite.Sprite;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameMap {
    private GameObject[][] gameBoard;
    private Sprite background; // Default background color
    private boolean grid; // Should draw grid?

    public GameMap(int width, int height, Sprite background) {
        if(width < Camera.ZOOM || height < Camera.ZOOM) throw new IllegalStateException("Width/Height cant be smaller than " + Camera.ZOOM);

        this.gameBoard = new GameObject[width][height];
        this.background = background;
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
        if(posX <= -1 || posY <= -1 || posX >= getWidth() || posY >= getHeight()) return true;
        if(gameBoard[posY][posX] != null) return true;
        return false;
    }

    public GameObject getObject(int x, int y) {
        if (x < 0 || x < getWidth()) return null;
        if (y < 0 || y < getHeight()) return null;
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

        // Render map colora
        for(int y =  (int)(camera.getPlayerY(1) - (double)camera.ZOOM/2); y < (int)(camera.getPlayerY(1) + (double)camera.ZOOM/2); y++) {
            for (int x = (int)(camera.getPlayerX(1) - (double)camera.ZOOM/2); x < (int)(camera.getPlayerX(1) + (double)camera.ZOOM/2); x++) {
                // TOP LEFT
                if(x == 0 && y == 0) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(0, 0), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());

                // TOP
                if(y == 0 && x > 0 && x < getWidth()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(1, 0), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());

                // TOP RIGHT
                if(x == 0 && y == getHeight()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(0, 2), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());

                // Right
                if(y > 0 && y < getHeight() && x == getWidth()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(2, 1), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());

                // BOTTOM RIGHT
                if(x == getWidth() && y == getHeight()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(2, 2), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());

                // BOTTOM
                if(x > 0 && x < getWidth() && y == getHeight()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(1, 2), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());


                //BOTTOM LEFT


                // LEFT
                if(x == 0 && y > 0 && y < getHeight()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(0, 1), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());


                // Center
                if(x > 0 && x < getWidth() && y > 0 && y < getHeight()) gc.drawImage(SwingFXUtils.toFXImage(background.getSprite(1, 1), null), camera.scaleX(x), camera.scaleY(y), camera.getScale(), camera.getScale());


                if(x > 0 && x < getWidth() && y > 0 && y < getHeight()) {


                }
            }
        }

        /* Render objects */
        for(int y = (int)(camera.getPlayerY(1) - (double)camera.ZOOM/2); y < (int)(camera.getPlayerY(1) + (double)camera.ZOOM/2); y++) {
            for (int x = (int)(camera.getPlayerX(1) - (double)camera.ZOOM/2); x < (int)(camera.getPlayerX(1) + (double)camera.ZOOM/2); x++) {
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
            for(int y = (int)(camera.getPlayerY(1) - (double)camera.ZOOM/2); y < (int)(camera.getPlayerY(1) + (double)camera.ZOOM/2); y++) {
                for (int x = (int)(camera.getPlayerX(1) - (double)camera.ZOOM/2); x < (int)(camera.getPlayerX(1) - (double)camera.ZOOM/2); x++) {
                    gc.fillRect(camera.scaleX(x), camera.scaleY(y), camera.getScale(), 1);
                    gc.fillRect(camera.scaleX(x), camera.scaleY(y), 1, camera.getScale());
                }
            }
        }
    }
}
