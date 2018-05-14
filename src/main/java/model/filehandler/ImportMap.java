package main.java.model.filehandler;

import javafx.embed.swing.SwingFXUtils;
import main.java.model.object.MapObject;
import main.java.model.object.sprite.Avatar;
import main.java.model.object.sprite.animation.StaticAnimation;
import main.java.model.world.World;
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
     * This method helps us to parse a file.
     * ... kommer mer
     * ...
     *
     * @param file ......
     */
    public World parseFile(File file) {
        World world = new World();

        //GameMap map = new GameMap(20, 20, new SpriteSheet("background", 32));
        //world.setGameMap(map);
        try {

            BufferedReader b = new BufferedReader(new FileReader(file));

            String strSprite = b.readLine().toString();

            String[] spriteSheetString = strSprite.split("§");

            for (int i = 0; i < spriteSheetString.length; i++) {

                if (spriteSheetString[i].equals("")) continue;
                if (!spriteSheetString[i].substring(0, 1).equals("&")) {
                    String[] spriteSheet = spriteSheetString[i].split(",");
                    String spriteFileName = spriteSheet[0];
                    int bits = Integer.parseInt(spriteSheet[1]);
                    int columns = Integer.parseInt(spriteSheet[2]);
                    int rows = Integer.parseInt(spriteSheet[3]);
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] imageByte = decoder.decodeBuffer(spriteSheet[4]);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                    BufferedImage image = ImageIO.read(bis);
                    bis.close();

                    //Create AHAC file if file is not in directory.
                    File f = new File("assets/spritesheets/" + spriteFileName + ".ahac");
                    if (!f.exists()) {
                        saveSpriteInput(SwingFXUtils.toFXImage(image, null), bits, columns, rows, spriteFileName);
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
                        int sizeX = Integer.parseInt(object[5]);
                        int sizeY = Integer.parseInt(object[6]);

                        MapObject mapObject = new MapObject(new Avatar(objectFileName, new StaticAnimation(objectX, objectY)), (int) posY, (int) posX, sizeX, sizeY);
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