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
        this.enemies[0] = new Enemy("BODY_skeleton", 2,2,5,5);

        play(); // Initate game
    }

    /**
     * Starting gameloop
     */
    public void play() {
        if(timeline == null) initTimeline();

        gameMap.render(camera);
        timeline.play();
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
     *
     */
    private void render() {
        if(devMode) {
            camera.renderPlayerMarker(player);
            camera.renderPlayerInfo(player);
        } else {
            player.render(camera);
            enemies[0].render(camera);
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean move(double x, double y) {
        // TODO Check for collition

        camera.translate(x, y);
        player.setPosX(camera.getPlayerX());
        player.setPosY(camera.getPlayerY());
        gameMap.renderArea(camera, (int)camera.getPlayerX() -1, (int)camera.getPlayerY() -1,  (int)camera.getPlayerX() +1, (int)camera.getPlayerY() +1);
        return true;













        /*
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
        player.animation.updateAnimation();


        int rx = (int)(camera.getPlayerX() + player.getSizeX() /2 * Math.signum(x) + x);
        int ry = (int)(camera.getPlayerY() + player.getSizeY() /2 * Math.signum(y) + y);

        System.out.println(rx);

        if(gameMap.willCollide(rx, ry)) return false;
        camera.move(x,y);
        */
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

}
