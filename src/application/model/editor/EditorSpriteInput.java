package application.model.editor;

import hac.model.filehandler.FileHandler;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import hac.model.filehandler.ExportMap;

/**
 * Created by henrytran1 on 11/05/2018.
 */
public class EditorSpriteInput {
    private int rows;
    private int columns;
    private int bits;


    public Stage popUp(Image image, String fileName, ImageList imageList, ObservableList<ImageItem> result) {
        final Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane root = new BorderPane();

        primaryStage.setTitle("Add asset");
        primaryStage.setScene(new Scene(root));

        Label rowLabel = new Label("Rows:");
        TextField inputRow = new TextField ();

        Label columnLabel = new Label("Columns:");
        TextField inputColumns = new TextField ();


        Button submit = new Button("Submit");

        submit.setOnAction(e -> {
            rows = Integer.valueOf(inputRow.textProperty().getValue());
            columns = Integer.valueOf(inputColumns.textProperty().getValue());
            int spriteWidth = ((int)image.getWidth()/columns);
            int spriteHeight = ((int)image.getHeight()/rows);


            try {
                //SpriteSheet test = new SpriteSheet(fileName, bits, columns, false);
                Image[] imgArray = new Image[rows * columns];
                for (int y = 0, k = 0; y < rows; y++) {
                    for (int x = 0; x < columns; x++, k++) {
                     //   Image img = SwingFXUtils.toFXImage(test.getSprite(x,y), null);

                    }
                }
                ExportMap exportSprite = new ExportMap();
                exportSprite.saveSpriteInput(image, spriteHeight, spriteWidth, columns,rows, fileName);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);
                ImageItem imageItem = new ImageItem(imageView,image,fileName,columns,rows);
                imageItem.setSpriteHeight(spriteHeight);
                imageItem.setSpriteWidth(spriteWidth);
                imageList.getSpriteBottom().add(imageItem);
                primaryStage.close();
            }catch(Exception y) {
                y.printStackTrace();
                FileHandler.showAlert("Import error!", "Are you sure each sub image is one of the following size? 16x16, 32x32, 64x64, 128x128?");
            }
        });


        VBox Vertikalboks = new VBox(rowLabel, inputRow, columnLabel, inputColumns,submit);

        ImageView imageView = new ImageView();
        imageView.setImage(image);

        root.setLeft(Vertikalboks);
        root.setCenter(imageView);
        return primaryStage;
    }



    public int getRow() {
        return rows;
    }

    public void setRow(int row) {
        this.rows = row;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getBits() {
        return bits;
    }
}

