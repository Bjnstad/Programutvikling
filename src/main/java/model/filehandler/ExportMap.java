package main.java.model.filehandler;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import main.java.model.editor.ImageItem;
import main.java.model.object.MapObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by henrytran1 on 13/05/2018.
 */
public class ExportMap extends FileHandler {
    private ArrayList<String> elements = new ArrayList<>();
    ArrayList<String> filenames = new ArrayList<>();



    public void addElement(MapObject mapObject, ImageItem imageItem){
            StringBuilder sb = new StringBuilder();
        boolean exist = false;
        for(String fileName : filenames){
            if (fileName.equals(imageItem.getFileName())) {
                exist = true;
                break;
            }
        }

        if(!exist) {
            filenames.add(imageItem.getFileName());
            sb.append("§");
            sb.append(imageItem.getFileName());
            sb.append(",");
            sb.append(imageItem.getBits());
            sb.append(",");
            sb.append(imageItem.getFrames());
            sb.append(",");
            sb.append(imageItem.getX());
            sb.append(",");
            sb.append(imageItem.getY());
            sb.append(",");
            sb.append(imageItem.getImage());
            sb.append("§");


        }





/*        String base64String = encodeImageToString(SwingFXUtils.fromFXImage(mapObject.getAsset(), null), "png");
        base64String = base64String.substring(0, base64String.length()-5);

        sb.append(base64String + "&");
        sb.append(mapObject.getSizeX() +"&");
        sb.append(mapObject.getSizeY() +"&");
        sb.append(mapObject.getPosX()+ "&");
        sb.append(mapObject.getPosY()+ "&#");*/
        sb.append("&");
        sb.append(imageItem.getFileName());
        sb.append(",");
        sb.append(imageItem.getX());
        sb.append(",");
        sb.append(imageItem.getY());
        sb.append(",");
        sb.append(mapObject.getPosX());
        sb.append(",");
        sb.append(mapObject.getPosY());
        sb.append(",");
        sb.append(mapObject.getSizeX());
        sb.append(",");
        sb.append(mapObject.getSizeY());
        sb.append("&");

        elements.add(sb.toString());

    }

    public ArrayList<String> getElements() {
        return elements;
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
