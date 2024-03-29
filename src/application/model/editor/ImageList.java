package application.model.editor;

import hac.model.filehandler.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import hac.model.object.predefined.MapObject;
import hac.model.filehandler.SpriteSheet;
import hac.model.object.sprite.Avatar;
import hac.model.object.sprite.animation.StaticAnimation;
import sun.misc.BASE64Decoder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * This class includes the logic behind the vertical and horizontal listView for Editor.
 *
 * @author Henry Tran - s315309
 */
public class ImageList {

    private ObservableList<ImageItem> result = FXCollections.observableArrayList();
    private ObservableList<ImageItem> spriteBottom = FXCollections.observableArrayList();
    private ListView spriteListView;
    private ListView assetsListView;
    private MapObject mapObject;
    private ImageItem imageItem;
    /**
     * Static variable defining the folder that you select using FileChooser
     */
    static final File dir = new File("assets/spritesheets");

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
                    FileHandler.showAlert("Error","Something went wrong");
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
                imageItem = result.get(assetsListView.getSelectionModel().getSelectedIndex());

                MapObject object = new MapObject(new Avatar(imageItem.getFileName(), new StaticAnimation(imageItem.getX(), imageItem.getY())),  1, 1);
                mapObject = object;


            }
        });
    }

    /**
     * Loops through directory for ".ahac" files and parses each file.
     * Adding parsed results in ObservableList result.
     *
     * @param result the list to add the results to.
     * @return the list with the parsed content added.
     */
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


