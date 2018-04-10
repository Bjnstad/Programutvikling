package HAC.editor;

import HAC.sprite.Sprite;
import HAC.world.GameMap;
import HAC.world.GameObject;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import sun.misc.BASE64Decoder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;




/**
 * Created by henrytran1 on 06/03/2018.
 */
public class HacParser {




    public GameMap parseFile(File file){
        GameMap map = new GameMap(20, 20, new Sprite("background", 32));;
        try {

            BufferedReader b = new BufferedReader(new FileReader(file));
            

            String[] obj = b.readLine().split("#");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] imageByte;

            for (int i = 0; i <obj.length ; i++) {
                System.out.println("THE NUMBER IS " + i);

                String[] objContent = obj[i].split("&");
                imageByte = decoder.decodeBuffer(objContent[0]);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                BufferedImage image = ImageIO.read(bis);
                bis.close();

                GameObject hf = new GameObject(SwingFXUtils.toFXImage(image, null), Integer.valueOf(objContent[1]), Integer.valueOf(objContent[2]), Integer.valueOf(objContent[3]), Integer.valueOf(objContent[4]));

                System.out.println(map.addGameObject(hf));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
