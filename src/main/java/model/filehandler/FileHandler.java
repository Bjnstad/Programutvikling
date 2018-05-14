package main.java.model.filehandler;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import main.java.model.object.GameObject;
import main.java.model.object.sprite.animation.MultiAnimation;
import main.java.model.object.sprite.animation.SingleAnimation;
import main.java.model.object.sprite.animation.StaticAnimation;
import main.java.model.world.GameMap;
import main.java.model.world.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Created by henrytran1 on 13/05/2018.
 */
public class FileHandler {


    /**
     *
     * @param image
     * @param type
     * @return string of the image
     */
    public String encodeImageToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }


    public void createFile(File file, String content){
        //StringBuilder sb = new StringBuilder();
        //for(String content: elements) sb.append(elements);

        //String content = sb.toString();

        //File file = new File("assets/maps/newMap.mhac");

        try(FileOutputStream outputStream = new FileOutputStream(file)){
            if(!file.exists()){
                file.createNewFile();
            }

            byte[] contentInBytes = content.getBytes();

            outputStream.write(contentInBytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to save imported spritesheets.
     * @param image
     * @param bits
     * @param cols
     * @param rows
     * @param fileName
     */
    public void saveSpriteInput(Image image, int bits, int cols, int rows, String fileName){
        String base64String = encodeImageToString(SwingFXUtils.fromFXImage(image, null), "png");
        base64String = base64String.substring(0, base64String.length()-5);

        StringBuilder sb = new StringBuilder();
        sb.append(fileName);
        sb.append("#");
        sb.append(bits);
        sb.append("#");
        sb.append(cols);
        sb.append("#");
        sb.append(rows);
        sb.append("#");
        sb.append(base64String);

        String content = sb.toString();
        File file = new File("assets/editorassets/"+fileName+".ahac");
        createFile(file, content);
        System.out.println("CREATED FILE");

    }










}
