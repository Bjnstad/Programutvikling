package hac.model.filehandler;

import hac.controller.World;
import hac.model.object.GameObject;
import hac.model.object.sprite.animation.MultiAnimation;
import hac.model.object.sprite.animation.SingleAnimation;
import hac.model.object.sprite.animation.StaticAnimation;
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

import java.io.*;
import java.util.ArrayList;

/**
 * ExportMap is the class for exporting custom made maps.
 *
 * @see FileHandler
 * @author Henry Tran - s315309
 */
public class ExportMap extends FileHandler {
    private World world;
    private ArrayList<String> elements = new ArrayList<>();
    private ArrayList<String> filenames = new ArrayList<>();

    public ExportMap(World world){
        this.world = world;
    }
    public ExportMap(){

    }


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
            sb.append(imageItem.getSpriteHeight());
            sb.append(",");
            sb.append(imageItem.getSpriteWidth());
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
        elements.add(sb.toString());

    }

    public void addMapSize(){
        StringBuilder sb = new StringBuilder();
        sb.append("$");
        sb.append(world.getGameMap().getWidth());
        sb.append(",");
        sb.append(world.getGameMap().getHeight());
        elements.add(sb.toString());

    }

    public void saveGame(){
        StringBuilder saveBuilder = new StringBuilder();

        saveBuilder.append(appendAhac());
        saveBuilder.append(saveGameState());
        saveBuilder.append(addObject());
        elements.add(saveBuilder.toString());
        handleSaveMapName(false);

    }

    public String addObject(){
        StringBuilder sb = new StringBuilder();

        for (GameObject object : world.getGameObjects()) {
            sb.append("§");
            String type = object.getClass().getSimpleName();
            sb.append(type);
            sb.append(",");
            sb.append(object.getPosX());
            sb.append(",");
            sb.append(object.getPosY());
            sb.append(",");
            if(object.getAvatar().getAnimation() instanceof MultiAnimation) {
                MultiAnimation animation = (MultiAnimation)object.getAvatar().getAnimation();
                sb.append("#");
                sb.append(",");
                sb.append(object.getAvatar().getFilename());
                sb.append(",");
                sb.append(animation.getDirection());
                sb.append(",");
                sb.append(animation.getFrames());
                sb.append(",");
                sb.append(animation.getX());
                sb.append(",");
                sb.append(animation.getY());

            }

            if(object.getAvatar().getAnimation() instanceof SingleAnimation) {
                SingleAnimation animation = (SingleAnimation)object.getAvatar().getAnimation();
                sb.append("/");
                sb.append(animation.getFrames());
                sb.append(",");
                sb.append(animation.getY());
                // HENT UT DATA
            }

            if(object.getAvatar().getAnimation() instanceof StaticAnimation) {
                StaticAnimation animation = (StaticAnimation)object.getAvatar().getAnimation();
                sb.append("$");
                sb.append(animation.getX());
                sb.append(",");
                sb.append(animation.getY());

                // HENT UT DATA
            }
        }
        return sb.toString();

    }

    public String appendAhac(){

        try {
            BufferedReader b = new BufferedReader(new FileReader(new File("assets/maps/" + world.getGameMap().getMapFileName())));

            String str = b.readLine().toString();
            System.out.println(str);
            return str;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String saveGameState(){
        StringBuilder sb = new StringBuilder();
        sb.append("@");
        sb.append(world.getGameMap().getWidth());
        sb.append(",");
        sb.append(world.getGameMap().getHeight());
        sb.append(",");
        sb.append(world.getGameMap().getBackgroundFileName());
        sb.append(",");
        sb.append(world.getCurrentLevel());
        sb.append(",");
        sb.append(world.isGodmode());
        sb.append(",");
        sb.append(world.getCamera().getTranslateX());
        sb.append(",");
        sb.append(world.getCamera().getTranslateY());
        sb.append("@");
        return sb.toString();


    }



    /**
     * This methods shows a new scene to user, where the user inputs a filename for the file to be created.
     */
    public void handleSaveMapName(boolean onlyMap){
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
                if(onlyMap == true){
                    createFile(new File("assets/maps/"+inputFileName.getText()+".mhac"), content);
                }else{
                    createFile(new File("assets/savegame/"+inputFileName.getText()+".mhac"), content);

                }

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
