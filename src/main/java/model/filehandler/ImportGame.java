package main.java.model.filehandler;

import main.java.model.world.GameMap;

import java.io.*;

/**
 * Created by henrytran1 on 02/05/2018.
 */
public class ImportGame {

    public void parseFile(File file){
        GameMap map = new GameMap(20, 20, new SpriteSheet("background", 32));;

        try {

            BufferedReader b = new BufferedReader(new FileReader(file));
            String str = b.readLine().toString();

            String[] obj = str.split("@");
            String[] objArr = obj[1].split("§");
            String[] mapSize = obj[2].split("&");
            String[] camera = mapSize[2].split("#");
            String[] enemies = mapSize[2].split("/");
            String[] player = enemies[2].split("!");

            String[] enemyArray = enemies[1].split("§");

            for (int i = 0; i <objArr.length ; i++) {
                System.out.println(objArr[i]);
            }
            System.out.println(camera[1]);
            System.out.println(mapSize[1]);

            for (int i = 0; i <enemyArray.length ; i++) {
                System.out.println(enemyArray[i]);
            }
            System.out.println(player[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // return map;
    }
}
