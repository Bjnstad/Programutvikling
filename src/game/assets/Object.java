package game.assets;

import game.FileHandler.Asset;

import java.io.File;

public class Object implements Asset {
    private String name;
    private File image;

    public Object(String name, File image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public File getImage() {
        return image;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
