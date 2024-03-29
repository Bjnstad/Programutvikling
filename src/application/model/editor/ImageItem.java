package application.model.editor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * This class holds the necessary information for each item in the listView.
 * Each item in listView are using this class.
 * Holds information of each item in listView.
 */
public class ImageItem {
    private ImageView imageView;
    private String fileName;
    private int x;
    private int y;
    private Image image;
    private int bits;
    private int frames;
    private int columns;

    public ImageItem(ImageView imageView, Image image, String fileName, int x, int y) {
        this.imageView = imageView;
        this.image = image;
        this.fileName = fileName;
        this.x = x;
        this.y = y;
    }

    public ImageView getImageView() {
        return imageView;
    }



    public Image getImage() {
        return image;
    }

    public String getFileName() {
        return fileName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBits() {
        return bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }
    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}

