package main.java.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import main.java.model.Camera;
import main.java.model.character.Bullet;
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

    private int currentLevel;

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

        currentLevel = 1;
    }

    /**
     * Initiate on start of state.
     */
    @Override
    public void initiate() {
        this.camera = new Camera(mainController.getWidth(), graphics);
        this.player = new Player();
        generateEnemies(10);
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
        File file = new File("assets/maps/newMap.txt");
        ImportGame ig =  new ImportGame(file);

        gameMap = ig.getMap();
        player = ig.getPlayer();
        enemies = ig.getEnemies();

    }

    /**
     * Gets the EventHandler.
     * @return EventHandler to add support for user input.
     */
    @Override
    public EventHandler<KeyEvent> getEventHandler() {

        // TODO: add acceleration in own controller class
        double speed = 0.4;
        return (event -> {
            switch (event.getCode()) {
                case W:
                    move(0, speed);
                    break;
                case A:
                    move(-speed, 0);
                    break;
                case S:
                    move(0, -speed);
                    break;
                case D:
                    move(speed, 0);
                    break;
                case ESCAPE:
                    pause();
                    mainController.addSubState(SubState.PAUSE_MENU);
                    break;

            }
        });
    }

    public EventHandler<MouseEvent> getMouseEventHandler(){

        return (event -> {

            double mX = event.getX() - camera.getTranslateX();
            double mY = event.getY() - camera.getTranslateY();
            double pX = (player.getPosX() + (double)player.getSizeX()/2)* camera.getScale();
            double pY = (player.getPosY() + (double)player.getSizeY()/2)* camera.getScale();

            player.shoot(camera.getScale(), pX, pY, mX, mY);
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
            this.enemies[i].setSpeed(1 + rand.nextInt(4));
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



    private void render() {
        /**
         * States that if the player-character collides with enemy-character, then player will die.
         */
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


        for (int i = 0; i < player.getBullets().size(); i++) {
            Bullet b = player.getBullets().get(i);

            for(Enemy enemy : enemies) {
                if (enemy.willCollide(b)) enemy.hit(20);
                player.getBullets().remove(b);
            }
        }

        /*
        GraphicsContext gc = camera.getGraphicsContext();
        gameMap.renderArea(camera, 1,1,3,1);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("helvetica", 32));
        gc.fillText("Level: " + currentLevel, camera.fixedX(1), camera.fixedY(1));
        */
    }

    /**
     * Makes the character move vertical or horizontal.
     * @param x is horizontal.
     * @param y is vertical.
     * @return the position to character.
     */
    public boolean move(double x, double y) {
        if(!isRunning) return false; // Dont move if game not running

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
     * Called when character dies.
     */
    private void die() {
        timeline.stop();
        isRunning = false;
        mainController.addSubState(SubState.DIE);
    }

    /**
     * This method initiate a timeline.
     */
    private void initTimeline() {
        KeyFrame frame = new KeyFrame(Duration.seconds(1/FPS), event -> gameloop());
        timeline = new Timeline();
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void gameloop() {
        if(enemies.length == 0) {
            currentLevel++;
            generateEnemies((int)(10 * currentLevel * 0.25));
        }

        checkDeath();
        render();
    }

    private void checkDeath() {
        for (int i = 0; i < enemies.length; i++) {
            if(enemies[i].isDead()) {
                removeEnemy(i);
                return;
            }
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