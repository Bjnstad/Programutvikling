package hac.model.filehandler;

import hac.model.object.GameMap;
import javafx.embed.swing.SwingFXUtils;
import hac.controller.World;
import hac.model.object.MapObject;
import hac.model.object.sprite.Avatar;
import hac.model.object.sprite.animation.StaticAnimation;
import sun.misc.BASE64Decoder;
import javax.imageio.ImageIO;
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
     * @param file
     * @return
     */
    public World parseFile(File file) {
        World world = new World();

        try {

            BufferedReader b = new BufferedReader(new FileReader(file));

            String strSprite = b.readLine().toString();


            String[] spriteSheetString = strSprite.split("§");

            for (int i = 0; i < spriteSheetString.length; i++) {
                System.out.println(spriteSheetString[i]);
                if (spriteSheetString[i].equals("")) continue;
                if(spriteSheetString[i].substring(0, 1).equals("$")){
                    String[] mapSize = spriteSheetString[i].substring(1).split(",");
                    int mapX = Integer.parseInt(mapSize[0]);
                    int mapY = Integer.parseInt(mapSize[1]);
                    System.out.println("MapX:" + mapX + "mapY:" + mapY);
                    GameMap map = new GameMap(mapX, mapY, new SpriteSheet("default_background"));
                    world.setGameMap(map);
                    continue;
                }
                if (!spriteSheetString[i].substring(0, 1).equals("&")) {
                    String[] spriteSheet = spriteSheetString[i].split(",");
                    String spriteFileName = spriteSheet[0];
                    int spriteHeight = Integer.parseInt(spriteSheet[1]);
                    int spriteWidth = Integer.parseInt(spriteSheet[2]);
                    int columns = Integer.parseInt(spriteSheet[3]);
                    int rows = Integer.parseInt(spriteSheet[4]);
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] imageByte = decoder.decodeBuffer(spriteSheet[5]);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                    BufferedImage image = ImageIO.read(bis);
                    bis.close();

                    //Create AHAC file if file is not in directory.
                    File f = new File("assets/spritesheets/" + spriteFileName + ".ahac");
                    if (!f.exists()) {
                        saveSpriteInput(SwingFXUtils.toFXImage(image, null), spriteHeight, spriteWidth, columns, rows, spriteFileName);
                    }

                } else {
                    String[] objects = spriteSheetString[i].split("&");

                    for (int j = 0; j < objects.length; j++) {
                        if (objects[j].equals("")) continue;
                        String[] object = objects[j].split(",");
                        String objectFileName = object[0];
                        int objectX = Integer.parseInt(object[1]);
                        int objectY = Integer.parseInt(object[2]);
                        double posX = Double.parseDouble(object[3]);
                        double posY = Double.parseDouble(object[4]);

                        MapObject mapObject = new MapObject(new Avatar(objectFileName, new StaticAnimation(objectX, objectY)), (int) posY, (int) posX);
                        world.addGameObject(mapObject);
                    }
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return world;
    }
}