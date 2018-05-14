package main.java.hac.model.filehandler;


import javafx.embed.swing.SwingFXUtils;
import main.java.application.model.editor.ImageItem;
import main.java.hac.model.object.MapObject;

import java.util.ArrayList;

/**
 * ExportMap is the class for exporting custom made maps.
 *
 * @see FileHandler
 * @author Henry Tran - s315309
 */
public class ExportMap extends FileHandler {
    private ArrayList<String> elements = new ArrayList<>();
    ArrayList<String> filenames = new ArrayList<>();


    /**
     * Adds element to list, used to structure the map with objects and spritesheets used.
     *
     * @param mapObject Objects on the map.
     * @param imageItem the item being used.
     */
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
            String base64String = encodeImageToString(SwingFXUtils.fromFXImage(imageItem.getImage(), null), "png");
            base64String = base64String.substring(0, base64String.length()-5);
            filenames.add(imageItem.getFileName());
            sb.append("§");
            sb.append(imageItem.getFileName());
            sb.append(",");
            sb.append(imageItem.getBits());
            sb.append(",");
            sb.append(imageItem.getFrames());
            sb.append(",");
            sb.append(imageItem.getColumns());
            sb.append(",");
            sb.append(base64String);
            sb.append("§");
        }
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

        elements.add(sb.toString());

    }

    /**
     * Gets the string elements of the constructed maps.
     * @return elements of strings representing the structure of the map.
     */
    public ArrayList<String> getElements() {
        return elements;
    }


}
