package main.java.model.world;

import main.java.controller.GameController;
import main.java.model.Camera;
import main.java.model.character.Bullet;
import main.java.model.character.Enemy;
import main.java.model.character.Player;
import main.java.model.object.GameObject;

import java.util.Random;

/**
 * World
 * @author Axel Bjørnstad - s315322
 */
public class World {
    private final double ENEMY_GENERATION_RATE = .25;

    private GameController gameController; // Parent
    private GameMap gameMap;
    private Enemy[] enemies;
    private Player player;

    private int currentLevel;

    public void gameLogic() {
        if(enemies.length == 0) levelUp(); // All enemies killed.
        for (Enemy enemy : enemies) {
            for(Bullet bullet : player.getBullets()) {
                if(bullet.willCollide(enemy)) enemy.hit(20);
                bullet.update();
            }
            moveEnemy(enemy); // Calculate new move for all enemies.
        }
    }


    public void render(Camera camera) {
        // CLEAN
        for(Bullet bullet : player.getBullets()) cleanObject(bullet, camera);
        for(Enemy enemy : enemies) cleanObject(enemy, camera);
        cleanObject(player, camera);

        // RENDER
        for (Enemy enemy : enemies) {
            camera.render(enemy);
            enemy.renderOptional(camera);
        }

        for(Bullet bullet : player.getBullets()) camera.render(bullet);
        camera.render(player);


        drawStats(camera);
        gameMap.drawAllObjects(camera);
    }

    public void levelUp() {
        currentLevel++;
        generateEnemies((int)(10 * currentLevel * ENEMY_GENERATION_RATE));
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

    /**
     *  Generates given number of enemies with random location.
     *  @param numberOfEnemies how many enemies to create.
     */
    private void generateEnemies(int numberOfEnemies) {
        enemies = new Enemy[numberOfEnemies];
        Random rand = new Random();
        for (int i = 0; i < numberOfEnemies; i++) {
            this.enemies[i] = new Enemy("BODY_skeleton", 1,1,rand.nextInt(20),rand.nextInt(20));
            this.enemies[i].setSpeed(1 + rand.nextInt(4));
        }
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

    public Player getPlayer() {
        return player;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap map) {
        this.gameMap = map;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }


    public void drawStats(Camera camera) {

        /*GraphicsContext gc = camera.getGraphicsContext();
        gameMap.renderArea(camera, 1,1,3,1);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("helvetica", 32));
        gc.fillText("Level: " + currentLevel, camera.fixedX(1), camera.fixedY(1));*/

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


    public void loadMap(Camera camera) {
        gameMap.render(camera);
    }



    public void checkDeath() {
        if(enemies.length == 0) {
            currentLevel++;
            generateEnemies((int)(10 * currentLevel * 0.25));
        }

        for (int i = 0; i < enemies.length; i++) {

            if(enemies[i].isDead()) {
                removeEnemy(i);
                return;
            }

        }
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



}
