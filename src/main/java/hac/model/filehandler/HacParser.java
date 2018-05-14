package main.java.hac.model.filehandler;

import javafx.embed.swing.SwingFXUtils;
import main.java.hac.model.object.MapObject;
import main.java.hac.model.object.sprite.Avatar;
import main.java.hac.model.object.sprite.animation.StaticAnimation;
import main.java.hac.controller.World;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * This class content
 *
 * @author
 */
public class HacParser extends FileHandler {

    /**
     * This method helps us to parse a file.
     * ... kommer mer
     * ...
     * @param file ......
     */
    public World parseFile(File file){
        World world = new World();

        //GameMap map = new GameMap(20, 20, new SpriteSheet("background", 32));
        //world.setGameMap(map);
        try {

            BufferedReader b = new BufferedReader(new FileReader(file));

            String strSprite = b.readLine().toString();

            String[] spriteSheetString =strSprite.split("§");

            for (int i = 0; i < spriteSheetString.length ; i++) {

                if(spriteSheetString[i].equals(""))continue;
                if(!spriteSheetString[i].substring(0, 1).equals("&")){
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
                    File f = new File("assets/spritesheets/"+spriteFileName+".ahac");
                    if(!f.exists()){
                        saveSpriteInput(SwingFXUtils.toFXImage(image, null), bits, columns, rows, spriteFileName);
                    }

                    //System.out.println(spriteSheetString[i]);
                }else{

                    String[] objects = spriteSheetString[i].split("&");

                    for (int j = 0; j < objects.length; j++) {
                        if(objects[j].equals(""))continue;

                        String[] object = objects[j].split(",");
                        String objectFileName = object[0];
                        int objectX = Integer.parseInt(object[1]);
                        int objectY = Integer.parseInt(object[2]);
                        double posX = Double.parseDouble(object[3]);
                        double posY = Double.parseDouble(object[4]);
                        int sizeX = Integer.parseInt(object[5]);
                        int sizeY = Integer.parseInt(object[6]);

                        MapObject mapObject = new MapObject(new Avatar(objectFileName, new StaticAnimation(objectX, objectY)),(int)posY, (int)posX, sizeX, sizeY);
                        world.addGameObject(mapObject);




                    }


                }



            }






          //  String[] objects = b.readLine().split("&");
/*
            for (int i = 0; i < spriteSheetString.length; i++) {
                if(spriteSheetString[i].equals(""))continue;
                String object = spriteSheetString[i].substring(0,1);
                if(object.equals("&")){
                    String[] obj = spriteSheetString[i].split("&");
                    for (int j = 0; j <obj.length ; j++) {
                        if(obj[j].equals(""))continue;
                        System.out.println(obj[j]);
                        String[] objValues = obj[j].split(",");
                        System.out.println("Filnavn: " + objValues[0] + " x sprite: " + objValues[1] + " y Sprite: " + objValues[2] + " posX: " + objValues[3] + " posY: " + objValues[4] + " size x " + objValues[5] + " size y " + objValues[6]);
                        SpriteSheet sprite = null;
                        for(SpriteSheet spriteSheet : spriteSheets){
                            if(spriteSheet.getFilename().equals(objValues[0])) sprite = spriteSheet ;
                        }
                        MapObject mapObject = new MapObject(new Avatar(objValues[0], new StaticAnimation(Integer.valueOf(objValues[1]), Integer.valueOf(objValues[2]))), Integer.parseInt(objValues[4]), Integer.parseInt(objValues[3]), Integer.parseInt(objValues[5]), Integer.parseInt(objValues[6]));
                        world.addGameObject(mapObject);
                    }


                } else {
                    String[] spriteValues = spriteSheetString[i].split(",");
                    String fileName = spriteValues[0];
                    int bits = Integer.parseInt(spriteValues[1]);
                    int frames = Integer.parseInt(spriteValues[2]);
                    //SpriteSheet spriteSheet = new SpriteSheet(fileName, bits, frames, false);
                   // spriteSheets.add(spriteSheet);
                }

            }
          /*  for (int i = 0; i < objects.length; i++) {
                System.out.println(objects[i]);

            }

           /* BASE64Decoder decoder = new BASE64Decoder();
            byte[] imageByte;

            for (int i = 0; i <obj.length ; i++) {
                String[] objContent = obj[i].split("&");
                imageByte = decoder.decodeBuffer(objContent[0]);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                BufferedImage image = ImageIO.read(bis);
                bis.close();

                int posY = Integer.valueOf(objContent[4]);
                int posX = Integer.valueOf(objContent[3]);
                int sizeX = Integer.valueOf(objContent[1]);
                int sizeY = Integer.valueOf(objContent[2]);
                //MapObject hf = new MapObject(SwingFXUtils.toFXImage(image, null), posY, posX, sizeX, sizeY);
                //map.addGameObject(hf);
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return map;
        return world;
    }
}
