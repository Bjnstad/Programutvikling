package game.world;

import game.character.Player;
import game.utils.Offset;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameMap {
    public static final int MIN_SIZE_X = 22;
    public static final int MIN_SIZE_Y = 22;

    private GameObject[][] gameObjects;
    private Color backgroundColor; // Default background color
    int calc = 0;


    public GameMap(int width, int height) {
        if(width < MIN_SIZE_X || height < MIN_SIZE_Y) return; // TODO: Throw error?
        this.gameObjects = new GameObject[width][height];
        backgroundColor = Color.GRAY;
    }

    public boolean setGameObject(GameObject gameObjects, int posX, int posY) {
        for (int x = posX; x < posX + gameObjects.getSizeX(); x++) {
            for (int y = posY; y < posY + gameObjects.getSizeY(); y++) {
                if (willCollide(x, y)) return false;
            }
        }
        for (int x = posX; x < posX + gameObjects.getSizeX(); x++) {
            for (int y = posY; y < posY + gameObjects.getSizeY(); y++) {
                this.gameObjects[y][x] = gameObjects;
            }
        }

        return true;
    }

    public boolean willCollide(int posX, int posY) {
        return posX >= 0 && posY >= 0 && posX <= getWidth() && posY <= getHeight() && gameObjects[posX] == null && gameObjects[posY] == null;
    }

    public GameObject getObject(int x, int y) {
        return this.gameObjects[y][x];
    }

    public int getWidth() {
        if(gameObjects == null || gameObjects.length < 1) return -1; // If no other rows
        return this.gameObjects[0].length;
    }

    public int getHeight() {
        if(gameObjects == null) return -1;
        return this.gameObjects.length;
    }

    public void render(GraphicsContext gc, Offset offset, boolean grid, Player player) {
        double POX = 0; // Player offset X
        double POY = 0; // Player offset Y
        if (player != null) {
            POX = player.getPosX();
            POY = player.getPosY();
        }

        // Render map color
        gc.setFill(backgroundColor);
        for(int y = 0; y < GameMap.MIN_SIZE_Y; y++) {
            for (int x = 0; x < GameMap.MIN_SIZE_X; x++) {
                gc.fillRect(x * offset.getSize() + offset.getOffsetX() + POX, y * offset.getSize() + offset.getOffsetY() + POY, offset.getSize(), offset.getSize());
            }
        }

        /* Render objects */
        for(int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                GameObject gameObject = getObject(x, y);
                if(gameObject != null) {
                    // Is object
                    gc.drawImage(gameObject.getAsset(), gameObject.getSizeX(), gameObject.getSizeY());
                    gc.setFill(Color.RED);
                    gc.fillRect(x * offset.getSize() + offset.getOffsetX() + POX, y * offset.getSize() + offset.getOffsetY() + POY, offset.getSize(), offset.getSize());
                }
            }
        }

        /* Draw grid */
        gc.setFill(Color.BLACK);
        if(grid) {
            for(int y = 0; y < MIN_SIZE_X; y++) {
                for (int x = 0; x < MIN_SIZE_X; x++) {
                    gc.fillRect(x * offset.getSize() + offset.getOffsetX() + POX, y * offset.getSize() + offset.getOffsetY() + POY, offset.getSize(), 1);
                    gc.fillRect(x * offset.getSize() + offset.getOffsetX() + POX, y * offset.getSize() + offset.getOffsetY() + POY, 1, offset.getSize());
                }
            }
        }
    }
}
