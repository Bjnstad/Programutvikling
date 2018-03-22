package application.controller;

import HAC.sprite.Sprite;
import HAC.HacEditor;
import application.State;
import HAC.filehandler.FileHandler;
import HAC.world.GameMap;
import HAC.world.GameObject;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import HAC.editor.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;

public class EditorController implements Controller {

    private MainController mainController;
    private FileHandler fileHandler;
    private ImageList imageList;
    private HacEditor map;
    final FileChooser fileChooser = new FileChooser();
    private EditorHandler editorHandler;


    @FXML
    Canvas graphics;

    @FXML
    ListView listView;




   // public EditorController() {
   //     super(State.EDITOR);
   // }

    @FXML
    public void newFile(ActionEvent event){
        mainController.setState(State.MAIN_MENU);
    }

    @Override
    public void setMainController (MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initiate () {
        FileHandler fileHandler = new FileHandler();

        imageList = new ImageList();

        listView.setItems(imageList.getAllNames());

        listView.setCellFactory(param -> imageList.getAllAssets());

        map = new HacEditor(new GameMap(300,300, new Sprite("background", 32)), graphics);

        editorHandler = new EditorHandler(mainController, map);

        graphics.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map.move(event.getX(), event.getY());
                map.render();
            }
        });


        listView.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {

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
                VBox Vertikalboks = new VBox(sizeX, inputSizeX, sizeY, inputSizeY,posX, inputPosX, posY, inputPosY);

                root.setLeft(Vertikalboks);
                root.setCenter(submit);
                primaryStage.show();

                submit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        int inputX = Integer.parseInt(inputSizeX.getText());
                        int inputY = Integer.parseInt(inputSizeY.getText());

                        int posX = Integer.parseInt(inputPosX.getText());
                        int posY = Integer.parseInt(inputPosY.getText());

                        listView.getSelectionModel().getSelectedItems();

                        GameObject object = new GameObject(imageList.getResource(listView.getSelectionModel().getSelectedItem().toString()), inputX, inputY);
                        map.setGameObject(object, posX, posY);
                        map.render();
                    }
                });
            }
        });


    }

    @FXML
    private void close(ActionEvent event) {
        mainController.setState(State.MAIN_MENU);
    }

    @FXML
    private void save(ActionEvent event){
        map.saveFile();
        System.out.println("Saved");
    }

    @FXML
    private void Import(ActionEvent event){
        System.out.println("hei");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            map.openFile(file);
        }

    }


    @Override
    public void onClose () {

    }

    @Override
    public EventHandler<KeyEvent> getEventHandler() {
        return (event -> {
            editorHandler.getEventHandler(event);
        });
    }




}
