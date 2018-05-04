package main.java.HAC;

import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.java.HAC.character.Enemy;
import main.java.HAC.character.Player;
import main.java.HAC.editor.ExportHac;
import main.java.HAC.editor.HacParser;
import main.java.HAC.world.GameMap;
import main.java.HAC.world.GameObject;
import main.java.view.Camera;

import java.io.File;

/**
 * Main class for game main.java.HAC
 */
public class HACEditor {
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
    public HACEditor(GameMap gameMap, Canvas canvas) {
        if(gameMap == null) throw new NullPointerException("Gamemap cannot be null");
        if(canvas == null) throw new NullPointerException("JavaFx canvas cannot be null");

        this.gameMap = gameMap;
        this.canvas = canvas;
        this.camera = new Camera();

        this.hacParser = new HacParser();
        this.exportHac = new ExportHac();
        this.render();
    }



    /**
     *
     */
    public void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gameMap.render(camera); // Render map and objects with offset

    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean move(double x, double y) {
        camera.translate(x,y);
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

    public boolean setGameObject(GameObject gameObject) {
        gameMap.addGameObject(gameObject);
        exportHac.addElement(gameObject);

        gameMap.drawObject(gameObject, camera);
        return true;
    }

    public void saveFile() {
        exportHac.createFile();

    }

    public void openFile(File file) {
        gameMap = hacParser.parseFile(file);
        this.render();



    }

}
