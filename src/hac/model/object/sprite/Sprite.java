package hac.model.object.sprite;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import hac.model.filehandler.SpriteSheet;

/**
 * // TODO: Enemies moves to fast, better interval increment
 * This class handles animation for a character find what sprite to return respectly.
 * @author Axel Bjørnstad - s315322
 */
public class Sprite {

    private String fileName;
    private Image[][] images;
    private double width;
    private double height;

    public Sprite(String fileName) {
        this.fileName = fileName;
        SpriteSheet spriteSheet = new SpriteSheet(fileName);
        this.width = spriteSheet.getSpriteWidth();
        this.height = spriteSheet.getSpriteHeigth();
        images = new Image[spriteSheet.getRows()][spriteSheet.getColumns()];
        loadImages(spriteSheet);
    }

    private void loadImages(SpriteSheet spriteSheet) {
        for (int y = 0; y < spriteSheet.getRows(); y++) {
            for (int x = 0; x < spriteSheet.getColumns(); x++) {
                images[y][x] = SwingFXUtils.toFXImage(spriteSheet.getSprite(x, y), null);
            }
        }
    }

    public Image getSprite(int x, int y) {
        if(x < 0 || y < 0 || x > images[0].length || y > images.length) return null;
        return images[y][x];
    }

    public String getSpriteFileName() {
        return fileName;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}