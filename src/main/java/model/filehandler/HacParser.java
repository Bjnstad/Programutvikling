package main.java.model.filehandler;

import javafx.embed.swing.SwingFXUtils;
import main.java.model.world.GameMap;
import main.java.model.world.MapObject;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * This class content
 *
 * @author
 */
public class HacParser {

    /**
     * This method helps us to parse a file.
     * ... kommer mer
     * ...
     * @param file ......
     */
    public GameMap parseFile(File file){
        GameMap map = new GameMap(20, 20, new SpriteSheet("background", 32));

        try {

            BufferedReader b = new BufferedReader(new FileReader(file));


            String[] obj = b.readLine().split("#");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] imageByte;

            for (int i = 0; i <obj.length ; i++) {
                String[] objContent = obj[i].split("&");
                imageByte = decoder.decodeBuffer(objContent[0]);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                BufferedImage image = ImageIO.read(bis);
                bis.close();

                int posY = Integer.valueOf(objContent[4]);
                int posX = Integer.valueOf(objContent[3]);
                int sizeX = Integer.valueOf(objContent[1]);
                int sizeY = Integer.valueOf(objContent[2]);
                //MapObject hf = new MapObject(SwingFXUtils.toFXImage(image, null), posY, posX, sizeX, sizeY);
                //map.addGameObject(hf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
