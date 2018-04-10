package HAC.editor;

import HAC.world.GameObject;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by henrytran1 on 06/03/2018.
 */
public class HacParser {
    private ArrayList<GameObject> hacContent = new ArrayList<>();



    public void addObject(Image image, int sizeX, int sizeY, int posX, int posY){
        GameObject hf = new GameObject(image, posY, posX, sizeX, sizeY);

        hacContent.add(hf);

    }

    public ArrayList<GameObject> parseFile(File file){



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


                addObject(SwingFXUtils.toFXImage(image, null), Integer.valueOf(objContent[1]), Integer.valueOf(objContent[2]), Integer.valueOf(objContent[3]), Integer.valueOf(objContent[4]));
            }

                                                                                                                                                                                                                                                                                                                                    

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return hacContent;
    }
}
