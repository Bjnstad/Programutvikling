package main.java.model.filehandler;

import main.java.model.object.MapObject;
import main.java.model.world.GameMap;
import main.java.model.world.World;

import java.io.*;
import java.util.ArrayList;

/**
 * This class content
 *
 * @author
 */
public class HacParser {

    /**
     * This method helps us to parse a file.
     * ... kommer mer
     * ...
     * @param file ......
     */
    public GameMap parseFile(File file){
        World world = new World();

        GameMap map = new GameMap(20, 20, new SpriteSheet("background", 32));
        world.setGameMap(map);

        ArrayList<SpriteSheet> spriteSheets = new ArrayList<>();

        try {

            BufferedReader b = new BufferedReader(new FileReader(file));

            String strSprite = b.readLine().toString();

            String[] spriteSheetString =strSprite.split("&");


          //  String[] objects = b.readLine().split("&");

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
                        MapObject mapObject = new MapObject(sprite, Integer.parseInt(objValues[4]), Integer.parseInt(objValues[3]), Integer.parseInt(objValues[5]), Integer.parseInt(objValues[6]));
                        world.addGameObject(mapObject);
                    }


                } else {
                    String[] spriteValues = spriteSheetString[i].split(",");
                    String fileName = spriteValues[0];
                    int bits = Integer.parseInt(spriteValues[1]);
                    int frames = Integer.parseInt(spriteValues[2]);
                    SpriteSheet spriteSheet = new SpriteSheet(fileName, bits, frames, false);
                    spriteSheets.add(spriteSheet);
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
        return map;
    }
}
