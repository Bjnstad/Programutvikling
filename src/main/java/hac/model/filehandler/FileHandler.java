package main.java.hac.model.filehandler;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * FileHandler is the base class for all file I/O. Which allows the application to
 * export, import games and maps.
 *
 * @see ExportMap
 * @see ImportGame
 * @author Henry Tran - s315309
 *
 */
public class FileHandler {


    /**
     * Returns an image in Byte64 String.
     * @param image desired image to be converted to Byte64 String.
     * @param type image extension. (".png", ".jpg" etc..)
     * @return Byte64 string of image.
     */
    public String encodeImageToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }


    /**
     * Creates file to machine.
     * @param file desired file to be written.
     * @param content the content to be written to machine.
     */
    public void createFile(File file, String content){

        try(FileOutputStream outputStream = new FileOutputStream(file)){
            if(!file.exists()){
                file.createNewFile();
            }

            byte[] contentInBytes = content.getBytes();

            outputStream.write(contentInBytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates .ahac files from imported spritesheets.
     * @param image the image of the spritesheet.
     * @param bits  pixel size per sub image in spritesheet (e.g 32x32, 64x64, 128x128...).
     * @param cols  number of columns on image/spritesheet.
     * @param rows  number of rows on image/spritesheet.
     * @param fileName the name of the image file.
     */
    public void saveSpriteInput(Image image, int bits, int cols, int rows, String fileName){
        String base64String = encodeImageToString(SwingFXUtils.fromFXImage(image, null), "png");
        base64String = base64String.substring(0, base64String.length()-5);

        StringBuilder sb = new StringBuilder();
        sb.append(fileName);
        sb.append("#");
        sb.append(bits);
        sb.append("#");
        sb.append(cols);
        sb.append("#");
        sb.append(rows);
        sb.append("#");
        sb.append(base64String);

        String content = sb.toString();
        File file = new File("assets/spritesheets/"+fileName+".ahac");
        createFile(file, content);

    }

}
