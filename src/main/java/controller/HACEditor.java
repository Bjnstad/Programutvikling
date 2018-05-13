package main.java.controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.java.model.Camera;
import main.java.model.filehandler.ExportHac;
import main.java.model.filehandler.HacParser;
import main.java.model.world.GameMap;
import main.java.model.object.MapObject;

import java.io.File;

/**
 * Main class for game main.java.HAC
 * @author
 */
public class HACEditor {
    private final static double FPS = 60;

    private GameMap gameMap;
    private Canvas canvas;
    private Camera camera;
    private boolean devMode = false;
    private HacParser hacParser;
    private ExportHac exportHac;

    /**
     * Editor to HAC that inserts gameMap and canvas.
     * @param gameMap of the game cannot be null.
     * @param canvas JavaFx cannot be null.
     */
    public HACEditor(GameMap gameMap, Canvas canvas) {
        if(gameMap == null) throw new NullPointerException("Gamemap cannot be null");
        if(canvas == null) throw new NullPointerException("JavaFx canvas cannot be null");

        this.gameMap = gameMap;
        this.canvas = canvas;

        // TODO: CANNOT HAVE STATIC VALUE
        this.camera = new Camera(800, canvas);

        this.hacParser = new HacParser();
        this.exportHac = new ExportHac();
        this.render();
    }



    /**
     * Render map and objects with offset.
     */
    public void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gameMap.render(camera);



    }

    /**
     * Moves the camera.
     * @param x left or right.
     * @param y up or down.
     * @return the position on gameboard.
     */
    public boolean move(double x, double y) {
        camera.translate(x,y);
        return true;
    }

    /**
     * Starts shoot
     * @param endX left or right.
     * @param endY up or down.
     */
    public void shoot(double endX, double endY) {
        //player.animation.startShoot();
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
     * @param grid is being called with gameMap.
     */
    public void setGrid(boolean grid) {
        //gameMap.setGrid(grid);
    }

    /**
     * Sets the player position.
     * @param x position horizontal when calling camera.
     * @param y position vertical when calling camera.
     */
    public void setPlayerPostion(int x, int y) {
        // camera.setPlayerPosition(x, y);
    }


    /**
     * Gets the camera to gameboard.
     * @return the visual that camera shows us.
     * @author ceciliethoresen
     */
    public Camera getCamera() {
        return camera;
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

    /**
     * Sets mapObject.
     * @param mapObject object int the game.
     * @return draws current mapObject to gameMap.
     */
    public boolean setGameObject(MapObject mapObject) {
        //gameMap.addGameObject(mapObject);
        exportHac.addElement(mapObject);

        gameMap.drawObject(mapObject, camera);
        return true;
    }

    /**
     * Saves a file.
     */
    public void saveFile() {
        exportHac.createFile();

    }

    /**
     * Opens file.
     * @param file implements a selected file to gameMap.
     */
    public void openFile(File file) {
        gameMap = hacParser.parseFile(file);
        this.render();


    }

}
