package HAC;

import HAC.character.Enemy;
import HAC.character.Player;
import HAC.editor.ExportHac;
import HAC.editor.HacParser;
import HAC.world.GameMap;
import HAC.world.GameObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

/**
 * Main class for game HAC
 */
public class HacEditor {
    private final static double FPS = 60;

    private GameMap gameMap;
    private Canvas canvas;
    private Camera camera;
    private Enemy[] enemies = new Enemy[1];
    private Timeline timeline;
    private Player player;
    private boolean devMode = false;
    private HacParser hacParser;
    private ExportHac exportHac;


    /**
     * ....
     * @param gameMap
     * @param canvas
     */
    public HacEditor(GameMap gameMap, Canvas canvas) {
        if(gameMap == null) throw new NullPointerException("Gamemap cannot be null");
        if(canvas == null) throw new NullPointerException("JavaFx canvas cannot be null");

        this.gameMap = gameMap;
        this.canvas = canvas;
        this.camera = new Camera(canvas, 200, 200);

        this.player = new Player();
        this.hacParser = new HacParser();
        this.exportHac = new ExportHac();

        this.enemies[0] = new Enemy("BODY_skeleton", 2,2,213,14);
        this.render();

    }



    /**
     *
     */
    public void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gameMap.render(gc, camera); // Render map and objects with offset

    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean move(double x, double y) {
        player.animation.setWalkingDown();

        if(x == 0 && y < 1){
        }else if(x > 1 && y == 0){
            player.animation.setWalkLeft();
        }else if(x == 0.0 && y > 1){
            player.animation.setWalkingUp();
        }else if (x < 0 && y == 0){
            player.animation.setWalkRight();
        }

        player.animation.startAnimation();

        int rx = (int)(camera.getPlayerX() + player.getSizeX() /2 * Math.signum(x) + x);
        int ry = (int)(camera.getPlayerY() + player.getSizeY() /2 * Math.signum(y) + y);

        System.out.println(rx);

        if(gameMap.willCollide(rx, ry)) return false;
        camera.move(x,y);
        return true;
    }

    /**
     *
     * @param endX
     * @param endY
     */
    public void shoot(double endX, double endY) {
        //player.animation.startShoot();
        player.animation.startAnimation();
        player.animation.updateAnimation();
        //player.shoot(camera.getPlayerPosition(player.getSizeX(), true) + player.getSizeX()/2 ,camera.getPlayerPosition(player.getSizeY(), false) + player.getSizeY()/2, endX / camera.getScale(), endY /camera.getScale());
    }

    /**
     * Sets the devmode
     * @param devMode
     */
    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    /**
     * Sets the grid of the game
     * @param grid
     */
    public void setGrid(boolean grid) {
        //gameMap.setGrid(grid);
    }

    /**
     * resize the game
     */
    public void resize() {
        camera.calcOffset();
    }

    /**
     * Sets the okayer position
     * @param x position
     * @param y position
     */
    public void setPlayerPostion(int x, int y) {
        // camera.setPlayerPosition(x, y);
    }

    /**
     * Loads new map
     * @param gameMap map to load
     */
    private void loadMap(GameMap gameMap) {
        if(gameMap == null) throw new NullPointerException("Gamemap cannot be null");

        this.gameMap = gameMap;

        render();
    }

    public boolean setGameObject(GameObject gameObjects, int posX, int posY) {
        gameMap.setGameObject(gameObjects, posX, posY);
        exportHac.addElement(gameObjects, posX, posY);
        this.render();

        return true;
    }

    public void saveFile() {
        exportHac.createFile();

    }

    public void openFile(File file) {
        hacParser.parseFile(file);

    }

}
