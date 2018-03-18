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

/**
 * Character in the game
 */
public class Character {
    private double posX = 0;
    private double posY = 0;
    public CharacterAvatar animation; // TODO: Change to sprite
    private int sizeX;
    private int sizeY;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    /**
     * Character in the game
     * @param avatar
     * @param sizeX
     * @param sizeY
     */
    public Character(CharacterAvatar avatar, int sizeX, int sizeY) {
        this.animation = avatar;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Sets position of x
     * @param posX
     */
    public void setPosX(double posX) {
        this.posX = posX;
    }

    /**
     * Sets position of y
     * @param posY
     */
    public void setPosY(double posY) {
        this.posY = posY;
    }

    /**
     * Adds position of x
     * @param speed
     */
    public void addPosX(double speed) {
        this.posX += speed;
    }

    /**
     * Adds position of y
     * @param speed
     */
    public void addPosY(double speed) {
        this.posY += speed;
    }

    /**
     * Gets position of x
     * @return position to x
     */
    public double getPosX() {
        return this.posX;
    }

    /**
     * Gets position of y
     * @return position of y
     */
    public double getPosY() {
        return this.posY;
    }

    /**
     * Gets the size to x
     * @return the size to x
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Gets the size of y
     * @return the size to y
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    public void shoot(double startX, double startY, double endX, double endY){
        Bullet b = new Bullet(startX, startY, endX, endY);
        bullets.add(b);
        //  animation = shootLeft;
        //   animation.start();
    }

    /**
     * Arraylist of bullets
     * @return bullets
     */
    public ArrayList getBullets(){
        return bullets;
    }

    /**
     *
     * @param animation
     * @param gc
     * @param camera
     */
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
        //RENDER PLAYER
        //gc.setFill(Color.YELLOW);

        //gc.fillRect(offset.getOffsetX() + (((double)GameMap.MIN_SIZE_X-1)/2) * offset.getSize() - sizeX/2* offset.getSize(), offset.getOffsetY() + (((double)GameMap.MIN_SIZE_Y-1)/2) * offset.getSize() - sizeY/2* offset.getSize(), sizeX*offset.getSize(), sizeY*offset.getSize());
        //gc.drawImage(avatar,offset.getOffsetX() + (((double)GameMap.MIN_SIZE_X-1)/2) * offset.getSize() - sizeX/2* offset.getSize(), offset.getOffsetY() + (((double)GameMap.MIN_SIZE_Y-1)/2) * offset.getSize() - sizeY/2* offset.getSize(), sizeX*offset.getSize(), sizeY*offset.getSize());
        gc.drawImage(SwingFXUtils.toFXImage(animation.getSprite(), null), camera.getCenterX(), camera.getCenterY(), sizeX * camera.getScale(), sizeY * camera.getScale());
    }

    /**
     *
     * @param gc
     * @param player
     */
    public void renderEnemy(GraphicsContext gc, Player player) {
        // RENDER PLAYER
       // gc.drawImage(avatar,offset.getOffsetX() + posX * offset.getSize() + player.getPosX(), offset.getOffsetY() + posY * offset.getSize() + player.getPosY(), sizeX * offset.getSize(),sizeY * offset.getSize());
    }
}
