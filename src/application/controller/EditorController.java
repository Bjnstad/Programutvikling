package application.controller;

import game.GameState;
import game.State;
import game.filehandler.FileHandler;
import game.utils.Offset;
import game.world.GameMap;
import game.world.GameObject;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import game.editor.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;




public class EditorController extends GameState implements Controller {

    private MainController mainController;
    private FileHandler fileHandler;

    private GameMap map;

    private Image tempImage = new Image("https://i.pinimg.com/originals/6f/6e/c3/6f6ec310eedfbcb45f300d24d0ea0cda.png");

    private ImageList imageList;

    @FXML
    Canvas graphics;

    @FXML
    ListView listView;


    public EditorController() {
        super(State.EDITOR);
    }

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



        map = new GameMap(300,300);


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

                        System.out.println(inputSizeX.getText() + inputSizeY.getText());

                    }
                });
            }
        });


    }

    @FXML
    private void close(ActionEvent event) {
        mainController.setState(State.MAIN_MENU);
    }

    @Override
    public void render () {
        if(map == null) return; // No map set
        GraphicsContext gc = graphics.getGraphicsContext2D();
        Offset offset = calcOffset(graphics); // Calculate values for offset


        graphics.setWidth(651);
        graphics.setHeight(560);

        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getWidth());
        map.render(gc, offset, true, null);



    }

    @Override
    public void onClose () {

    }

    private Offset calcOffset(Canvas c) {
        Offset o = new Offset();
        if(c.getWidth() < c.getHeight()) {
            o.setSize(c.getWidth() / GameMap.MIN_SIZE_X);
            o.setOffsetY((c.getHeight() - c.getWidth())/2);
        } else {
            o.setSize(c.getHeight() / GameMap.MIN_SIZE_Y);
            o.setOffsetX((c.getWidth() - c.getHeight())/2);
        }
        return o;
    }


    @Override
    public EventHandler<KeyEvent> getEventHandler() {
        return (event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    mainController.setState(State.MAIN_MENU);
                    break;
            }
        });
    }


}
