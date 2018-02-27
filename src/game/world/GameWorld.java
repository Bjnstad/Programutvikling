package game.world;

import game.character.Bullet;
import game.character.Character;
import game.character.Enemy;
import game.character.Player;
import game.sprite.Animation;
import game.sprite.Sprite;
import game.utils.Offset;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameWorld {
    private int currentLevel;
    private Player player;
    private Enemy[] enemies;
    private GameMap map;
    private Offset offset;
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    private Image tempZombie = new Image("https://s3.amazonaws.com/gameartpartnersimagehost/wp-content/uploads/edd/2015/09/Zombie-Army-Character-Royalty-Free-Game-Art.png");
    // Images for each animation

    Sprite sprite = new Sprite();

    public void setWalkingSprite(String file) {
        sprite.loadSprite(file);
    }
    Sprite spriteShooting = new Sprite();

    public void setSpriteShooting(String file){
        spriteShooting.loadSprite(file);
    }



    private BufferedImage[] walkingDownAnimation = {sprite.getSprite(0, 2), sprite.getSprite(1, 2), sprite.getSprite(2,2),sprite.getSprite(3,2), sprite.getSprite(4,2), sprite.getSprite(5,2), sprite.getSprite(6,2), sprite.getSprite(7,2), sprite.getSprite(8,2)};

    private BufferedImage[] walkingLeftAnimation = {sprite.getSprite(0, 1), sprite.getSprite(1, 1), sprite.getSprite(2,1),sprite.getSprite(3,1), sprite.getSprite(4,1), sprite.getSprite(5,1), sprite.getSprite(6,1), sprite.getSprite(7,1), sprite.getSprite(8,1)};

    private BufferedImage[] walkingRightAnimation = {sprite.getSprite(0, 3), sprite.getSprite(1, 3), sprite.getSprite(2,3),sprite.getSprite(3,3), sprite.getSprite(4,3), sprite.getSprite(5,3), sprite.getSprite(6,3), sprite.getSprite(7,3), sprite.getSprite(8,3)};

    private BufferedImage[] walkingUpAnimation = {sprite.getSprite(0, 0), sprite.getSprite(1, 0), sprite.getSprite(2,0),sprite.getSprite(3,0), sprite.getSprite(4,0), sprite.getSprite(5,0), sprite.getSprite(6,0), sprite.getSprite(7,0), sprite.getSprite(8,0)};

//    private BufferedImage[] shootLeftAnimation = {spriteShooting.getSprite(0,1), spriteShooting.getSprite(1,1), spriteShooting.getSprite(2,1), spriteShooting.getSprite(3,1), spriteShooting.getSprite(4,1), spriteShooting.getSprite(5,1), spriteShooting.getSprite(6,1),spriteShooting.getSprite(7,1),spriteShooting.getSprite(8,1), spriteShooting.getSprite(9,1), spriteShooting.getSprite(10,1),spriteShooting.getSprite(11,1),spriteShooting.getSprite(12,1)};

    private BufferedImage[] standingAnimation = {sprite.getSprite(3, 0)};

    // These are animation states
    private Animation walkingUp = new Animation(walkingUpAnimation, 2);
    private Animation walkingDown = new Animation(walkingDownAnimation, 2);
    private Animation walkLeft = new Animation(walkingLeftAnimation, 2);
    private Animation walkRight = new Animation(walkingRightAnimation, 2);
    private Animation standing = new Animation(standingAnimation, 2);
   // private Animation shootLeft = new Animation(shootLeftAnimation, 2);

    // This is the default standing animation
    private Animation animation = standing;

    public GameWorld(GameMap map) {
        if(map == null) throw new NullPointerException(); // Map cannot be null
        this.map = map;
        this.player = new Player();
        this.enemies = new Enemy[1];
        this.currentLevel = 0;

        this.enemies[0] = new Enemy(tempZombie, 4, 4, 6, 6);
    }

    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        calcOffset(canvas); // Calculate values for offset

        // Make screen black
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Render map with objects
        map.render(gc, offset, true, player);


        // Render characters
        for (Enemy enemy : enemies) {
            enemy.renderEnemy(gc, offset, player);
        }

        //Render bullets
        for (Bullet bullet : bullets){

            gc.setFill(Color.YELLOW);
            gc.fillRect(bullet.getX(),bullet.getY(), 10, 5);
        }

        //player.render(gc, offset);
        player.render(animation, gc, offset);

    }

    private void calcOffset(Canvas c) {
        Offset o = new Offset();
        if(c.getWidth() < c.getHeight()) {
            o.setSize(c.getWidth() / GameMap.MIN_SIZE_X);
            o.setOffsetY((c.getHeight() - c.getWidth())/2);
        } else {
            o.setSize(c.getHeight() / GameMap.MIN_SIZE_Y);
            o.setOffsetX((c.getWidth() - c.getHeight())/2);
        }
        offset = o;
    }

    public boolean movePlayer(double x, double y) {
        if(x == 0 && y < 1){
            animation = walkingDown;
        }else if(x > 1 && y == 0){
            animation = walkLeft;
        }else if(x == 0.0 && y > 1){
            animation = walkingUp;
        }else if (x < 0 && y == 0){
            animation = walkRight;
        }
        System.out.println("x: " + x + " y: " + y);
        animation.start();
        animation.update();

        return move(player, x, y);
    }

    public void shoot(double endX, double endY){
        Bullet b = new Bullet(offset.getOffsetX() + ((double)GameMap.MIN_SIZE_X/2) * offset.getSize() - player.getSizeX()/2* offset.getSize(), offset.getOffsetY() + ((double)GameMap.MIN_SIZE_Y/2) * offset.getSize() - player.getSizeY()/2* offset.getSize(), endX, endY);
        bullets.add(b);
      //  animation = shootLeft;
     //   animation.start();



    }

    public ArrayList getBullets(){
        return bullets;
    }

    public boolean move(Character character, double x, double y) {
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
        return false;
    }

    private double playerCoordinates(double pos, boolean isX) {
        if(isX) return (offset.getOffsetX() + (((double)GameMap.MIN_SIZE_X-1)/2) * offset.getSize() - player.getSizeX()/2* offset.getSize() + player.getPosX())/offset.getSize();
        return (offset.getOffsetY() + (((double)GameMap.MIN_SIZE_Y-1)/2) * offset.getSize() - player.getSizeY()/2* offset.getSize() + player.getPosY())/offset.getSize();
    }

    public Offset getOffset() {
        return offset;
    }
}
