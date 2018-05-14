package main.java.model.filehandler;

import main.java.model.Camera;
import main.java.model.object.GameObject;
import main.java.model.object.character.Enemy;
import main.java.model.object.character.Player;
import main.java.model.object.sprite.animation.MultiAnimation;
import main.java.model.object.sprite.animation.SingleAnimation;
import main.java.model.object.sprite.animation.StaticAnimation;
import main.java.model.world.GameMap;
import main.java.model.world.World;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by henrytran1 on 17/04/2018.
 * Game extends Hac
 */
public class ExportGame extends FileHandler {
    private ArrayList<String> mapList = new ArrayList<>();
    //private ArrayList<String> gameObject = new ArrayList<>();

    private World world;
    private StringBuilder sb = new StringBuilder();


    /**
     * Exports the game.
     * Contains gameMap, camera, enemies and player.
     * @param world
     */
    public ExportGame(World world) {
        this.world = world;

        saveGame(world);

        createFile(new File("assets/maps/newMap.txt"), sb.toString());

    }
    public void saveGame(World world) {
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


        for (GameObject object : world.getGameObjects()) {
            sb.append("§");
            String type = object.getClass().getSimpleName();
            sb.append(type);
            sb.append(",");
            sb.append(object.getPosX());
            sb.append(",");
            sb.append(object.getPosY());
            sb.append(",");
            sb.append(object.getSizeX());
            sb.append(",");
            sb.append(object.getSizeY());
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

                // HENT UT DATA
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
    }
/*
    /**
     * Saves the map.
     */
    public void saveMap() {

        /*
        MapObject[] object = ;
        if(object == null){
            sb.append("@!");
            sb.append('@');
            sb.append('&');
            sb.append(gameMap.getBackgroundFileName());
            sb.append(',');
            sb.append(gameMap.getHeight());
            sb.append(',');
            sb.append(gameMap.getWidth());
            sb.append('&');
            return;
        }

        sb.append("@");
        if(object != null) {
            for (int i = 0; i < object.length; i++) {
                if (object[i] == null) continue;
                sb.append(object[i].getSizeY());
                sb.append(',');
                sb.append(object[i].getSizeX());
                sb.append(',');
                sb.append(object[i].getPosX());
                sb.append(',');
                sb.append(object[i].getPosY());
                sb.append(',');
                String base64String = encodeImageToString(SwingFXUtils.fromFXImage(object[i].getAsset(), null), "png");
                sb.append(base64String);
                sb.append("BILDESTRENG");
                sb.append("§");
            }
        }
        sb.append('@');
        sb.append('&');
        sb.append(gameMap.getBackgroundFileName());
        sb.append(',');
        sb.append(gameMap.getHeight());
        sb.append(',');
        sb.append(gameMap.getWidth());
        sb.append('&');
        */
    }

/*

    public void saveCamera(){
        sb.append("#");
        sb.append(camera.getTranslateX());
        sb.append(",");
        sb.append(camera.getTranslateY());
        sb.append("#");
    }


    public void saveEnemies(){
        sb.append("/");
        for (int i = 0; i < enemies.length; i++) {
            //sb.append(enemies[i].getSpriteFileName());
            sb.append(',');
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


    public void savePlayer(){
        sb.append("!");
        //sb.append(player.getSpriteFileName());
        sb.append(',');
        sb.append(player.getPosY());
        sb.append(',');
        sb.append(player.getPosX());
        sb.append(',');
        sb.append(player.getSizeX());
        sb.append(',');
        sb.append(player.getSizeY());
        sb.append("!");

    }
    */



    /**
     * Adds element.
     * @param gameMap
     */
    public void addElement(GameMap gameMap) {
        gameMap.getBackground();
        mapList.add(String.valueOf(gameMap.getWidth()));
        mapList.add(String.valueOf(gameMap.getHeight()));
    }


}
