package main.java.model.filehandler;

import main.java.model.Camera;
import main.java.model.character.Enemy;
import main.java.model.character.Player;
import main.java.model.world.GameMap;
import main.java.model.world.GameObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by henrytran1 on 17/04/2018.
 * Game extends Hac
 */
public class ExportGame extends ExportHac {
    private ArrayList<String> mapList = new ArrayList<>();
    //private ArrayList<String> gameObject = new ArrayList<>();


    private GameMap gameMap;
    private Camera camera;
    private Enemy[] enemies = new Enemy[1];
    private Player player;
    private StringBuilder sb = new StringBuilder();


    /**
     * Exports the game.
     * Contains gameMap, camera, enemies and player.
     * @param gameMap
     * @param camera
     * @param enemies
     * @param player
     */
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

    /**
     * Saves the map.
     */
    public void saveMap() {
        GameObject[] object = gameMap.getGameObjects();
        int count = 0;
        sb.append("@");
        for (int i = 0; i <object.length; i++) {
            if(object[i] == null) continue;
            count++;
            sb.append(object[i].getSizeY());
            sb.append(',');
            sb.append(object[i].getSizeX());
            sb.append(',');
            sb.append(object[i].getPosX());
            sb.append(',');
            sb.append(object[i].getPosY());
            sb.append(',');
            sb.append(object[i].getSizeY());
            sb.append(',');
            //String base64String = encodeImageToString(SwingFXUtils.fromFXImage(object[i].getAsset(), null), "png");
            //sb.append(base64String);
            sb.append("BILDESTRENG");
            sb.append("§");
        }
        sb.append("@");
        sb.append('&');
        sb.append(gameMap.getHeight());
        sb.append(',');
        sb.append(gameMap.getWidth());
        sb.append('&');
    }


    /**
     * Saves the camera.
     */
    public void saveCamera(){
        sb.append("#");
        sb.append(camera.getTranslateX());
        sb.append(",");
        sb.append(camera.getTranslateY());
        sb.append("#");
    }

    /**
     * Saves enemies.
     */
    public void saveEnemies(){
        sb.append("/");
        for (int i = 0; i < enemies.length; i++) {
            //sb.append(enemies[i].getSpriteFileName());
            //sb.append(',');
            sb.append(enemies[i].getSizeX());
            sb.append(',');
            sb.append(enemies[i].getSizeY());
            sb.append(',');
            sb.append(enemies[i].getPosX());
            sb.append(',');
            sb.append(enemies[i].getPosY());
            sb.append("§");
        }

        sb.append("/");
    }

    /**
     * Saves player.
     */
    public void savePlayer(){
        //sb.append(player.getSpriteFileName());
        sb.append("!");
        sb.append(player.getPosY());
        sb.append(',');
        sb.append(player.getPosX());
        sb.append(',');
        sb.append(player.getSizeX());
        sb.append(',');
        sb.append(player.getSizeY());
        sb.append("!");

    }



    /**
     * Adds element.
     * @param gameMap
     */
    public void addElement(GameMap gameMap) {
        gameMap.getBackground();
        mapList.add(String.valueOf(gameMap.getWidth()));
        mapList.add(String.valueOf(gameMap.getHeight()));
    }

    /**
     * Exports a file.
     */
    public void exportFile(){
        String content = sb.toString();
        File file = new File("assets/maps/newMap.txt");

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