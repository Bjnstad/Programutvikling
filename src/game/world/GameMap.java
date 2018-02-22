package game.world;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameMap {
    public static final int MIN_SIZE_X = 22;
    public static final int MIN_SIZE_Y = 22;

    private GameObject[][] gameObjects;
    private Color backgroundColor; // Default background color


    public GameMap(int width, int height) {
        if(width < MIN_SIZE_X || height < MIN_SIZE_Y) return; // TODO: Throw error?
        this.gameObjects = new GameObject[width][height];
        backgroundColor = Color.GRAY;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
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


    public void render(GraphicsContext gc) {
        this.render(gc, 0, 0, false);
    }

    public void render(GraphicsContext gc, double offsetX, double offsetY, boolean grid) {
        int size = 16;
        // Render map color
        for(int y = 0; y < GameMap.MIN_SIZE_Y; y++) {
            for (int x = 0; x < GameMap.MIN_SIZE_X; x++) {
                gc.setFill(backgroundColor);
                gc.fillRect(x * size + offsetY, y * size + offsetX, size, size);
            }
        }

        // Render objects
        for(int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                GameObject gameObject = getObject(x, y);
                if(gameObject != null) {
                    // Is object
                    gc.setFill(gameObject.getAsset());
                    gc.fillRect(x * size + offsetY, y * size + offsetX, size, size);
                }
            }
        }

        if(grid) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0,  0, offsetY, gc.getCanvas().getHeight());
            gc.fillRect( gc.getCanvas().getWidth() - offsetY,  0, offsetY, gc.getCanvas().getHeight());

            gc.fillRect(0, 0, gc.getCanvas().getWidth(), offsetX);
            gc.fillRect(0, gc.getCanvas().getHeight() - offsetX, gc.getCanvas().getWidth(), offsetX);
        }
    }
}
