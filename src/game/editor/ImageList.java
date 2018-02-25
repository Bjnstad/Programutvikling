package game.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by henrytran1 on 20/02/2018.
 */
public class ImageList {


    // File representing the folder that you select using a FileChooser
    static final File dir = new File("assets");

    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp", "jpg"
    };

    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

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
                                // imageView.setImage(image);
                                setText(f.getName());
                            }
                        }
                        setGraphic(imageView);
                    }
                }
            }
        });

        //return list;

    }


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

    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

}


