package main.java.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class represents the sprite we upload to the game.
 * @author ceciliethoresen
 */
public class Sprite {

    private BufferedImage spriteSheet;
    private String spriteFileName;

    private int tileSize;

    /**
     * In this method we insert Sprite to the game.
     * @param spriteFileName is the filename of the animation we get from sprite.
     * @param tileSize is the size of the animation that we get from the sprite.
     */
    public Sprite(String spriteFileName, int tileSize){
        this.spriteFileName = spriteFileName;
        this.spriteSheet = loadSprite(spriteFileName);
        this.tileSize = tileSize;
    }

    /**
     * Loads the sprite.
     * Makes it available to get animation from.
     * @param file that we can upload and get from the Sprite.
     * @return sprite that we get from BufferedImage and describes an image with an accessible buffer of image data.
     */
    public BufferedImage loadSprite(String file){
        BufferedImage sprite = null;

        try{
            System.out.println("assets/"+file+".png");
            sprite = ImageIO.read((new File("assets/"+file+".png")));
        }catch (IOException e){
            e.printStackTrace();
        }
        return sprite;
    }

    /**
     * Gets the sprite and declaring it in height and width.
     * @param gridX is the width in the grid.
     * @param gridY is the heigth in the grid.
     * @return spritesheet that gets the subImage with sizes in the grid of the gameboard.
     */
    public BufferedImage getSprite(int gridX, int gridY){
        if(spriteSheet == null){
            spriteSheet = loadSprite(spriteFileName);
        }
        return spriteSheet.getSubimage(gridX * tileSize, gridY * tileSize, tileSize, tileSize);
    }
}
