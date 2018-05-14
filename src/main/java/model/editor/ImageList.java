package main.java.model.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
import main.java.model.object.sprite.Avatar;
import main.java.model.object.sprite.animation.SingleAnimation;
import sun.misc.BASE64Decoder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * This class contains a list of images.
 * @author
 */
public class ImageList {

    private ObservableList<ImageItem> result = FXCollections.observableArrayList();
    private ObservableList<ImageItem> spriteBottom = FXCollections.observableArrayList();
    private ListView spriteListView;
    private ListView assetsListView;
    private MapObject mapObject;
    private ImageItem imageItem;


    static final File dir = new File("assets/spritesheets"); // File representing the folder that you select using a FileChooser

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


    /**
     * This method contains the imageList.
     */
    public ImageList(ListView spriteListView, ListView assetsListView) {
        this.spriteListView = spriteListView;
        this.assetsListView = assetsListView;



        initiateSpriteListView();
        handleSpriteListView();
        handleAssetsListView();

    }


    public void initiateSpriteListView(){
        Label placeholder = new Label();
        placeholder.setText("Please Import SpriteSheet!");
        if(spriteListView.getItems().size() < 1) spriteListView.setPlaceholder(placeholder);


        spriteListView.setItems(openSpriteEditorSave(getSpriteBottom()));

        spriteListView.setCellFactory(param -> new ListCell<ImageItem>() {
            @Override
            protected void updateItem(ImageItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setGraphic(item.getImageView());
                }
            }
        });

    }

    public void handleSpriteListView(){
        spriteListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ImageItem imageItem = spriteBottom.get(spriteListView.getSelectionModel().getSelectedIndex());
                if(result.size() > 1){
                    assetsListView.getItems().clear();
                }
                try {
                    SpriteSheet test = new SpriteSheet(imageItem.getFileName());
                    for (int y = 0; y < imageItem.getY(); y++) {
                        for (int x = 0; x < imageItem.getX(); x++) {
                            Image img = SwingFXUtils.toFXImage(test.getSprite(x,y), null);
                            ImageItem imageItemExtracted = new ImageItem(new ImageView(img), img, imageItem.getFileName(), x,y);
                            imageItemExtracted.setBits(imageItem.getBits());
                            imageItemExtracted.setFrames(imageItem.getX());
                            imageItemExtracted.setColumns(imageItem.getY());
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

    public void handleAssetsListView(){
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

                        imageItem = result.get(assetsListView.getSelectionModel().getSelectedIndex());

                        MapObject object = new MapObject(new Avatar(imageItem.getFileName(), new SingleAnimation(imageItem.getFrames(), imageItem.getX())),  1, 1, inputX, inputY);
                        mapObject = object;

                        primaryStage.close();


                    }
                });
            }
        });
    }




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



    public ObservableList<ImageItem> getResult() {
        return result;
    }

    public ObservableList<ImageItem> getSpriteBottom() {
        return spriteBottom;
    }

    public MapObject getMapObject() {
        return mapObject;
    }



    public ImageItem getImageItem() {
        return imageItem;
    }



}


