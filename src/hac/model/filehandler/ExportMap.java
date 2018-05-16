package hac.model.filehandler;


import application.model.editor.ImageItem;
import hac.controller.World;
import hac.model.object.GameObject;
import hac.model.object.predefined.MapObject;
import hac.model.object.predefined.character.Enemy;
import hac.model.object.sprite.animation.MultiAnimation;
import hac.model.object.sprite.animation.SingleAnimation;
import hac.model.object.sprite.animation.StaticAnimation;
import javafx.embed.swing.SwingFXUtils;
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
            sb.append(NEW_LINE);
            sb.append(SPRITE_CONTENT);
            sb.append(imageItem.getFileName());
            sb.append(INLINE_CONTENT);
            sb.append(imageItem.getBits());
            sb.append(INLINE_CONTENT);
            sb.append(imageItem.getFrames());
            sb.append(INLINE_CONTENT);
            sb.append(imageItem.getColumns());
            sb.append(INLINE_CONTENT);
            sb.append(base64String);
        }
        sb.append(NEW_LINE);
        sb.append(SPRITE_POSITION);
        sb.append(imageItem.getFileName());
        sb.append(INLINE_CONTENT);
        sb.append(imageItem.getX());
        sb.append(INLINE_CONTENT);
        sb.append(imageItem.getY());
        sb.append(INLINE_CONTENT);
        sb.append(mapObject.getPosX());
        sb.append(INLINE_CONTENT);
        sb.append(mapObject.getPosY());

        elements.add(sb.toString());

    }


    public void addMapSize(){
        StringBuilder sb = new StringBuilder();
        sb.append(INLINE_CONTENT);
        sb.append(world.getGameMap().getWidth());
        sb.append(INLINE_CONTENT);
        sb.append(world.getGameMap().getHeight());

        elements.add(sb.toString());

    }

    public void saveGame(){
        StringBuilder saveBuilder = new StringBuilder();

        saveBuilder.append(appendAhac());
        saveBuilder.append(saveGameState());
        saveBuilder.append(saveObjects());
        elements.add(saveBuilder.toString());
        handleSaveMapName(false);

    }

    public String saveObjects(){
        StringBuilder sb = new StringBuilder();

        for (GameObject object : world.getGameObjects()) {
            sb.append(NEW_LINE);
            sb.append(OBJECT);
            String type = object.getClass().getSimpleName();
            sb.append(type);
            sb.append(INLINE_CONTENT);
            sb.append(object.getPosX());
            sb.append(INLINE_CONTENT);
            sb.append(object.getPosY());
            sb.append(INLINE_CONTENT);
            if(object.getAvatar().getAnimation() instanceof MultiAnimation) {
                MultiAnimation animation = (MultiAnimation)object.getAvatar().getAnimation();
                sb.append(MULTI_ANIMATION);
                sb.append(INLINE_CONTENT);
                sb.append(object.getAvatar().getFilename());
                sb.append(INLINE_CONTENT);
                sb.append(animation.getDirection());
                sb.append(INLINE_CONTENT);
                sb.append(animation.getFrames());
                sb.append(INLINE_CONTENT);
                sb.append(animation.getX());
                sb.append(INLINE_CONTENT);
                sb.append(animation.getY());
                if(object.getClass().getSimpleName().equals("Skeleton")){
                    sb.append(INLINE_CONTENT);
                    String health = String.valueOf(((Enemy) object).getHealth());
                    sb.append(health);
                }
            }

            if(object.getAvatar().getAnimation() instanceof SingleAnimation) {
                SingleAnimation animation = (SingleAnimation)object.getAvatar().getAnimation();
                sb.append(SINGLE_ANIMATION);
                sb.append(animation.getFrames());
                sb.append(INLINE_CONTENT);
                sb.append(animation.getY());
            }

            if(object.getAvatar().getAnimation() instanceof StaticAnimation) {
                StaticAnimation animation = (StaticAnimation)object.getAvatar().getAnimation();
                sb.append(STATIC_ANIMATION);
                sb.append(animation.getX());
                sb.append(INLINE_CONTENT);
                sb.append(animation.getY());

            }
        }
        return sb.toString();

    }

    public String appendAhac(){

        try {
            System.out.println("assets/maps/" + world.getGameMap().getMapFileName());
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
        sb.append(NEW_LINE);
        sb.append(GAME_SAVE_STATE);
        sb.append(world.getGameMap().getWidth());
        sb.append(INLINE_CONTENT);
        sb.append(world.getGameMap().getHeight());
        sb.append(INLINE_CONTENT);
        sb.append(world.getGameMap().getBackgroundFileName());
        sb.append(INLINE_CONTENT);
        sb.append(world.getCurrentLevel());
        sb.append(INLINE_CONTENT);
        sb.append(world.isGodmode());
        sb.append(INLINE_CONTENT);
        sb.append(world.getCamera().getTranslateX());
        sb.append(INLINE_CONTENT);
        sb.append(world.getCamera().getTranslateY());
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

                if(onlyMap == true) {
                    sb.append(MAP_SIZE);
                 sb.append(inputFileName.getText());

                }
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
