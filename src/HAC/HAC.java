package HAC;

import HAC.character.Enemy;
import HAC.character.Player;
import HAC.world.GameMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;

/**
 * Main class for game HAC
 */
public class HAC {
    private final static double FPS = 60;

    private GameMap gameMap;
    private Camera camera;
    private Enemy[] enemies = new Enemy[1];
    private Timeline timeline;
    private Player player;
    private boolean devMode = false;
    private boolean isRunning = false;


    /**
     * ....
     * @param gameMap
     * @param canvas
     */
    public HAC(GameMap gameMap, Canvas canvas, double width, double height) {
        if(gameMap == null) throw new NullPointerException("Gamemap cannot be null");
        if(canvas == null) throw new NullPointerException("JavaFx canvas cannot be null");

        this.gameMap = gameMap;
        this.camera = new Camera(canvas, height, width);

        this.player = new Player();
        player.setPosX(camera.getPlayerX());
        player.setPosY(camera.getPlayerY());
        this.enemies[0] = new Enemy("BODY_skeleton", 2,2,5,5);

        //play(); // Initate game
    }

    /**
     * Starting gameloop running accordingly to FPS
     */
    public void play() {
        if(timeline == null) initTimeline();

        gameMap.render(camera); // Render the gameboard to the screen.
        timeline.play(); // Start timeline
        isRunning = true;
    }

    /**
     * Pauses the game
     */
    public void pause() {
        timeline.pause();
        isRunning = false;
    }


    /**
     *
     */
    private void render() {
        for(Enemy enemy : enemies) {
            if(player.willCollide(enemy)) die();
            enemy.calculateMove(player);
            gameMap.renderArea(camera, (int)enemy.getPosX() -3, (int)enemy.getPosY() -3,  (int)enemy.getPosX() +2, (int)enemy.getPosY() +2);
            enemy.render(camera);
        }

        player.render(camera);
    }

    /**
     *
     * @param x
     * @param y
     * @return
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
     * Loads new map
     * @param gameMap map to load
     */
    private void loadMap(GameMap gameMap) {
        if(gameMap == null) throw new NullPointerException("Gamemap cannot be null");
        this.gameMap = gameMap;
        gameMap.render(camera);
    }

    /**
     * resize the game
     */
    public void resize() {
        camera.calcOffset();
    }

    /**
     * Sets the player position
     * @param x position
     * @param y position
     */
    public void setPlayerPostion(int x, int y) {
        // camera.setPlayerPosition(x, y);
    }


    /**
     * Initiate timeline
     */
    private void initTimeline() {
        KeyFrame frame = new KeyFrame(Duration.seconds(1/FPS), event -> render());
        timeline = new Timeline();
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Returns the running state of the game
     * @return
     */
    public boolean isRunning() {
        return isRunning;
    }


    public Camera getCamera() {
        return camera;
    }

    public Player getPlayer() {
        return player;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
