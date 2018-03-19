package HAC.character;

import HAC.Camera;
import HAC.sprite.Animation;
import HAC.sprite.AnimationReader;
import HAC.sprite.Sprite;
import HAC.sprite.SpriteAnimation;
import HAC.world.GameMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Character {
    private double posX = 0;
    private double posY = 0;
    public CharacterAvatar animation; // TODO: Change to sprite
    private int sizeX;
    private int sizeY;
    private ArrayList<Bullet> bullets = new ArrayList<>();




    public Character(CharacterAvatar avatar, int sizeX, int sizeY) {
        this.animation = avatar;
        this.sizeX = sizeX;
        this.sizeY = sizeY;


    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }


    public void addPosX(double speed) {
        this.posX += speed;
    }

    public void addPosY(double speed) {
        this.posY += speed;
    }

    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }






    public void shoot(double startX, double startY, double endX, double endY){
        Bullet b = new Bullet(startX, startY, endX, endY);
        bullets.add(b);
        //  animation = shootLeft;
        // animation.start();

    }

    public ArrayList getBullets(){
        return bullets;
    }

    public void render(SpriteAnimation animation, GraphicsContext gc, Camera camera) {

        for (int i = 0; i < bullets.size(); i++) {
            if(bullets.get(i).isVisible()) {
                bullets.get(i).update();
                gc.setFill(Color.YELLOW);
                gc.fillRect(camera.scaleX(bullets.get(i).getX()),camera.scaleY(bullets.get(i).getY()), 10, 5);

            }else{
                bullets.remove(i);
            }
        }

        //
        gc.drawImage(SwingFXUtils.toFXImage(animation.getSprite(), null), camera.getCenterX(), camera.getCenterY(), sizeX * camera.getScale(), sizeY * camera.getScale());
    }
    public void renderEnemy(GraphicsContext gc, Player player) {
        // RENDER PLAYER
       // gc.drawImage(avatar,offset.getOffsetX() + posX * offset.getSize() + player.getPosX(), offset.getOffsetY() + posY * offset.getSize() + player.getPosY(), sizeX * offset.getSize(),sizeY * offset.getSize());


    }
}
