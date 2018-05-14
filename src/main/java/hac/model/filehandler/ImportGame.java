package main.java.hac.model.filehandler;

import main.java.hac.model.object.character.Enemy;
import main.java.hac.model.object.character.MainPlayer;
import main.java.hac.model.object.character.Player;
import main.java.hac.model.object.GameMap;
import main.java.hac.controller.World;

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



            /*
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

            //GameMap gameMap = new GameMap(mapWidth, mapHeight, new SpriteSheet(background, 32));
            //map = gameMap;



            for (int i = 0; i <objArr.length ; i++) {
                String[] objectValues = objArr[i].split(",");
                for (int j = 0; j < objectValues.length; j++) {
                    int sizeY = Integer.valueOf(objectValues[0]);
                    int sizeX = Integer.valueOf(objectValues[1]);
                    int posX = Integer.valueOf(objectValues[2]);
                    int posY = Integer.valueOf(objectValues[3]);

                    System.out.println("Size Y: " + objectValues[0]);
                    System.out.println("Size X: " + objectValues[1]);
                    System.out.println("Pos X: " +objectValues[2]);
                    System.out.println("Pos Y: " +objectValues[3]);
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] imageByte = decoder.decodeBuffer(objectValues[4]);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                    BufferedImage image = ImageIO.read(bis);
                    bis.close();

                    System.out.println(objectValues[4]);

                    //map.addGameObject(new MapObject(SwingFXUtils.toFXImage(image, null), posY, posX, sizeX, sizeY));
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
            */

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

    public World getWorld(){
        return world;
    }
}
