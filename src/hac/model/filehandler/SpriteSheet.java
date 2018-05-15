package hac.model.filehandler;

import java.awt.image.BufferedImage;
import java.io.*;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;

/**
 *
 */
public class SpriteSheet {

    private final int max_bits = 128;

    private BufferedImage spriteSheet;
    private String filename;
    private int spriteWidth;
    private int spriteHeigth;
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
            String fileName = values[0];
            int spriteWidth = Integer.parseInt(values[2]);
            int spriteHeigth = Integer.parseInt(values[1]);
            int cols = Integer.parseInt(values[3]);
            int rows = Integer.parseInt(values[4]);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] imageByte = decoder.decodeBuffer(values[5]);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            BufferedImage spriteImage = ImageIO.read(bis);
            this.spriteWidth = spriteWidth;
            this.spriteHeigth = spriteHeigth;
            this.columns = cols;
            this.rows = rows;
            this.filename = fileName;

            // Resize
            spriteSheet = spriteImage;


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

        /*
        BufferedImage resizedImage = new BufferedImage((int)(width * multiplier), (int)(height * multiplier), upload.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(upload, 0, 0, (int)(width * multiplier), (int)(height * multiplier), null);
        g.dispose();
        */

        this.spriteSheet = upload;
    }

    /**
     * Gets the sprite and declaring it in height and width.
     * @param gridX is the width in the grid.
     * @param gridY is the heigth in the grid.
     * @return spritesheet that gets the subImage with sizes in the grid of the gameboard.
     */
    public BufferedImage getSprite(int gridX, int gridY){
        return spriteSheet.getSubimage(gridX * spriteWidth, gridY * spriteHeigth, spriteWidth, spriteHeigth);
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

    public double getSpriteHeigth() {
        return spriteHeigth;
    }

    public double getSpriteWidth() {
        return spriteWidth;
    }

}
