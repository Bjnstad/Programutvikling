package game.world;

public class GameMap {
    private int width;
    private int height;
    private GameObject[][] gameObjects;


    public void setGameObjects(GameObject gameObjects, int posX, int posY) {
        this.gameObjects[posX][posY] = gameObjects;
    }

    public boolean willCollide(int posX, int posY) {
        return false;
    }
}
