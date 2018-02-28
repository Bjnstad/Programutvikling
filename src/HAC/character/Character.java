package HAC.character;

import HAC.sprite.Animation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Character {
    private double posX = 0;
    private double posY = 0;
    private Image avatar; // TODO: Change to sprite
    private int sizeX;
    private int sizeY;




    public Character(Image avatar, int sizeX, int sizeY) {
        this.avatar = avatar;
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

    public Image getAvatar() {
        return avatar;
    }

    public void render(Animation animation, GraphicsContext gc) {
        // RENDER PLAYER
       // gc.setFill(Color.YELLOW);

  //     gc.fillRect(offset.getOffsetX() + (((double)GameMap.MIN_SIZE_X-1)/2) * offset.getSize() - sizeX/2* offset.getSize(), offset.getOffsetY() + (((double)GameMap.MIN_SIZE_Y-1)/2) * offset.getSize() - sizeY/2* offset.getSize(), sizeX*offset.getSize(), sizeY*offset.getSize());
     //   gc.drawImage(avatar,offset.getOffsetX() + (((double)GameMap.MIN_SIZE_X-1)/2) * offset.getSize() - sizeX/2* offset.getSize(), offset.getOffsetY() + (((double)GameMap.MIN_SIZE_Y-1)/2) * offset.getSize() - sizeY/2* offset.getSize(), sizeX*offset.getSize(), sizeY*offset.getSize());
 //       gc.drawImage(SwingFXUtils.toFXImage(animation.getSprite(), null),offset.getOffsetX() + (((double)GameMap.MIN_SIZE_X-1)/2) * offset.getSize() - sizeX/2* offset.getSize(), offset.getOffsetY() + (((double)GameMap.MIN_SIZE_Y-1)/2) * offset.getSize() - sizeY/2* offset.getSize(), sizeX*offset.getSize(), sizeY*offset.getSize());

    }
    public void renderEnemy(GraphicsContext gc, Player player) {
        // RENDER PLAYER
       // gc.drawImage(avatar,offset.getOffsetX() + posX * offset.getSize() + player.getPosX(), offset.getOffsetY() + posY * offset.getSize() + player.getPosY(), sizeX * offset.getSize(),sizeY * offset.getSize());


    }
}
