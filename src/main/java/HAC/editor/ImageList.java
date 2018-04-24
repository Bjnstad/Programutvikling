package main.java.HAC.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

/**
 * This class contains a list of images.
 * @author ceciliethoresen
 */
public class ImageList {


    private HashMap<String, Image> resourceMap;

    /**
     * This is our imageList.
     * @author ceciliethoresen
     */
    public ImageList() {
        this.resourceMap = new HashMap<>();
    }

    static final File dir = new File("assets/gameobjects"); // File representing the folder that you select using a FileChooser

    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp", "jpg"
    };

    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() { // filter to identify images based on their extensions

        /**
         *
         * @param dir
         * @param name
         * @return
         */
        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    /**
     * Lists the cells to get all assets.
     * @return list of cells
     * @author ceciliethoresen
     */
    public ListCell<String> getAllAssets() {
        //File folder = new File("assets");
        //if(folder == null) return null;

        return (new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (dir.isDirectory()){
                        for (final File f : dir.listFiles(IMAGE_FILTER)){
                            if (name.equals(f.getName())) {
                                Image image = new Image(f.toURI().toString());
                                imageView.setImage(image);

                                resourceMap.put(f.getName(), image);
                                setText(f.getName());
                            }
                        }
                        setGraphic(imageView);
                    }
                }
            }
        });
    }


    /**
     * Observable list to get all names.
     * @return names?
     */
    public ObservableList<String> getAllNames() {
        ObservableList<String> res = FXCollections.observableArrayList();

        if (dir.isDirectory()) for (final File f : dir.listFiles(IMAGE_FILTER)) {
            System.out.println(getFileExtension(f));

            if(getFileExtension(f).equals("png")) {
                System.out.println(f.getName());
                res.add(f.getName());
            }
        }

        return res;
    }

    /**
     * Gets the file extension from file.
     * @param file in game.
     * @return a string of the file.
     * @author
     */
    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Gets resource
     * @param name of the file.
     * @return map resource
     */
    public Image getResource(String name)
    {
        return resourceMap.get(name);
    }

}

