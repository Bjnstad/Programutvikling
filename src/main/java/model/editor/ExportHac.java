package main.java.model.editor;

import javafx.scene.image.Image;
import main.java.model.world.MapObject;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

/**
 * This class makes it possible to export a file.
 * @author henrytran
 */

//@TODO: RESTRUCTURE METHODS!!
public class ExportHac {
    private ArrayList<String> elements = new ArrayList<>();

    /**
     * This method adds a element to the game.
     * @param mapObject is the element that is being added to the game.
     */
    public void addElement(MapObject mapObject){
        String base64String = encodeImageToString(SwingFXUtils.fromFXImage(mapObject.getAsset(), null), "png");
        base64String = base64String.substring(0, base64String.length()-5);

        StringBuilder sb = new StringBuilder();
        sb.append(base64String + "&");
        sb.append(mapObject.getSizeX() +"&");
        sb.append(mapObject.getSizeY() +"&");
        sb.append(mapObject.getPosX()+ "&");
        sb.append(mapObject.getPosY()+ "&#");


        elements.add(sb.toString());
    }

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
        System.out.println("CREATED FILE");

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
     * This method encodes a image to a string.
     * @param image from bufferedImage
     * @param type is a string.
     * @return a string of image.
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

    /**
     * Creates a file from stringBuilder.
     */
    public void createFile(){
        StringBuilder sb = new StringBuilder(); // creates empty builder.
        for(String content : elements){
            sb.append(content); // adds elements to the builder.
        }
        String content = sb.toString();
        File file = new File("assets/maps/newMap.mhac");
        System.out.println("CREATED FILE");

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
}
