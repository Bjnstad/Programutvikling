package hac.model.filehandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;

/**
 * SpriteSheet Class
 *
 * @author Henry Tran - s315309
 */
public class SpriteSheet {

    private final int max_bits = 128;

    private BufferedImage spriteSheet;
    private String filename;
    private int bits;
    private int columns;
    private int rows;

    public SpriteSheet(String filename){
        load("assets/spritesheets/" + filename + ".ahac");
    }

    public void load(String filepath) {
        File file = new File(filepath);

        BufferedReader b = null;
        try {
           b = new BufferedReader(new FileReader(file));
            String str = b.readLine().toString();
            String[] values = str.split("#");
            String fileName = values[0].substring(1);
            int bits = Integer.parseInt(values[1]);
            int cols = Integer.parseInt(values[2]);
            int rows = Integer.parseInt(values[3]);

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] imageByte = decoder.decodeBuffer(values[4]);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            BufferedImage spriteImage = ImageIO.read(bis);
            this.bits = bits;
            this.columns = cols;
            this.rows = rows;
            this.filename = fileName;

            // Resize
            resize(spriteImage, max_bits / bits);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param upload
     * @param multiplier
     */
    private void resize(BufferedImage upload, int multiplier) {
        double width = upload.getWidth();
        double height = upload.getHeight();

        BufferedImage resizedImage = new BufferedImage((int)(width * multiplier), (int)(height * multiplier), upload.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(upload, 0, 0, (int)(width * multiplier), (int)(height * multiplier), null);
        g.dispose();

        this.spriteSheet = resizedImage;
    }

    /**
     * Gets the sprite and declaring it in height and width.
     * @param gridX is the width in the grid.
     * @param gridY is the heigth in the grid.
     * @return spritesheet that gets the subImage with sizes in the grid of the gameboard.
     */
    public BufferedImage getSprite(int gridX, int gridY){
        return spriteSheet.getSubimage(gridX * max_bits, gridY * max_bits, max_bits, max_bits);
    }


    public String getFilename() {
        return filename;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }


}
