package hac.model.filehandler;

import javafx.embed.swing.SwingFXUtils;
import application.model.editor.ImageItem;
import hac.model.object.MapObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
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



    public void handleSaveMapName(){
        final Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane root = new BorderPane();

        primaryStage.setTitle("Export map");
        primaryStage.setScene(new Scene(root));

        Button submit = new Button("Submit");

        Label fileName = new Label("Filename (Do not include extension):");
        TextField inputFileName = new TextField ();

        VBox Vertikalboks = new VBox(fileName, inputFileName);

        root.setLeft(Vertikalboks);
        root.setBottom(submit);
        primaryStage.show();

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                StringBuilder sb = new StringBuilder();

                for(String content : getElements()){
                    sb.append(content);
                }
                String content = sb.toString();
                createFile(new File("assets/maps/"+inputFileName.getText()+".mhac"), content);

                primaryStage.close();

            }
        });
    }
    /**
     * Gets the string elements of the constructed maps.
     * @return elements of strings representing the structure of the map.
     */
    public ArrayList<String> getElements() {
        return elements;
    }



}
