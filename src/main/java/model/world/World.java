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
    private final double ENEMY_GENERATION_RATE = 2.25;

    private GameController gameController; // Parent
    private GameMap gameMap;
    private int currentLevel;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();


    public void gameloop() {
        Enemy[] enemies = getObjects(Enemy.class);
        if(enemies.length < 1) levelUp();


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
            //enemies[i] = new Enemy("BODY_skeleton", 1,1,rand.nextInt(20),rand.nextInt(20));
            Enemy enemy = new Enemy("BODY_skeleton", 1,1,6, 5);
            enemy.setSpeed(1 + rand.nextInt(4));
            addGameObject(enemy);
        }
    }














































/*





    public void gameLogic() {
        if (enemies.length == 0) {
            levelUp(); // All enemies killed.
            // TODO: show new level animation
            return; // No need to calculate new moves.
        }

        RenderHandler rh = new RenderHandler();
        int workers = enemies.length / rh.getThreadCount();
        if(workers == 0) workers = 1;

        for (int i = 0; i < enemies.length; i += workers) {

        }

        for (int i = 0; i < enemies.length; i++) {
            enemies[i].hit(1);
            for (Bullet bullet : player.getBullets()) {
                if (bullet.willCollide(enemies[i])) enemies[i].hit(20);
                bullet.update();
            }
            moveEnemy(enemies[i]); // Calculate new move for all enemies.

            if(enemies[i].isDead()) removeEnemy(i);
        }
    }


    private GameObject[] allObjects() {
        //GameObject[] objects = new GameObject[1 + enemies.length + player.getBullets().size()];
        GameObject[] objects = new GameObject[1 + enemies.length];
        objects[0] = player;
        for (int i = 0; i < enemies.length; i++) objects[i] = enemies[i];
        //for (int i = 0; i < player.getBullets().size(); i++) objects[i] = player.getBullets().get(i);
        return objects;
    }


    public void render(Camera camera) {

        byte[][] board = new byte[camera.getZoom()][camera.getZoom()];

        RenderHandler clean = new RenderHandler();
        RenderHandler render = new RenderHandler();

        GameObject[] pl =allObjects();

        int workers = pl.length / clean.getThreadCount();
        if(workers == 0) workers = 1;
        for (int i = 0; i < pl.length; i += workers) {
            if(i + workers > pl.length) workers = pl.length-i;

            clean.addWorker(new CleanBoard(i, i+workers,render, camera ,board, pl, (int)(camera.getTranslateX()/camera.getScale()), (int)(camera.getTranslateY()/camera.getScale())));

        }

        clean.start();

        // Clean
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if(board[y][x] == 1)gameMap.renderArea(camera, x, y, x,y);
            }
        }
        render.start();





        /*
        // CLEAN
        byte[][] board = new byte[camera.getZoom()][camera.getZoom()];
        GameObject[] objects = new GameObject[1 + enemies.length + player.getBullets().size()];
        objects[0] = player;
        for (int i = 0; i < enemies.length; i++) objects[i] = enemies[i];
        for (int i = 0; i < player.getBullets().size(); i++) objects[i] = player.getBullets().get(i);


        RenderHandler clean = new RenderHandler();
        RenderHandler render = new RenderHandler();

        int workers = objects.length / clean.getThreadCount();
        if(workers == 0) workers = 1;

        for (GameObject object : objects) {
        }

        for (int i = 0; i < objects.length; i += workers) {
            GameObject[] to = new GameObject[workers];
            for (int j = i; j < workers; j++) {
                to[j] = objects[i + j];
            }
            clean.addWorker(new CleanBoard(render, camera ,board, to, (int)(camera.getTranslateX()/camera.getScale()), (int)(camera.getTranslateY()/camera.getScale())));
        }

        clean.start();
        render.start();


        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if(board[y][x] == 1)gameMap.renderArea(camera, x, y, x,y);
            }
        }
        /*
        // RENDER
        for(Enemy enemy : enemies) {
            camera.render(enemy);
            enemy.renderOptional(camera);
        }

        for(Bullet bullet : player.getBullets()) camera.render(bullet);
        camera.render(player);


        drawStats(camera);
        gameMap.drawAllObjects(camera);
        */


/*
    }

    public void levelUp() {
        currentLevel++;
        generateEnemies((int)(10 * currentLevel * ENEMY_GENERATION_RATE));

        System.out.println("----------");
        System.out.println("LEVEL: " + currentLevel);
        System.out.println("NUMBER OF ENEMIES: " + (int)(10 * currentLevel * ENEMY_GENERATION_RATE));
    }

    public boolean moveEnemy(Enemy enemy) {
        double angle = Math.atan2(player.getPosX() - enemy.getPosX(), player.getPosY() - enemy.getPosY());
        double rx = enemy.getSpeed() * Math.sin(angle) / 100;
        double ry = enemy.getSpeed() * Math.cos(angle) / 100;

        if(gameMap!=null) {
            for(GameObject object : gameMap.getMapObjects()) {
                if(enemy == null) return false;
                if(enemy.willCollide(object,(int)(enemy.getPosX() + rx), (int)(enemy.getPosY() + ry))) return false;
            }
        }
        enemy.addPos(rx, ry);

        if (player.willCollide(enemy)) gameController.die(); // Player dies
        return true;
    }

    public void cleanObject(GameObject object, Camera camera) {
        gameMap.renderArea(camera, object.getPosX() - object.getSizeX() -1, object.getPosY() - object.getSizeY() -1,  object.getPosX() +  object.getSizeX() +1, object.getPosY() + object.getSizeY() +1);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    private void removeEnemy(int i) {
        if(enemies.length == 1) {
            enemies = new Enemy[0];
            return;
        }

        Enemy[] res = new Enemy[enemies.length - 1];

        for (int j = 0, k = 0; k < enemies.length; j++, k++) {
            if(k == i) {
                j--;
                continue;
            }
            res[j] = enemies[k];
        }

        this.enemies = res;
    }


    public GameMap getGameMap() {
        return gameMap;
    }

    pu

    public void drawStats(Camera camera) {

        /*GraphicsContext gc = camera.getGraphicsContext();
        gameMap.renderArea(camera, 1,1,3,1);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("helvetica", 32));
        gc.fillText("Level: " + currentLevel, camera.fixedX(1), camera.fixedY(1));*

    }


    public void shoot(double endX, double endY, Camera camera) {
        double pX = (player.getPosX() + (double)player.getSizeX()/2)* camera.getScale();
        double pY = (player.getPosY() + (double)player.getSizeY()/2)* camera.getScale();


        System.out.println("Click posX: " + endX);
        System.out.println("Click posY: " + endY);

        System.out.println("Player posX: " + pX);
        System.out.println("Player posY: " + pY);
        player.shoot(camera.getScale(), pX, pY, endX, endY);
    }





    // TODO: REWORK
    public boolean move(double x, double y, Camera camera) {
        int rX = (int) (player.getPosX() + x);
        int rY = (int) (player.getPosY() - y);
        for(GameObject object : gameMap.getMapObjects())if(player.willCollide(object, rX, rY)) return false;

        double translateX = 0;
        double translateY = 0;
        if(rX >= ((Math.signum(x) == -1) ? -1 : 0) + camera.getZoom()/2) translateX -= camera.scale(x);
        if(rY >= ((Math.signum(y) == -1) ? 0 : -1) + camera.getZoom()/2) translateY += camera.scale(y);
        if(gameMap.getWidth() - camera.getZoom()/2 < rX) translateX = 0;
        if(gameMap.getHeight() - camera.getZoom()/2 < rY) translateY = 0;
        camera.translate(translateX, translateY);

        player.addPos(x, -y);

        return true;
    }


*/
}
