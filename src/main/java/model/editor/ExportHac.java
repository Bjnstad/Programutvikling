package main.java.model.editor;

import main.java.model.world.GameObject;
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
public class ExportHac {
    private HacFile hacFile;
    private ArrayList<String> elements = new ArrayList<>();

    /**
     * This method adds a element to the game.
     * @param gameObject is the element that is being added to the game.
     * @author ceciliethoresen
     */
    public void addElement(GameObject gameObject){
        String base64String = encodeImageToString(SwingFXUtils.fromFXImage(gameObject.getAsset(), null), "png");
        base64String = base64String.substring(0, base64String.length()-5);

        StringBuilder sb = new StringBuilder();
        sb.append(base64String + "&");
        sb.append(gameObject.getSizeX() +"&");
        sb.append(gameObject.getSizeY() +"&");
        sb.append(gameObject.getPosX()+ "&");
        sb.append(gameObject.getPosY()+ "&#");


        elements.add(sb.toString());
    }

    private String encodeImageToString(BufferedImage image, String type) {
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

    public void createFile(){
        StringBuilder sb = new StringBuilder(); // creates empty builder.
        for(String content : elements){
            sb.append(content); // adds elements to the builder.
        }
        String content = sb.toString();
        //File file = new File("/Users/henrytran1/Documents/Github/Programutvikling/newFile.txt");
        File file = new File("assets/maps/newMap.mhac");

        try(FileOutputStream outputStream = new FileOutputStream(file)){
            if(!file.exists()){
                file.createNewFile();
            }

            byte[] contentInBytes = content.getBytes();

            outputStream.write(contentInBytes);
            outputStream.flush();
            outputStream.close();
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
