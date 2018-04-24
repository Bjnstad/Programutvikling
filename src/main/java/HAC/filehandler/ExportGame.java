package main.java.HAC.filehandler;

import javafx.embed.swing.SwingFXUtils;
import main.java.HAC.Camera;
import main.java.HAC.character.Enemy;
import main.java.HAC.character.Player;
import main.java.HAC.editor.ExportHac;
import main.java.HAC.world.GameMap;
import main.java.HAC.world.GameObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by henrytran1 on 17/04/2018.
 */
public class ExportGame extends ExportHac {
    private ArrayList<String> mapList = new ArrayList<>();
    //private ArrayList<String> gameObject = new ArrayList<>();


    private GameMap gameMap;
    private Camera camera;
    private Enemy[] enemies = new Enemy[1];
    private Player player;
    private StringBuilder sb = new StringBuilder();


    public ExportGame(GameMap gameMap, Camera camera, Enemy[] enemies, Player player) {
        this.gameMap = gameMap;
        this.camera = camera;
        this.enemies = enemies;
        this.player = player;



        saveMap();
        saveCamera();
        saveEnemies();
        savePlayer();
        exportFile();

    }

    public void saveMap() {
        GameObject[] object = gameMap.getGameObjects();
        for (int i = 0; i <object.length; i++) {
            if(object[i] == null) continue;
            sb.append(object[i].getSizeY());
            sb.append(object[i].getSizeX());
            sb.append(object[i].getPosX());
            sb.append(object[i].getPosY());
            sb.append(object[i].getSizeY());
            sb.append('$');
            String base64String = encodeImageToString(SwingFXUtils.fromFXImage(object[i].getAsset(), null), "png");
            sb.append(base64String);
        }
        sb.append(gameMap.getHeight());
        sb.append(gameMap.getWidth());

    }


    public void saveCamera(){
        sb.append(camera.getWidth());
        sb.append(camera.getHeight());
        sb.append(camera.getTranslateX());
        sb.append(camera.getTranslateY());

    }

    public void saveEnemies(){
        for (int i = 0; i < enemies.length; i++) {
            sb.append(enemies[i].getSpriteFileName());
            sb.append(enemies[i].getSizeX());
            sb.append(enemies[i].getSizeY());
            sb.append(enemies[i].getPosX());
            sb.append(enemies[i].getPosY());

        }

    }

    public void savePlayer(){
        sb.append(player.getSpriteFileName());
        sb.append(player.getSizeX());
        sb.append(player.getSizeY());

    }




    public void addElement(GameMap gameMap){
        System.out.println(gameMap.getGameObjects());


        gameMap.getBackground();

        mapList.add(String.valueOf(gameMap.getWidth()));
        mapList.add(String.valueOf(gameMap.getHeight()));


    }

    public void exportFile(){
        String content = sb.toString();
        //File file = new File("/Users/henrytran1/Documents/Github/Programutvikling/newFile.txt");
        File file = new File("assets/maps/newMap.txt");

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