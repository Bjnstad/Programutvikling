package game.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by henrytran1 on 20/02/2018.
 */
public class ImageList {

    private static final String PREFIX =
            "http://icons.iconarchive.com/icons/jozef89/origami-birds/72/bird";

    private static final String SUFFIX =
            "-icon.png";

    private static final ObservableList<String> birds = FXCollections.observableArrayList(
            "-black",
            "-blue",
            "-red",
            "-red-2",
            "-yellow",
            "s-green",
            "s-green-2"
    );

    private static final ObservableList<Image> birdImages = FXCollections.observableArrayList();


    public void initiate() {
        birds.forEach(bird -> birdImages.add(new Image(PREFIX + bird + SUFFIX)));

        ListView<String> birdList = new ListView<>(birds);
        birdList.setCellFactory(param -> new BirdCell());
        birdList.setPrefWidth(180);

        VBox layout = new VBox(birdList);
        layout.setPadding(new Insets(10));

        //stage.setScene(new Scene(layout));
        //stage.show();
    }

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

    private class BirdCell extends ListCell<String> {
        private final ImageView imageView = new ImageView();

        public BirdCell() {
            ListCell thisCell = this;

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setAlignment(Pos.CENTER);

            setOnDragDetected(event -> {
                if (getItem() == null) {
                    return;
                }

                ObservableList<String> items = getListView().getItems();

                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(getItem());
                dragboard.setDragView(
                        birdImages.get(
                                items.indexOf(
                                        getItem()
                                )
                        )
                );
                dragboard.setContent(content);

                event.consume();
            });

            setOnDragOver(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            });

            setOnDragEntered(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(0.3);
                }
            });

            setOnDragExited(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(1);
                }
            });

            setOnDragDropped(event -> {
                if (getItem() == null) {
                    return;
                }

                Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasString()) {
                    ObservableList<String> items = getListView().getItems();
                    int draggedIdx = items.indexOf(db.getString());
                    int thisIdx = items.indexOf(getItem());

                    Image temp = birdImages.get(draggedIdx);
                    birdImages.set(draggedIdx, birdImages.get(thisIdx));
                    birdImages.set(thisIdx, temp);

                    items.set(draggedIdx, getItem());
                    items.set(thisIdx, db.getString());

                    List<String> itemscopy = new ArrayList<>(getListView().getItems());
                    getListView().getItems().setAll(itemscopy);

                    success = true;
                }
                event.setDropCompleted(success);

                event.consume();
            });

            setOnDragDone(DragEvent::consume);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                imageView.setImage(
                        birdImages.get(
                                getListView().getItems().indexOf(item)
                        )
                );
                setGraphic(imageView);
            }
        }
    }

}


