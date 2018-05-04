package main.java.model.filehandler;

import main.java.model.SpriteSheet;
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
            String[] obj = str.split(",");
            for (int i = 0; i <= Integer.valueOf(obj[8]); i++) {

                System.out.println(obj[i]);
                System.out.println(System.lineSeparator());
            }

            /*for (int i = 0; i <= Integer.valueOf(obj[8]) ; i++) {
                System.out.println(obj[i]);
                System.out.println(System.lineSeparator());
            }*/



/*            String[] obj = b.readLine().split("#");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] imageByte;

            for (int i = 0; i <obj.length ; i++) {
                System.out.println("THE NUMBER IS " + i);

                String[] objContent = obj[i].split("&");
                imageByte = decoder.decodeBuffer(objContent[0]);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                BufferedImage image = ImageIO.read(bis);
                bis.close();

                GameObject hf = new GameObject(SwingFXUtils.toFXImage(image, null), Integer.valueOf(objContent[1]), Integer.valueOf(objContent[2]), Integer.valueOf(objContent[3]), Integer.valueOf(objContent[4]));

                System.out.println(map.addGameObject(hf));
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // return map;
    }
}
