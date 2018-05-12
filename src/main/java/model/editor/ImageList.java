package main.java.model.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.model.object.sprite.SpriteSheet;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

/**
 * This class contains a list of images.
 * @author
 */
public class ImageList {


    private HashMap<String, Image> resourceMap;
    private Image[] images;

    /**
     * This method contains the imageList.
     */
    public ImageList() {
        this.resourceMap = new HashMap<>();
    }

    static final File dir = new File("assets/editorassets"); // File representing the folder that you select using a FileChooser

    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp", "jpg, ahac"
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
                                System.out.println(f.getName());
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

    public ObservableList<ImageView> openEditorSave(ObservableList<ImageView> result){
        for(File f : dir.listFiles()){
            BufferedReader b = null;
            try {
                b = new BufferedReader(new FileReader(f));
                String str = b.readLine().toString();
                String[] values = str.split("#");
                String fileName = values[0];
                int bits = Integer.parseInt(values[1]);
                int cols = Integer.parseInt(values[2]);
                int rows = Integer.parseInt(values[3]);
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] imageByte = decoder.decodeBuffer(values[4]);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                BufferedImage spriteImage = ImageIO.read(bis);
                System.out.println("Filnavn: " + values[0]);
                System.out.println("Bits: " + values[1]);
                System.out.println("Cols: " + values[2]);
                System.out.println("Rows: " + values[3]);
                System.out.println("Bilde: " + values[4]);

                SpriteSheet test = new SpriteSheet(fileName, bits, cols, false);

                for (int y = 0, k = 0; y < rows; y++) {
                    for (int x = 0; x < cols; x++, k++) {
                        Image img = SwingFXUtils.toFXImage(test.getSprite(x, y), null);
                        result.add(new ImageView(img));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



        return result;
    }

    public ObservableList<ImageItem> setImageItem(Image image, String filename, int x, int y) {
        ObservableList<ImageItem> result = FXCollections.observableArrayList();

        result.add(new ImageItem(new ImageView(image), filename, x, y));



        return result;
    }
    /**
     * Lists the cells to get all assets.
     * @return list of cells
     */
    public ListCell<String> setAssets() {
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
                    for(Image img : images){
                        imageView.setImage(img);
                    }
                    setGraphic(imageView);
                }
            }
        });
    }


/*
    public ListCell<String> setAssets(Image[] images, String fileName) {
        //File folder = new File("assets");
        //if(folder == null) return null;
        return new ListCell<String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    //  super.updateItem(item, empty);
                    if (item != null) {
                        box.setSpacing(10) ;
                        box.setPadding(new Insets(10, 10, 10, 10));
                        Image img = null;
                        Knjiga k = getTableView().getItems().get(getIndex());
                        img = new Image(new File(k.getMainPage()).toURI().toString());

                        imageview.setImage(img);
                        imageview.setFitHeight(320.0);
                        imageview.setFitWidth(200.0);

                    }
                    if(!box.getChildren().contains(imageview)) {
                        box.getChildren().add(imageview);
                        setGraphic(box);
                    }
                }


            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    for (int i = 0; i < images.length; i++) {
                        imageView.setImage(images[i]);
                        setText("Sprite: " + i);
                    }
                }
                setGraphic(imageView);
            }
        });
    }*/

    /**
     * Observable list to set all names.
     * @return names? res?
     */
    public ObservableList<String> setAllNames(Image[] images) {
        ObservableList<String> res = FXCollections.observableArrayList();
        for (int i = 0; i < images.length; i++) res.add(String.valueOf("Sprite: " + i));


        return res;
    }

    public int[] getName(Image[] images){
        int[] intArr = new int[images.length];
        for (int i = 0; i <images.length ; i++) {
            intArr[i] = i;
        }
        return intArr;
    }



    /**
     * Observable list to get all names.
     * @return names? res?
     */
    public ObservableList<String> getAllNames() {
        ObservableList<String> res = FXCollections.observableArrayList();

        if (dir.isDirectory()) for (final File f : dir.listFiles(IMAGE_FILTER)) {
            if(getFileExtension(f).equals("png")) {
                res.add(f.getName());
            }
        }

        return res;
    }

    /**
     * Gets the file extension from file.
     * @param file in game.
     * @return a string of the file.
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
     * Gets resource to image.
     * @param name of the file.
     * @return map resource
     */
    public Image getResource(String name)
    {
        return resourceMap.get(name);
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }
}


