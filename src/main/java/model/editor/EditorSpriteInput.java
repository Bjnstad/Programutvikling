package main.java.model.editor;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.model.object.sprite.SpriteSheet;
import main.java.model.world.MapObject;

import java.io.File;

/**
 * Created by henrytran1 on 11/05/2018.
 */
public class EditorSpriteInput {
    private int rows;
    private int columns;
    private int bits;


    public Stage popUp(Image image, String fileName, ListView listView, ImageList imageList) {
        final Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane root = new BorderPane();

        primaryStage.setTitle("Add asset");
        primaryStage.setScene(new Scene(root));

        double height = 60;
        double width = 70;


        //submit.setMinSize(3*width, height);

        Label rowLabel = new Label("Rows:");
        TextField inputRow = new TextField ();

        Label columnLabel = new Label("Columns:");
        TextField inputColumns = new TextField ();


        Button submit = new Button("Submit");

        submit.setOnAction(e -> {
            System.out.println("Row: " + inputRow.textProperty().getValue());
            System.out.println("Column: " + inputColumns.textProperty().getValue());
            rows = Integer.valueOf(inputRow.textProperty().getValue());
            columns = Integer.valueOf(inputColumns.textProperty().getValue());
            bits = ((int)image.getWidth()/columns);


            try {
                SpriteSheet test = new SpriteSheet(fileName, bits, columns, false);

                Image[] imgArray = new Image[rows * columns];
                for (int y = 0, k = 0; y < rows; y++) {
                    for (int x = 0; x < columns; x++, k++) {
                        Image img = SwingFXUtils.toFXImage(test.getSprite(x,y), null);
                        imgArray[k] = img;
                    }
                }
                listView.setCellFactory(param -> imageList.setAssets(imgArray, fileName));

            }catch(Exception y){
                y.printStackTrace();

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Ooops, there was an error!");

                alert.showAndWait();
            }



            System.out.println("DET FUNKER");
            System.out.println(Integer.parseInt(inputColumns.getText()));
            System.out.println(bits);


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
