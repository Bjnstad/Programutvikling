package HAC.sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by henrytran1 on 27/02/2018.
 */
public class Sprite {

    private BufferedImage spriteSheet;
    private String spriteFileName;

    private int tileSize;

    public Sprite(String spriteFileName, int tileSize){
        this.spriteFileName = spriteFileName;
        this.spriteSheet = loadSprite(spriteFileName);
        this.tileSize = tileSize;

    }

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

    public  BufferedImage getSprite(int gridX, int gridY){
        if(spriteSheet == null){
            spriteSheet = loadSprite(spriteFileName);
        }
        return spriteSheet.getSubimage(gridX * tileSize, gridY * tileSize, tileSize, tileSize);
    }

}
