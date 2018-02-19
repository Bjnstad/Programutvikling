package game.controller;

import FileHandler.FileHandler;
import game.GameState;
import game.State;
import game.world.GameMap;
import game.world.GameObject;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;


public class EditorController extends GameState implements Controller {

    private MainController mainController;
    private FileHandler fileHandler;

    private GameMap map;

    @FXML
    Canvas graphics;

    @FXML
    ListView listView;


    public EditorController() {
        super(State.EDITOR);
    }

    @FXML
    public void newFile(ActionEvent event){
        System.out.println("");


    }

    @Override
    public void setMainController (MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initiate () {
        FileHandler fileHandler = new FileHandler();


        //ObservableList<String> items = fileHandler.getAllAssets();

        listView.setItems(fileHandler.getAllNames());
        listView.setCellFactory(param -> fileHandler.getAllAssets());
        map = new GameMap(300,300);


    }


    @Override
    public void render () {

        if(graphics != null && map != null) {
            GraphicsContext gc = graphics.getGraphicsContext2D();

            graphics.setWidth(mainController.getWidth());
            graphics.setHeight(mainController.getHeight());

            int calcX = (int)(mainController.getWidth() / GameMap.MIN_SIZE_X);
            int calcY = (int)(mainController.getHeight() / GameMap.MIN_SIZE_Y);

            double restX = 0;
            double restY = 0;
            int calc = 0;
            if(calcX < calcY) {
                calc = calcX;
                restX = (mainController.getHeight() - mainController.getWidth()) / 2;
            } else {
                calc = calcY;
                restY = (mainController.getWidth() - mainController.getHeight()) / 2;
            }



            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getWidth());
            for(int y = 0; y < GameMap.MIN_SIZE_Y; y++) {
                for (int x = 0; x < GameMap.MIN_SIZE_X; x++) {
                    gc.setFill(map.getBackgroundColor());
                    gc.fillRect(x * calc + restY, y * calc + restX, calc, calc);
                }
            }


            for(int y = 0; y < map.getHeight(); y++) {
                for (int x = 0; x < map.getWidth(); x++) {
                    GameObject gameObject = map.getObject(x, y);
                    if(gameObject != null) {
                        // Is object

                        gc.setFill(gameObject.getAsset());
                        gc.fillRect(x * calc + restY, y * calc  + restX, calc, calc);
                    }
                }
            }

            // RENDER PLAYER
            gc.setFill(Color.YELLOW);
            gc.fillRect(GameMap.MIN_SIZE_X/2 * calc + restY - calc/2, GameMap.MIN_SIZE_Y/2 * calc + restX - calc/2, calc, calc);



            for(int y = 0; y < map.getHeight(); y++) {
                for (int x = 0; x < map.getWidth(); x++) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x * calc + restY, y * calc + restX, calc, 1);
                    gc.fillRect(x * calc + restY , y * calc + restX, 1, calc);
                }
            }

            gc.setFill(Color.BLACK);
            gc.fillRect(0,  0, restY, mainController.getHeight());
            gc.fillRect(mainController.getWidth() - restY,  0, restY, mainController.getHeight());

            gc.fillRect(0,  0, mainController.getWidth(), restX);
            gc.fillRect(0,  mainController.getHeight() - restX, mainController.getWidth(), restX);
        }

    }

    @Override
    public void onClose () {

    }


    @Override
    public EventHandler<KeyEvent> getEventHandler() {
        return null;
    }


}
