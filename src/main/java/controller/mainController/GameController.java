package main.java.controller.mainController;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;
import main.java.controller.subController.SubState;
import main.java.model.Camera;
import main.java.model.filehandler.ExportGame;
import main.java.model.inputs.GameInputs;
import main.java.model.object.Bullet;
import main.java.model.object.character.MainPlayer;
import main.java.model.object.character.Player;
import main.java.model.world.World;

/**
 * GameController is linking javafx to main game logic
 * @author Axel Bjørnstad - s315322
 */
public class GameController extends Controller {

    private final static double FPS = 60;

    private World world;
    private boolean isRunning = false;
    private Timeline timeline;

    private Camera camera;

    @FXML
    private Canvas canvas;


    public GameController() {
        super(GameState.GAME);
    }

    /**
     * Initiate on start of state.
     */
    @Override
    public void initiate() {
        this.camera = new Camera(getParent().getWidth(), canvas);
        setInputs(new GameInputs(this, 0.1));
    }

    /**
     * On state close.
     */
    @Override
    public void onClose() {
        // TODO: Auto save
        save();
    }

    /**
     * Starting gameloop running accordingly to FPS.
     */
    public void play() {
        if(timeline == null) initTimeline();

        world.renderMap(camera);
        timeline.play(); // Start timeline
        isRunning = true;
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        timeline.pause();
        isRunning = false;
        getParent().addSubState(SubState.PAUSE_MENU);
    }

    /**
     * Continue the game.
     */
    public void resume() {
        getParent().toMainView();
        play();
    }

    /**
     * Exit the game.
     */
    public void exit() {
        getParent().setState(GameState.MAIN_MENU);
    }

    /**
     * Save the game.
     */
    public void save() {
        ExportGame save = new ExportGame(world);

        //ExportGame save = new ExportGame(world.getGameMap(),getCamera(), world.getEnemies(), world.getPlayer());
    }

    public void shoot(double x, double y) {
        if(!isRunning) return;
        Player player = world.getMainPlayer();
        double mX = x - camera.getTranslateX();
        double mY = y - camera.getTranslateY();
        double pX = (player.getPosX() + (double)player.getSizeX()/2);
        double pY = (player.getPosY() + (double)player.getSizeY()/2);

        world.addGameObject(new Bullet(player, pX, pY, mX/camera.getScale(), mY/camera.getScale()));
    }

    private void gameloop() {
        ((GameInputs)inputs).loop();
        world.gameloop();
    }

    /**
     * Makes the character move vertical or horizontal.
     * @param x is horizontal.
     * @param y is vertical.
     * @return the position to character.
     */
    public boolean move(double x, double y) {
        if(!isRunning) return false; // Dont move if game not running
        MainPlayer player = world.getMainPlayer();
        return player.move(x, y, world);
    }


    /**
     * Called when character dies.
     */
    public void die() {
        //timeline.stop();
        //isRunning = false;
        //mainController.addSubState(SubState.DIE);
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



    public void setWorld(World world) {
        world.setGameController(this);
        this.world = world;
        getParent().toMainView();
        play();
    }

    public World getWorld() {
        return world;
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
}