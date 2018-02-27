package game.world;

import game.character.Bullet;
import game.character.Character;
import game.character.Enemy;
import game.character.Player;
import game.utils.Offset;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameWorld {
    private int currentLevel;
    private Player player;
    private Enemy[] enemies;
    private GameMap map;
    private Offset offset;
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    private Image tempZombie = new Image("https://s3.amazonaws.com/gameartpartnersimagehost/wp-content/uploads/edd/2015/09/Zombie-Army-Character-Royalty-Free-Game-Art.png");


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

        player.render(gc, offset);

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
        return move(player, x, y);
    }

    public void shoot(double endX, double endY){
        Bullet b = new Bullet(offset.getOffsetX() + ((double)GameMap.MIN_SIZE_X/2) * offset.getSize() - player.getSizeX()/2* offset.getSize(), offset.getOffsetY() + ((double)GameMap.MIN_SIZE_Y/2) * offset.getSize() - player.getSizeY()/2* offset.getSize(), endX, endY);
        bullets.add(b);
        System.out.println("Added bullet");
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
