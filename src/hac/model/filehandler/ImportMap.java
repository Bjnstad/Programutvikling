package hac.model.filehandler;

import hac.model.Camera;
import hac.model.GameMap;
import hac.model.object.predefined.character.Enemy;
import hac.model.object.defaults.MainPlayer;
import hac.model.object.defaults.Skeleton;
import javafx.embed.swing.SwingFXUtils;
import hac.controller.World;
import hac.model.object.predefined.MapObject;
import hac.model.object.sprite.Avatar;
import hac.model.object.sprite.animation.StaticAnimation;

import java.awt.image.BufferedImage;
import java.io.*;


/**
 * This class content
 *
 * @author
 */
public class ImportMap extends FileHandler {

    /**
     *
     * @param
     * @return
     */

    public String[] splitString(String str){
        String[] res = str.substring(1).split(INLINE_CONTENT);
        return res;
    }

    public World parseFile(File file, Camera camera){
        try {
            World world = new World();

            BufferedReader b = new BufferedReader(new FileReader(file));

            String strSprite = b.readLine().toString();

            String[] spriteSheetString = strSprite.split(NEW_LINE);

            for (int i = 0; i < spriteSheetString.length; i++) {
                if(spriteSheetString[i].substring(0, 1).equals(MAP_SIZE)){

                    String[] mapSize = splitString(spriteSheetString[i]);
                    String mapFileName = mapSize[0] +".mhac";
                    int mapWidth = Integer.parseInt(mapSize[1]);
                    int mapHeight = Integer.parseInt(mapSize[2]);
                    world.setGameMap(new GameMap(mapWidth, mapHeight, new SpriteSheet("default_background")));

                    world.getGameMap().setMapFileName(mapFileName);


                    System.out.println("MAPSIZE: " + mapWidth + " height: " + mapHeight );
                }

                if(spriteSheetString[i].substring(0, 1).equals(SPRITE_CONTENT) || spriteSheetString[i].equals("")){
                    String[] spriteSheet = splitString(spriteSheetString[i]);
                    String spriteFileName = spriteSheet[0];
                    int spriteBits = Integer.parseInt(spriteSheet[1]);
                    int cols = Integer.parseInt(spriteSheet[2]);
                    int rows = Integer.parseInt(spriteSheet[3]);
                    BufferedImage image = encodeStringToImage(spriteSheet[4]);
                    //Create AHAC file if file is not in directory.
                    File f = new File("assets/spritesheets/" + spriteFileName + ".ahac");
                    if (!f.exists()) {
                        saveSpriteInput(SwingFXUtils.toFXImage(image, null), spriteBits, cols, rows, spriteFileName);
                    }

                    System.out.println("SPRITE POSITION " + spriteSheetString[i]);
                }

                if(spriteSheetString[i].substring(0, 1).equals(SPRITE_POSITION)){
                    String[] spritePosition = splitString(spriteSheetString[i]);
                    String objectFileName = spritePosition[0];
                    int objectX = Integer.parseInt(spritePosition[1]);
                    int objectY = Integer.parseInt(spritePosition[2]);
                    double posX = Double.parseDouble(spritePosition[3]);
                    double posY = Double.parseDouble(spritePosition[4]);
                    MapObject mapObject = new MapObject(new Avatar(objectFileName, new StaticAnimation(objectX, objectY)), (int) posY, (int) posX);
                    world.addGameObject(mapObject);

                    System.out.println("Sprite position used: " + spriteSheetString[i]);
                }

                if(spriteSheetString[i].substring(0, 1).equals(GAME_SAVE_STATE)){
                    String[] gameState = splitString(spriteSheetString[i]);
                    String backgroundFileName = gameState[2];
                    int currentLevel = Integer.parseInt(gameState[3]);
                    boolean isGodMode = Boolean.parseBoolean(gameState[4]);
                    double translateX = Double.parseDouble(gameState[5]);
                    double translateY = Double.parseDouble(gameState[6]);

                    camera.translate(translateX, translateY);

                   // world.getGameMap().setMapFileName(backgroundFileName);
                    world.setCurrentLevel(currentLevel);
                    world.setGodmode(isGodMode);

                    System.out.println("GAME SAVE STATE: " + spriteSheetString[i]);
                }

                if(spriteSheetString[i].substring(0, 1).equals(OBJECT)){
                    String[] object = splitString(spriteSheetString[i]);
                    String type = object[0];
                    double posX = Double.parseDouble(object[1]);
                    double posY = Double.parseDouble(object[2]);
                    String animationType = object[3];

                    if(animationType.equals(MULTI_ANIMATION)){
                        String fileName = object[4];
                        String direction  = object[5];
                        int frames = Integer.parseInt(object[6]);
                        int x = Integer.parseInt(object[7]);
                        int y = Integer.parseInt(object[8]);

                        if(type.equals("Skeleton")){
                            Enemy enemy = new Skeleton(posX, posY);

                            if(object.length == 10){
                                double health = Double.parseDouble(object[9]);
                                enemy.setHealth(health);
                            }

                            world.addGameObject(enemy);
                        }
                        if(type.equals("MainPlayer")){
                            MainPlayer mainPlayer = new MainPlayer(fileName, posX, posY);
                            world.addGameObject(mainPlayer);
                        }

                    }

                    System.out.println("OJECT: " +spriteSheetString[i]);
                }

                System.out.println(spriteSheetString[i]);

            }return world;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            showAlert("Corrupt File!", "File is corrupt.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Corrupt File!", "File is corrupt.");
        } catch (ArrayIndexOutOfBoundsException e){
            showAlert("Corrupt File!", "File is corrupt.");
        }

        return null;
    }


}