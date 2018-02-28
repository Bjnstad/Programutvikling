package HAC;

import HAC.character.Enemy;
import HAC.character.Player;
import HAC.sprite.Animation;
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
    private final double TIME_FRAME = 0.1;

    private GameMap gameMap;
    private Canvas canvas;
    private Camera camera;
    private Enemy[] enemies;
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
        //gc.drawImage(player.getAvatar(), camera.getCenterX(player.getSizeX()), camera.getCenterY(player.getSizeY()), player.getSizeX() * camera.getScale(), player.getSizeY() * camera.getScale());

        //gc.setFill(Color.YELLOW);
        //gc.fillRect(camera.getCenterX(player.getSizeX()), camera.getCenterY(player.getSizeY()), player.getSizeX() * camera.getScale(), player.getSizeY() * camera.getScale());

//        for(Enemy enemy : enemies) enemy.renderBullet(gc);
        player.render(player.animation.getAnimation(), gc, camera);

    }

    public boolean move(double x, double y) {

        int rx = (int)(camera.getPlayerPosition(player.getSizeX(), true) + (double)player.getSizeX()/2 * Math.signum(x) - x / camera.getScale()- Math.signum(x));
        int ry = (int)(camera.getPlayerPosition(player.getSizeY(), false) + (double)player.getSizeY()/2 * Math.signum(y) - y / camera.getScale() - Math.signum(y));

        System.out.println(Math.signum(x));
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
        //double rx = camera.getPlayerPosition(player.getSizeX(), true) - Math.signum(x)*((double)player.getSizeX() / 2  - 1);


        System.out.println("-------");
        System.out.println("X: " + rx);
        System.out.println("Y: " + ry);


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

    private int postive(double n) {
        if(n > 0) return -1;
        return 1;
    }

    public void shoot(double endX, double endY) {
        player.animation.startShoot();
        player.animation.startAnimation();
        player.animation.updateAnimation();
        player.shoot(camera.getPlayerPosition(player.getSizeX(), true) ,camera.getPlayerPosition(player.getSizeY(), false), endX / camera.getScale(), endY /camera.getScale());
        System.out.println("-------");

     //   System.out.println("Scaled X: " + camera.scaleX(camera.getPlayerPosition(player.getSizeX(), true)));
     //   System.out.println("Scaled Y: " + camera.scaleY(camera.getPlayerPosition(player.getSizeY(), false)));
        System.out.println("Not Scaled X: " + camera.getPlayerPosition(player.getSizeX(), true));
        System.out.println("Not Scaled Y: " + camera.getPlayerPosition(player.getSizeY(), false));


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
