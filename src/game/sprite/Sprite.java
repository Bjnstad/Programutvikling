package game.sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by henrytran1 on 27/02/2018.
 */
public class Sprite {

    private  BufferedImage spriteSheet;
    private  final int TILE_SIZE = 64;

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
            spriteSheet = loadSprite("player_animations_walking");

        }
        return spriteSheet.getSubimage(gridX * TILE_SIZE, gridY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
