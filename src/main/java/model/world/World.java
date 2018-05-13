package main.java.model.world;

import main.java.controller.GameController;
import main.java.model.Camera;
import main.java.model.object.character.Enemy;
import main.java.model.object.character.Player;
import main.java.model.object.GameObject;
import main.java.model.render.Actions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * World contains everything shown on screen.
 * @author Axel Bjørnstad - s315322
 */
public class World {
    private final double ENEMY_GENERATION_RATE = 2;

    private GameController gameController; // Parent
    private GameMap gameMap;
    private int currentLevel;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private boolean godmode = true;


    public void gameloop() {
        Enemy[] enemies = getObjects(Enemy.class);
        if(enemies.length < 1) levelUp();

        if(godmode) {
            for(Enemy enemy : enemies)enemy.hit(1);
        }

        Actions actions = new Actions();
        int distribution = gameObjects.size() / Runtime.getRuntime().availableProcessors() * 2; // Times two for Hyperthreading
        if(distribution == 0) distribution = 1;

        for (int i = 0; i < gameObjects.size(); i += distribution) {
            if(i+distribution >= gameObjects.size()) {
                distribution = gameObjects.size() - i;
            }
            actions.checkGamelogic(gameObjects, i, i + distribution, this);
        }
        actions.start(gameObjects, gameMap, getCamera());
    }

    public void die() {
        gameController.die();
    }

    public Camera getCamera() {
        return gameController.getCamera();
    }

    public void loadMap(Camera camera) {
        gameMap.render(camera);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setGameMap(GameMap map) {
        this.gameMap = map;
    }

    public Player getPlayer() {
        Player[] players = getObjects(Player.class);
        if(players.length != 1) return null;
        return players[0];
    }

    public boolean move(double x, double y, Camera camera) {
        int rX = (int) (getPlayer().getPosX() + x);
        int rY = (int) (getPlayer().getPosY() - y);
        for(GameObject object : gameObjects)if(getPlayer().willCollide(object, rX, rY)) return false;

        double translateX = 0;
        double translateY = 0;
        if(rX >= ((Math.signum(x) == -1) ? -1 : 0) + camera.getZoom()/2) translateX -= camera.scale(x);
        if(rY >= ((Math.signum(y) == -1) ? 0 : -1) + camera.getZoom()/2) translateY += camera.scale(y);
        if(gameMap.getWidth() - camera.getZoom()/2 < rX) translateX = 0;
        if(gameMap.getHeight() - camera.getZoom()/2 < rY) translateY = 0;
        camera.translate(translateX, translateY);

        getPlayer().addPos(x, -y);

        return true;
    }

    public void levelUp() {
        currentLevel++;
        generateEnemies((int)(10 * currentLevel * ENEMY_GENERATION_RATE));

        System.out.println("----------");
        System.out.println("LEVEL: " + currentLevel);
        System.out.println("NUMBER OF ENEMIES: " + (int)(10 * currentLevel * ENEMY_GENERATION_RATE));
    }

    /**
     * Add gameobject to world, its only possible to add one player.
     * @param object
     */
    public void addGameObject(GameObject object) {
        if (object == null) throw new IllegalStateException("GameObject cannot be null");
        for(GameObject gameObject : gameObjects) {
            if(object.willCollide(gameObject)) throw new IllegalStateException("GameObject collide with other objects");
            if(gameObject instanceof Player && object instanceof Player) throw new IllegalStateException("A player has already been added, there could only be one.");
        }
        gameObjects.add(object);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public <T> T[] getObjects(Class<T> objectClass) {
        List<T> objects = new ArrayList<>();
        Iterator itr = gameObjects.iterator();
        while (itr.hasNext()) {
            GameObject gameObject = (GameObject)itr.next();
            if (objectClass.isInstance(gameObject)) {
                objects.add(objectClass.cast(gameObject));
            }
        }

        T[] result = (T[])Array.newInstance(objectClass, objects.size());
        for (int i = 0; i < objects.size(); i++) {
            result[i] = objects.get(i);
        }
        return result;
    }

    public <T> boolean removeObject(Class<T> objectClass) {
        if(objectClass.isInstance(Player.class)) throw new IllegalStateException("Cannot remove player.");
        return gameObjects.remove(objectClass);
    }




    /**
     *  Generates given number of enemies with random location.
     *  @param numberOfEnemies how many enemies to create.
     */
    private void generateEnemies(int numberOfEnemies) {
        Random rand = new Random();
        for (int i = 0; i < numberOfEnemies; i++) {
            Enemy enemy = new Enemy("BODY_skeleton", 1,1,rand.nextInt(20),rand.nextInt(20));
            //Enemy enemy = new Enemy("BODY_skeleton", 1,1,6, 5);
            enemy.setSpeed(1 + rand.nextInt(4));
            addGameObject(enemy);
        }
    }

    public void shoot(GameObject parent, double endX, double endY, Camera camera) {
        double pX = (parent.getPosX() + (double)parent.getSizeX()/2)* camera.getScale();
        double pY = (parent.getPosY() + (double)parent.getSizeY()/2)* camera.getScale();


        System.out.println("Click posX: " + endX);
        System.out.println("Click posY: " + endY);

        System.out.println("Player posX: " + pX);
        System.out.println("Player posY: " + pY);
        //player.shoot(camera.getScale(), pX, pY, endX, endY);
    }
}
