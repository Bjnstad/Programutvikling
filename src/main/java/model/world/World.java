package main.java.model.world;

import main.java.model.Camera;
import main.java.model.character.Bullet;
import main.java.model.character.Enemy;
import main.java.model.character.Player;

import java.util.Random;

/**
 * Created by henrytran1 on 09/05/2018.
 */

// TODO: CONSIDER MOVE CAMERA & CONSTRUCTOR
public class World {
    private GameMap gameMap;
    private Enemy[] enemies;
    private Player player;
    private int currentLevel;


    public void render(Camera camera) {
        // Check for player collision and re-render map at enemy position.
        for(Enemy enemy : enemies) {
            if(enemy == null)return; // TODO: ADD REMOVE
            if(player.willCollide(enemy)) ///die(); TODO: ADD DIE BACK TO MAIN CONTROLLER
            if(moveEnemy(enemy)) {
                gameMap.renderArea(camera, (int)enemy.getPosX() -3, (int)enemy.getPosY() -3,  (int)enemy.getPosX() +2, (int)enemy.getPosY() +2);
                enemy.render(camera);
            }
        }

        player.render(camera);


        for (int i = 0; i < player.getBullets().size(); i++) {
            Bullet b = player.getBullets().get(i);

            for(Enemy enemy : enemies) {
                if (enemy.willCollide(b)) enemy.hit(20);
                player.getBullets().remove(b);
            }
        }

        drawStats(camera);
        gameMap.drawAllObjects(camera);
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

        System.out.println("Playet GetPosX: " + player.getPosX());
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

    public boolean move(double x, double y, Camera camera) {
        int rX = (int) (player.getPosX() + x);
        int rY = (int) (player.getPosY() - y);
        if(gameMap.willCollide(rX, rY)) return false;

        double translateX = 0;
        double translateY = 0;
        if(rX >= ((Math.signum(x) == -1) ? -1 : 0) + camera.getZoom()/2) translateX -= camera.scale(x);
        if(rY >= ((Math.signum(y) == -1) ? 0 : -1) + camera.getZoom()/2) translateY += camera.scale(y);
        if(gameMap.getWidth() - camera.getZoom()/2 < rX) translateX = 0;
        if(gameMap.getHeight() - camera.getZoom()/2 < rY) translateY = 0;
        camera.translate(translateX, translateY);

        player.addPos(x, -y);

        gameMap.renderArea(camera, rX - player.getSizeX(), rY - player.getSizeY(),  rX +  player.getSizeX(), rY + player.getSizeY());
        return true;
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
        int k = 0;
        for (int j = k = 0; k < enemies.length; j++, k++) {
            if(k == i) {
                j--;
                continue;
            }
            res[j] = enemies[k];
        }

        this.enemies = res;
    }

    /**
     * This method calculates the movement to the player.
     * @param enemy is the animation in the game. TODO: makes no sense
     *               // TODO: change name
     */
    public boolean moveEnemy(Enemy enemy) {
        double angle = Math.atan2(player.getPosX() - enemy.getPosX(), player.getPosY() - enemy.getPosY());
        double rx = enemy.getSpeed() * Math.sin(angle) / 100;
        double ry = enemy.getSpeed() * Math.cos(angle) / 100;

        if(gameMap.willCollide((int)(enemy.getPosX() + rx), (int)((enemy.getPosY() + ry)))) return false;

        enemy.addPos(rx, ry);
        return true;
    }

    /**
     * Gets the player.
     * @return the visual of player to gameboard.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the gameMap.
     * @return the visual of gameMap.
     */
    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * Gets the enemies.
     * @return the visual of enemies.
     */
    public Enemy[] getEnemies() {
        return enemies;
    }


}
