package game.world;

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
}
