package application.controller;

import game.GameState;
import game.State;
import game.filehandler.FileHandler;
import game.world.GameMap;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import game.editor.*;


public class EditorController extends GameState implements Controller {

    private MainController mainController;
    private FileHandler fileHandler;

    private GameMap map;

    private final Image IMAGE_RUBY  = new Image("https://i.pinimg.com/originals/6f/6e/c3/6f6ec310eedfbcb45f300d24d0ea0cda.png");
    private final Image IMAGE_APPLE  = new Image("http://findicons.com/files/icons/832/social_and_web/64/apple.png");
    private final Image IMAGE_VISTA  = new Image("http://antaki.ca/bloom/img/windows_64x64.png");
    private final Image IMAGE_TWITTER = new Image("http://files.softicons.com/download/social-media-icons/fresh-social-media-icons-by-creative-nerds/png/64x64/twitter-bird.png");

    private Image[] listOfImages = {IMAGE_RUBY, IMAGE_APPLE, IMAGE_VISTA, IMAGE_TWITTER};

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

        ImageList imageList = new ImageList();
        imageList.initiate();

        //ObservableList<String> items = fileHandler.getAllAssets();
        //listView.setItems(fileHandler.getAllNames());
        //listView.setCellFactory(param -> fileHandler.getAllAssets());

        map = new GameMap(300,300);

    }

    @FXML
    private void close(ActionEvent event) {
        mainController.setState(State.MAIN_MENU);
    }

    @Override
    public void render () {
        if(map == null) return; // No map set
        GraphicsContext gc = graphics.getGraphicsContext2D();

        graphics.setWidth(651);
        graphics.setHeight(560);

        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getWidth());
        map.render(gc, true);

        /*    int calcX = (int)(651 / GameMap.MIN_SIZE_X);
            int calcY = (int)(560 / GameMap.MIN_SIZE_Y);

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
            gc.setFill(Color.RED);
            listView.getCellFactory();


            gc.drawImage(listOfImages[0], 100, 100, 100, 100);
          //  Image img = new Image((InputStream) listView.getItems().get(1));
         //   gc.drawImage(img, 100, 100, 100, 100);
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
        }*/

    }

    @Override
    public void onClose () {

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
