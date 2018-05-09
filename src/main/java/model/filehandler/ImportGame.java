package main.java.model.filehandler;

import main.java.model.character.Enemy;
import main.java.model.character.Player;
import main.java.model.world.GameMap;
import main.java.model.world.GameObject;

import java.io.*;

/**
 * Created by henrytran1 on 02/05/2018.
 */
public class ImportGame {
    private GameMap map;
    private Enemy[] enemies;
    private Player player;
    private double translateX;
    private double translateY;

    public ImportGame(File file) {
        parseFile(file);

    }

    public void parseFile(File file){



        try {

            BufferedReader b = new BufferedReader(new FileReader(file));
            String str = b.readLine().toString();


            String[] obj = str.split("@");
            String[] objArr = obj[1].split("§");
            String[] mapSize = obj[2].split("&");
            String[] camera = mapSize[2].split("#");
            String[] enemiesArr = mapSize[2].split("/");
            String[] playerString = enemiesArr[2].split("!");
            String[] enemyArray = enemiesArr[1].split("§");

            //System.out.println(mapSize[1]);
            String mapValues[] = mapSize[1].split(",");
            String background = mapValues[0];
            int mapHeight = Integer.valueOf(mapValues[1]);
            int mapWidth = Integer.valueOf(mapValues[2]);

            GameMap gameMap = new GameMap(mapWidth, mapHeight, new SpriteSheet(background, 32));
            map = gameMap;



            for (int i = 0; i <objArr.length ; i++) {
                System.out.println(objArr[i]);
                for (int j = 0; j < 6; j++) {
                    //GameObject object = new GameObject()
                }
            }

            System.out.println(camera[1]);
            String[] cameraValues = camera[1].split(",");
            translateX = Double.valueOf(cameraValues[0]);
            translateY = Double.valueOf(cameraValues[1]);


            enemies = new Enemy[enemyArray.length];
            for (int i = 0; i <enemyArray.length ; i++) {
                System.out.println(enemyArray[i]);
                for (int j = 0; j < 5; j++) {
                    String[] enemy = enemyArray[i].split(",");
                    String fileName = enemy[0];
                    int sizeX = Integer.valueOf(enemy[1]);
                    int sizeY = Integer.valueOf(enemy[2]);
                    double posX = Double.valueOf(enemy[3]);
                    double posY = Double.valueOf(enemy[4]);
                    this.enemies[i] = new Enemy(fileName, sizeX,sizeY,posX,posY);
                    //System.out.println("Filename: " + fileName + " SizeX: " + sizeX + " SizeY: " + sizeY + " posX: " + posX + " posY: " + posY);

                }

            }

            String[] playerValues = playerString[1].split(",");
            String playerSprite = playerValues[0];
            double playerPosY = Double.valueOf(playerValues[1]);
            double playerPosX = Double.valueOf(playerValues[2]);
            int sizeX = Integer.valueOf(playerValues[3]);
            int sizeY = Integer.valueOf(playerValues[4]);
            player = new Player(playerSprite, sizeX, sizeY, playerPosX, playerPosY);

            System.out.println(playerString[1]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public GameMap getMap() {
        return map;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public Player getPlayer() {
        return player;
    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }
}
