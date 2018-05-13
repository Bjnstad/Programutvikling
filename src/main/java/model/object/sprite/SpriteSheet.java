package main.java.model.object.sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * // TODO: Need touch up
 * This class makes it possible to retrieve sprites from spriteSheet image, all spritesheets should be placed in assets then loaded in with filename.
 * @author ceciliethoresen
 */
public class SpriteSheet {

    private BufferedImage spriteSheet;
    private String filename;

    private int bits = 128;
    private int multiplier;
    private int frames;
    private boolean directional;
    private boolean staticImage;

    private int staticX;
    private int staticY;

    /**
     * //TODO: SKRIV OM
     * In this method we insert Sprite to the game, we base our sprites on 128-bit spriteSheet, any sizes over 128 will be redjected as of now, lower bits will be multiplied to 128
     * @param filename filename for spritesheet that should be fetched from.
     */
    public SpriteSheet(String filename, int bits, int frames, boolean directional){
        if(bits > this.bits || bits < 8) throw new IllegalStateException("This game does not support anything bigger than 128 bit, lowest possible is 8 bit images.");
        this.frames = frames;
        this.directional = directional;
        this.filename = filename;
        this.multiplier = this.bits / bits;

        load();
    }

    public SpriteSheet(String filename, int bits) {
        this(filename, bits, 1, false);
    }

    public void setStaticImage(int x, int y) {
        staticImage = true;
        staticX = x;
        staticY = y;
    }

    public boolean isStaticImage() {
        return staticImage;
    }

    public int getStaticX() {
        return staticX;
    }

    public int getStaticY() {
        return staticY;
    }



    /**
     * Loads the sprite.
     * @return sprite that we get from BufferedImage and describes an image with an accessible buffer of image data.
     */
    private void load(){
        BufferedImage upload = null;

        try{
            upload = ImageIO.read((new File("assets/"+filename+".png")));
        }catch (IOException e){
            e.printStackTrace(); // TODO: Default sprite on null? or currupted files?
        }

        if(upload != null) resize(upload);
    }

    private void resize(BufferedImage upload) {
        double width = upload.getWidth();
        double height = upload.getHeight();

        BufferedImage resizedImage = new BufferedImage((int)(width * multiplier), (int)(height * multiplier), upload.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(upload, 0, 0, (int)(width * multiplier), (int)(height * multiplier), null);
        g.dispose();

        this.spriteSheet = resizedImage;
    }

    /**
     * Gets the sprite and declaring it in height and width.
     * @param gridX is the width in the grid.
     * @param gridY is the heigth in the grid.
     * @return spritesheet that gets the subImage with sizes in the grid of the gameboard.
     */
    public BufferedImage getSprite(int gridX, int gridY){
        return spriteSheet.getSubimage(gridX * bits, gridY * bits, bits, bits);
    }


    public String getFilename() {
        return filename;
    }

    public int getFrames() {
        return frames;
    }

    public boolean isDirectional() {
        return directional;
    }
}
