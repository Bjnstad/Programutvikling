package HAC;

import HAC.character.Player;
import HAC.world.GameMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Main file for game HAC
 */
public class HAC {
    private final double TIME_FRAME = 0.1;

    private GameMap gameMap;
    private Canvas canvas;
    private Camera camera;

    private Timeline timeline;

    private Player player;

    public HAC(GameMap gameMap, Canvas canvas) {
        if(gameMap == null) throw new NullPointerException("Gamemap cannot be null");
        if(canvas == null) throw new NullPointerException("JavaFx canvas cannot be null");

        this.gameMap = gameMap;
        this.canvas = canvas;
        this.camera = new Camera(canvas);

        this.player = new Player();

        this.gameMap.setGrid(true); // turn on grid

        play();
        render();
    }

    public void play() {
        if(timeline == null) initTimeline();
        timeline.play();
    }

    private void initTimeline() {
        KeyFrame frame = new KeyFrame(Duration.seconds(TIME_FRAME), event -> render());
        timeline = new Timeline();
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gameMap.render(gc, camera);
        gc.drawImage(player.getAvatar(), camera.getCenterX(player.getSizeX()), camera.getCenterY(player.getSizeY()), player.getSizeX() * camera.getScale(), player.getSizeY() * camera.getScale());
        gc.setFill(Color.YELLOW);
        gc.fillRect(camera.getCenterX(player.getSizeX()), camera.getCenterY(player.getSizeY()), player.getSizeX() * camera.getScale(), player.getSizeY() * camera.getScale());
    }

    public boolean move(double x, double y) {
        System.out.println(Math.signum(x));
        int rx = (int)(camera.getPlayerPosition(player.getSizeX(), true) + x /  camera.getScale() - (double)player.getSizeX()/2 * Math.signum(x));
        int ry = (int)(camera.getPlayerPosition(player.getSizeY(), false) + y /  camera.getScale() - (double)player.getSizeY()/2 * Math.signum(y));
        //double rx = camera.getPlayerPosition(player.getSizeX(), true) - Math.signum(x)*((double)player.getSizeX() / 2  - 1);

        camera.move(x,y);
        System.out.println("-------");
        System.out.println("X: " + camera.getPlayerPosition(player.getSizeX(), true));
        System.out.println("Y: " + camera.getPlayerPosition(player.getSizeY(), false));


        if(!gameMap.willCollide(rx, ry)) {
            camera.move(x,y);
        } else {
            /*
            if(y == 0) {
                camera.setPOX(rx + 0.1 * Math.signum(x));
            } else if (x == 0) {
                camera.setPOY(ry + 0.1 * Math.signum(y));
            } else {
                camera.setPOX(rx + 0.1 * Math.signum(x));
                camera.setPOY(ry + 0.1 * Math.signum(y));
            }
            */
        }
        /*
        int rX = GameMap.MIN_SIZE_X - 1 - (int)playerCoordinates(x, true);
        int rY = GameMap.MIN_SIZE_Y - 1 - (int)playerCoordinates(y, false);

        if(map.willCollide(rX, rY)) {
            System.out.println("collide!");
        } else {
            System.out.println("Free ");
        }

        if(!map.willCollide(rX, rY)) {
            character.addPosX(x);
            character.addPosY(y);
            return true;
        }
        return false;*/

        return true;
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
