package HAC;

import HAC.character.Enemy;
import HAC.character.Player;
import HAC.world.GameMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Main class for game HAC
 */
public class HAC {
    private final static double FPS = 60;

    private GameMap gameMap;
    private Canvas canvas;
    private Camera camera;
    private Enemy[] enemies;
    private Timeline timeline;
    private Player player;
    private boolean devMode = false;


    public HAC(GameMap gameMap, Canvas canvas) {
        if(gameMap == null) throw new NullPointerException("Gamemap cannot be null");
        if(canvas == null) throw new NullPointerException("JavaFx canvas cannot be null");

        this.gameMap = gameMap;
        this.canvas = canvas;
        this.camera = new Camera(canvas);

        this.player = new Player();

        play();
    }

    public void play() {
        if(timeline == null) initTimeline();
        timeline.play();
    }

    private void initTimeline() {
        KeyFrame frame = new KeyFrame(Duration.seconds(1/FPS), event -> render());
        timeline = new Timeline();
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gameMap.render(gc, camera); // Render map and objects with offset

        if(devMode) {
            camera.renderPlayerMarker(player);
            camera.renderPlayerInfo(player);
        } else {
            player.render(player.animation.getAnimation(), gc, camera);
        }
    }

    public boolean move(double x, double y) {
        int rx = (int)(camera.getPlayerPosition(player.getSizeX(), true) + (double)player.getSizeX()/2 * Math.signum(x) - x / camera.getScale()- Math.signum(x));
        int ry = (int)(camera.getPlayerPosition(player.getSizeY(), false) + (double)player.getSizeY()/2 * Math.signum(y) - y / camera.getScale() - Math.signum(y));

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

        //if(gameMap.willCollide(rx, ry)) return false;
        camera.move(x,y);
        return true;
    }

    public void shoot(double endX, double endY) {
        //player.animation.startShoot();
        player.animation.startAnimation();
        player.animation.updateAnimation();
        player.shoot(camera.getPlayerPosition(player.getSizeX(), true) + player.getSizeX()/2 ,camera.getPlayerPosition(player.getSizeY(), false) + player.getSizeY()/2, endX / camera.getScale(), endY /camera.getScale());
   }

   public void setDevMode(boolean devMode) {
        this.devMode = devMode;
   }

   public void setGrid(boolean grid) {
        gameMap.setGrid(grid);
   }

   public void resize() {
        camera.calcOffset();
   }

   public void setPlayerPostion(int x, int y) {
        camera.setPlayerPosition(x, y);
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
}
