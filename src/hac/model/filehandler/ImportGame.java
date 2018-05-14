package hac.model.filehandler;

import hac.model.object.character.Enemy;
import hac.model.object.character.MainPlayer;
import hac.model.object.character.Player;
import hac.model.object.GameMap;
import hac.controller.World;

import java.io.*;

/**
 * Imports,
 */
public class ImportGame {

    private double translateX;
    private double translateY;
    private World world;


    public ImportGame(File file) {
        parseFile(file);

    }

    public void parseFile(File file){
        this.world = new World();


        try {

            BufferedReader b = new BufferedReader(new FileReader(file));
            String str = b.readLine().toString();

            String[] mapArr = str.split("@");
            String[] map = mapArr[1].split(",");
            String[] objArr = mapArr[2].split("§");

            int mapWidth = Integer.parseInt(map[0]);
            int mapHeight = Integer.parseInt(map[1]);
            String mapFileName = map[2];
            int currentLevel = Integer.parseInt(map[3]);
            boolean isGodMode = Boolean.parseBoolean(map[4]);
            double translateX = Double.parseDouble(map[5]);
            double translateY = Double.parseDouble(map[6]);

            world.setGameMap(new GameMap(mapWidth, mapHeight, new SpriteSheet(mapFileName)));

            this.translateX = translateX;
            this.translateY = translateY;
               for (int i = 0; i <objArr.length ; i++) {
                String[] object = objArr[i].split(",");
                if(object[0].equals(""))continue;
                String type = object[0];
                double posX = Double.parseDouble(object[1]);
                double posY = Double.parseDouble(object[2]);
                int sizeX = Integer.parseInt(object[3]);
                int sizeY = Integer.parseInt(object[4]);
                String animationType = object[5];

                if(animationType.equals("#")){  //Multi animation
                    String fileName = object[6];
                    String direction  = object[7];
                    int frames = Integer.parseInt(object[8]);
                    int x = Integer.parseInt(object[9]);
                    int y = Integer.parseInt(object[10]);

                    if(type.equals("Enemy")){
                        Enemy enemy = new Enemy(fileName, sizeX, sizeY, posX, posY);
                        world.addGameObject(enemy);
                    }
                    if(type.equals("MainPlayer")){
                        MainPlayer mainPlayer = new MainPlayer(fileName, sizeX, sizeY, posX, posY);
                        world.addGameObject(mainPlayer);
                    }
                    //System.out.println("Type: " + type + " posX: " + posX + " posY: " + posY + " sizeX: " + sizeX + " sizeY: " + sizeY + " FileName: " + fileName + " direction: " + direction + " frames: " + frames + " x: " + x + " y: " + y);
                }

                if(animationType.equals("/")){  //Single Animation

                }
                if(animationType.equals("$")){  //staticAnimation

                }

                //System.out.println(objArr[i]);*/
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public World getWorld(){
        return world;
    }
}
