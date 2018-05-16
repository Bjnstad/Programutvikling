package hac.model.filehandler;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

/**
 * FileHandler is the base class for all file I/O. Which allows the application to
 * export, import games and maps.
 *
 * @see ExportMap
 * @see ImportMap
 * @author Henry Tran - s315309
 *
 */
public abstract class FileHandler {
    public String SPRITE_CONTENT = "%";
    public String SPRITE_POSITION ="&";
    public String NEW_LINE ="§";
    public String INLINE_CONTENT =",";
    public String MAP_SIZE = "$";
    public String GAME_SAVE_STATE = "@";
    public String OBJECT = "!";
    public String MULTI_ANIMATION = "#";
    public String SINGLE_ANIMATION = "=";
    public String STATIC_ANIMATION = "?";

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
    
    public BufferedImage encodeStringToImage(String imageString) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] imageByte = decoder.decodeBuffer(imageString);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        BufferedImage image = ImageIO.read(bis);
        bis.close();
        
        return image;
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
     * @param spriteWidth  pixel size per sub image in spritesheet (e.g 32x32, 64x64, 128x128...).
     * @param spriteHeight pixel size per sub image in spritesheet (e.g 32x32, 64x64, 128x128...).
     * @param cols  number of columns on image/spritesheet.
     * @param rows  number of rows on image/spritesheet.
     * @param fileName the name of the image file.
     */
    public void saveSpriteInput(Image image, int spriteHeight, int spriteWidth, int cols, int rows, String fileName){
        String base64String = encodeImageToString(SwingFXUtils.fromFXImage(image, null), "png");
        base64String = base64String.substring(0, base64String.length()-5);

        StringBuilder sb = new StringBuilder();
        sb.append(fileName);
        sb.append("#");
        sb.append(spriteHeight);
        sb.append("#");
        sb.append(spriteWidth);
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

    public static void showAlert(String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

}
