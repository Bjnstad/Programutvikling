package main.java.model.editor;

import javafx.scene.image.ImageView;

/**
 * Created by henrytran1 on 12/05/2018.
 */
public class ImageItem {
    private ImageView imageView;
    private String fileName;
    private int x;
    private int y;

    public ImageItem(ImageView imageView, String fileName, int x, int y) {
        this.imageView = imageView;
        this.fileName = fileName;
        this.x = x;
        this.y = y;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}

