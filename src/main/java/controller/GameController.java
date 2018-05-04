package main.java.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import main.java.model.Camera;
import main.java.model.character.Enemy;
import main.java.model.character.Player;
import main.java.model.filehandler.ExportGame;
import main.java.model.filehandler.ImportGame;
import main.java.model.world.GameMap;

import java.io.File;
import java.util.Random;

/**
 * GameController is linking javafx to main game logic
 * @author Axel Bjørnstad - s315322
 */
public class GameController implements Controller {

    private final static double FPS = 60;

    private MainController mainController; // Parent controller
    private GameMap gameMap;
    private Camera camera;
    private boolean isRunning = false;
    private Timeline timeline;
    private Enemy[] enemies;
    private Player player;


    @FXML
    private Canvas graphics;


    /**
     * This method controls gameMap.
     * If there´s nothing in gameMap, then it creates a simple map.
     * @param gameMap is the map in the game.
     */
    public GameController(GameMap gameMap) {
        if(gameMap == null) throw new NullPointerException("Gamemap cannot be null");
        this.gameMap = gameMap;

        this.camera = new Camera();
        this.player = new Player();

        // TODO: Link with highscore algoritm
        generateEnemies(10);
    }

    /**
     * Initiate on start of state.
     */
    @Override
    public void initiate() {
        camera.setDimension(mainController.getWidth());
        camera.setCanvas(graphics);
        play();
    }

     /**
     * On state close.
     */
    @Override
    public void onClose() {
        // TODO: autosave?
        save();
    }

    /**
     * Continue the game.
     */
    public void resume() {
        mainController.toMainView();
        play();
    }

    /**
     * Exit the game.
     */
    public void exit() {
        mainController.setState(State.MAIN_MENU);
    }

    /**
     * Load the game.
     */
    public void load() {
        ImportGame importGame = new ImportGame();
        File file = new File("assets/maps/newMap.txt");
        importGame.parseFile(file);

    }

    /**
     * Gets the EventHandler.
     * @return EventHandler to add support for user input.
     */
    @Override
    public EventHandler<KeyEvent> getEventHandler() {

        // TODO: add acceleration in own controller class
        double speed = 10;
        return (event -> {
            switch (event.getCode()) {
                case W:
                    move(0, speed);
                    break;
                case A:
                    move(speed, 0);
                    break;
                case S:
                    move(0, -speed);
                    break;
                case D:
                    move(-speed, 0);
                    break;

                case ESCAPE:
                    pause();
                    mainController.addSubState(SubState.PAUSE_MENU);
                    break;

            }
        });
    }

    /**
     * Sets mainController(parent).
     * @param mainController controls all the action.
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Save the game.
     */
    public void save() {
        ExportGame save = new ExportGame(getGameMap(),getCamera(), getEnemies(), getPlayer());
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
        }
    }

    /**
     * Starting gameloop running accordingly to FPS.
     */
    public void play() {
        if(timeline == null) initTimeline();

        gameMap.render(camera); // Render the gameboard to the screen.
        timeline.play(); // Start timeline
        isRunning = true;
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        timeline.pause();
        isRunning = false;
    }


    /**
     * States that if the player-character collides with enemy-character, then player will die.
     */
    private void render() {
        // Check for player collision and re-render map at enemy position.
        for(Enemy enemy : enemies) {
            if(player.willCollide(enemy)) die();
            enemy.calculateMove(player);
            gameMap.renderArea(camera, (int)enemy.getPosX() -3, (int)enemy.getPosY() -3,  (int)enemy.getPosX() +2, (int)enemy.getPosY() +2);
        }

        // Render enemies
        for (Enemy enemy : enemies) {
            enemy.render(camera);
        }

        player.render(camera);
    }

    /**
     * Makes the character move vertical or horizontal.
     * @param x is horizontal.
     * @param y is vertical.
     * @return the position to character.
     */
    public boolean move(double x, double y) {
        if (!isRunning) return false;

        int rx = (int)(camera.getPlayerX() + x/camera.getScale() - 1.5 * Math.signum(x));
        int ry = (int)(camera.getPlayerY() + x/camera.getScale() - 1.5 * Math.signum(y));
        System.out.println("------");
        System.out.println(rx);
        System.out.println(ry);
        if(gameMap.willCollide(rx, ry)) return false;

        camera.translate(x, y);
        player.setPosX(camera.getPlayerX());
        player.setPosY(camera.getPlayerY());
        gameMap.renderArea(camera, rx -3, ry -3,  rx +2, ry +2);

        return true;
    }


    /**
     * Called when character dies.
     */
    private void die() {
        timeline.stop();
        isRunning = false;
        System.out.println("YOU DIED!");
    }

    /**
     * This method initiate a timeline.
     */
    private void initTimeline() {
        KeyFrame frame = new KeyFrame(Duration.seconds(1/FPS), event -> render());
        timeline = new Timeline();
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Shows the state when we run the game.
     * @return the running state of the game.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Gets the camera to gameboard.
     * @return the visual that camera shows us.
     */
    public Camera getCamera() {
        return camera;
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
