package main.java.controller;

import main.java.model.filehandler.SpriteSheet;
import main.java.model.world.GameMap;
import main.java.model.world.GameObject;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import main.java.model.editor.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;

/**
 * Implements the EditorController to Controller.
 * @author
 */
public class EditorController implements Controller {

    private MainController mainController;
    private ImageList imageList;
    private HACEditor map;
    private FileChooser fileChooser = new FileChooser();
    private EditorHandler editorHandler;

    @FXML
    Canvas graphics;

    @FXML
    ListView listView;

   // public EditorController() {
   //     super(State.EDITOR);
   // }

    /**
     * This method creates a new file.
     * @param event allows us to access the properties of ActionEvent
     */
    @FXML
    public void newFile(ActionEvent event){
        mainController.setState(State.MAIN_MENU);
    }

    /**
     * This method sets the mainController.
     * @param mainController shows what on the screen of the game.
     */
    @Override
    public void setMainController (MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Initiates imageList (fileHandler)
     */
    @Override
    public void initiate () {
        //FileHandler fileHandler = new FileHandler();

        imageList = new ImageList();

        listView.setItems(imageList.getAllNames());

        listView.setCellFactory(param -> imageList.getAllAssets());


        map = new HACEditor(new GameMap(25,25, new SpriteSheet("background", 32)), graphics);
        //map.getCamera().setCanvas(graphics);


        editorHandler = new EditorHandler(mainController, map);

        /*
        graphics.setOnDragDetected(new EventHandler<MouseEvent>() {
            /**
             * This method makes the object move with a mouseclick.
             * @param event allows us to access the properties of MouseEvent.

            @Override
            public void handle(MouseEvent event) {
                map.move(event.getX(), event.getY());
            }
        });*/


        listView.setOnMouseClicked(new EventHandler<MouseEvent>(){

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
                VBox Vertikalboks = new VBox(sizeX, inputSizeX, sizeY, inputSizeY,posX, inputPosX, posY, inputPosY);

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

                        int posX = Integer.parseInt(inputPosX.getText());
                        int posY = Integer.parseInt(inputPosY.getText());
                        listView.getSelectionModel().getSelectedItems();


                        GameObject object = new GameObject(imageList.getResource(listView.getSelectionModel().getSelectedItem().toString()), posX, posY, inputX, inputY);
                        map.setGameObject(object);


                        System.out.println(object.getAsset());


                      /*  graphics.setOnMouseClicked((new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                GameObject object = new GameObject(imageList.getResource(listView.getSelectionModel().getSelectedItem().toString()), inputX, inputY);
                                System.out.println(object.getAsset());

                                map.setGameObject(object, (int)event.getX(), (int)event.getY());
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

    /**
     * This method closes the state of the game.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    private void close(ActionEvent event) {
        mainController.setState(State.MAIN_MENU);
    }

    /**
     * This method makes it possible to save a file.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    private void save(ActionEvent event){
        map.saveFile();
        System.out.println("Saved");
    }

    /**
     * This method makes it possible to import a file.
     * @param event allows us to access the properties of ActionEvent.
     */
    @FXML
    private void Import(ActionEvent event){
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("mHac files", "*.mhac");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            map.openFile(file);
        }
    }

    /**
     * Is being used to decorate a method that is being called when a session is closing.
     */
    @Override
    public void onClose () {

    }

    /**
     * This method gets the event handler.
     * @return the current event.
     */
    @Override
    public EventHandler<KeyEvent> getEventHandler() {
        return (event -> {
            editorHandler.getEventHandler(event);
        });
    }
}
