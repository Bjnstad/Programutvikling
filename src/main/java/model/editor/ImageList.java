package main.java.model.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.model.object.MapObject;
import main.java.model.filehandler.SpriteSheet;

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
    private ObservableList<ImageItem> result = FXCollections.observableArrayList();
    private ObservableList<ImageItem> spriteBottom = FXCollections.observableArrayList();
    private ListView spriteListView;
    private ListView assetsListView;
    private MapObject mapObject;
    private ImageItem imageItem;

    /**
     * This method contains the imageList.
     */
    public ImageList(ListView spriteListView, ListView assetsListView) {
        this.spriteListView = spriteListView;
        this.assetsListView = assetsListView;
        this.resourceMap = new HashMap<>();
        Label placeholder = new Label();
        placeholder.setText("Please Import SpriteSheet!");
        if(spriteListView.getItems().size() < 1){
            spriteListView.setPlaceholder(placeholder);
        }


    }

    public void handleSpriteListView(){
        spriteListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ImageItem imageItem = spriteBottom.get(spriteListView.getSelectionModel().getSelectedIndex());
                System.out.println(imageItem.getFileName());

                if(result.size() > 1){
                    assetsListView.getItems().clear();
                }
                try {
                    SpriteSheet test = new SpriteSheet(imageItem.getFileName(), imageItem.getBits(), imageItem.getX(), false);
                    for (int y = 0; y < imageItem.getY(); y++) {
                        for (int x = 0; x < imageItem.getX(); x++) {
                            Image img = SwingFXUtils.toFXImage(test.getSprite(x,y), null);
                            ImageItem imageItemExtracted = new ImageItem(new ImageView(img), img, imageItem.getFileName(), x,y);
                            imageItemExtracted.setBits(imageItem.getBits());
                            imageItemExtracted.setFrames(imageItem.getX());
                            result.add(imageItemExtracted);
                        }
                    }
                    assetsListView.setItems(result);
                    assetsListView.setCellFactory(param -> new ListCell<ImageItem>() {
                        @Override
                        protected void updateItem(ImageItem item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                setGraphic(item.getImageView());
                            }
                        }
                    });

                }catch(Exception y){
                    y.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Look, an Error Dialog");
                    alert.setContentText("Ooops, there was an error!");

                    alert.showAndWait();
                }


            }
        });
    }

    public void handleAssetsListView(Canvas graphics){
        assetsListView.setOnMouseClicked(new EventHandler<MouseEvent>(){

            /**
             * This method handles an action of mouseEvent.
             * @param mouseEvent is an event which indicates that a mouse action occurred in a component.
             */


            @Override
            public void handle(MouseEvent mouseEvent) {

                final Stage primaryStage = new Stage();
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                BorderPane root = new BorderPane();

                primaryStage.setTitle("Add asset");
                primaryStage.setScene(new Scene(root));

                double height = 60;
                double width = 70;

                Button submit = new Button("Submit");

                submit.setMinSize(3*width, height);

                Label sizeX = new Label("Size X:");
                TextField inputSizeX = new TextField ();

                Label sizeY = new Label("Size Y:");
                TextField inputSizeY = new TextField ();

                Label posX = new Label("Pos X:");
                TextField inputPosX = new TextField ();

                Label posY = new Label("Pos Y:");
                TextField inputPosY = new TextField ();

                CheckBox checkBox = new CheckBox("Collision?");
                VBox Vertikalboks = new VBox(sizeX, inputSizeX, sizeY, inputSizeY,checkBox);

                root.setLeft(Vertikalboks);
                root.setCenter(submit);
                primaryStage.show();

                submit.setOnAction(new EventHandler<ActionEvent>() {
                    /**
                     * This method handles som type of action.
                     * This event type is widely used to represent a variety of things
                     * @param e is a type of ActionEvent, it allows you to access the properties of ActionEvent.
                     */
                    @Override public void handle(ActionEvent e) {
                        int inputX = Integer.parseInt(inputSizeX.getText());
                        int inputY = Integer.parseInt(inputSizeY.getText());

                        String filename = String.valueOf(assetsListView.getSelectionModel().getSelectedItems());
                        String[] fileNameArr = filename.split("\\.");
                        filename = fileNameArr[0].substring(1);
                        imageItem = result.get(assetsListView.getSelectionModel().getSelectedIndex());
                        System.out.println(imageItem.getX());
                        System.out.println(imageItem.getY());


                        System.out.println(imageItem.getBits());
                        SpriteSheet spriteSheet = new SpriteSheet(imageItem.getFileName(), imageItem.getBits(), 1, false );
                        spriteSheet.setStaticImage(imageItem.getX(), imageItem.getY());
                        MapObject object = new MapObject(spriteSheet,1, 1, inputX, inputY);
                        mapObject = object;
                        graphics.setCursor(new ImageCursor(imageItem.getImage()));


                        primaryStage.close();

                      /*  graphics.setOnMouseClicked((new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                mapObject.setPosX((int)event.getX());
                                mapObject.setPosY((int)event.getY());
                                map.addGameObject(object);

                                System.out.println(object.getAsset());

                                System.out.println((int)event.getX());
                                System.out.println((int)event.getY());

                            }
                        }));

*/

                    }
                });
            }
        });
    }



    static final File dir = new File("assets/editorassets"); // File representing the folder that you select using a FileChooser

    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp", "jpg, ahac"
    };

    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() { // filter to identify images based on their extensions

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


    public ObservableList<ImageItem> openSpriteEditorSave(ObservableList<ImageItem> result){
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
                Image img = SwingFXUtils.toFXImage(spriteImage, null);
                ImageView imageView = new ImageView(img);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);
                ImageItem imageItem = new ImageItem(imageView,img,fileName,cols,rows);
                imageItem.setBits(bits);
                result.add(imageItem);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

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

    public ObservableList<ImageItem> getResult() {
        return result;
    }

    public ObservableList<ImageItem> getSpriteBottom() {
        return spriteBottom;
    }

    public MapObject getMapObject() {
        return mapObject;
    }

    public void setMapObject(MapObject mapObject) {
        this.mapObject = mapObject;
    }

    public ImageItem getImageItem() {
        return imageItem;
    }

    public void setImageItem(ImageItem imageItem) {
        this.imageItem = imageItem;
    }
}


