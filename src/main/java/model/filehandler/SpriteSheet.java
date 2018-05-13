package main.java.model.filehandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

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

        // Parse file here:




        this.bits = bitsfrafil;
        this.columns = columnsfrafil;
        this.rows = rowsfrafil;

        // Resize
        resize(file);
    }


    private void resize(BufferedImage upload) {
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
        return spriteSheet.getSubimage(gridX * bits, gridY * bits, bits, bits);
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
