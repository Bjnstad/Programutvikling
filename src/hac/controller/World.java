package hac.controller;

import hac.model.object.GameMap;
import application.controller.mainController.GameController;
import hac.model.Camera;
import hac.model.object.MapObject;
import hac.model.object.Bullet;
import hac.model.object.character.Enemy;
import hac.model.object.character.MainPlayer;
import hac.model.object.character.Player;
import hac.model.object.GameObject;
import hac.model.render.Actions;

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
    private final double ENEMY_GENERATION_RATE = 10;

    private GameController gameController; // Parent
    private GameMap gameMap;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    private boolean godmode = false;
    private int currentLevel;


    /**
     * Gameloop initiates defined calls for every GameObject like render and logic for each object. It distributes the load over the available threads on client.
     */
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

    /**
     * MainPlayer dies and game is over.
     */

    public void die() {
        gameController.die();
    }

    /**
     * Increase the level and generate increased number of enemies.
     */
    private void levelUp() {
        currentLevel++;
        generateEnemies((int)(0.8 * currentLevel * ENEMY_GENERATION_RATE));
        // Todo: render number of enemies created to screen?
    }

    /**
     *  Generates given number of enemies with random location.
     *  // TODO: add with threads for better performance
     *  @param numberOfEnemies how many enemies to create.
     */
    private void generateEnemies(int numberOfEnemies) {
        Random rand = new Random();
        for (int i = 0; i < numberOfEnemies; i++) {
            Enemy enemy = new Enemy("default_enemy", 1, 1, rand.nextInt(gameMap.getWidth()), rand.nextInt(gameMap.getHeight()));
            enemy.setSpeed(1 + rand.nextInt(4));
            if (!addGameObject(enemy)) i--; // Failed to add enemy retry creation
        }
        System.out.println("Enemies: " + numberOfEnemies);
    }

    /**
     * Render the whole map.
     */
    public void renderMap(Camera camera) {
        gameMap.render(camera);
    }
    

    /**
     * @return Returns MainPlayer if exist in world.
     */
    public MainPlayer getMainPlayer() {
        MainPlayer[] mainPlayers = getObjects(MainPlayer.class);
        if(mainPlayers.length != 1) return null;
        return mainPlayers[0];
    }

    public Player[] getPlayers() {
        return getObjects(Player.class);
    }

    public Enemy[] getEnemies() {
        return getObjects(Enemy.class);
    }

    public MapObject[] getMapObjects() {
        return getObjects(MapObject.class);
    }

    public Bullet[] getBullets() {
        return getObjects(Bullet.class);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Add gameobject to world, its only possible to add one player.
     * @param object
     */
    public boolean addGameObject(GameObject object) {
        if (object == null) throw new IllegalStateException("GameObject cannot be null");
        if (!object.isCollideable()) {
            gameObjects.add(object);
            return true;
        }
        for(GameObject gameObject : gameObjects) {
            if(gameObject instanceof Player || gameObject instanceof MapObject) {
                if(object.willCollide(gameObject)) return false;
            }

            if(gameObject instanceof MainPlayer && object instanceof MainPlayer) return false; // Can only have one MainPlayer
        }
        gameObjects.add(object);
        return true;
    }

    /**
     * Fetch static array from main gameObjects with the objectClass parameter.
     * @see World#getEnemies()  used to fetch enemies in array.
     * @param objectClass Class to fetch
     * @return Static array of objectClass
     */
    public <T> T[] getObjects(Class<T> objectClass) {

        // Fetch objects from the ArrayList that exist of the class objectClass
        List<T> objects = new ArrayList<>();
        Iterator itr = gameObjects.iterator();
        while (itr.hasNext()) {
            GameObject gameObject = (GameObject)itr.next();
            if (objectClass.isInstance(gameObject)) {
                objects.add(objectClass.cast(gameObject));
            }
        }

        // Convert to static array
        T[] result = (T[])Array.newInstance(objectClass, objects.size());
        for (int i = 0; i < objects.size(); i++) {
            result[i] = objects.get(i);
        }

        return result;
    }

    /**
     * Remove object from world.
     */
    @SuppressWarnings("SuspiciousMethodCalls")
    public <T> boolean removeObject(Object objectClass) {
        if(objectClass instanceof MainPlayer) return false; // Cannot remove MainPlayer
        return gameObjects.remove(objectClass);
    }

    public Camera getCamera() {
        return gameController.getCamera();
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setGameMap(GameMap map) {
        this.gameMap = map;
    }

    public GameMap getGameMap(){
        return gameMap;
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public boolean isGodmode(){
        return godmode;
    }

}
