package application.controller;

import game.State;
import game.character.Enemy;
import game.character.Player;
import game.world.GameMap;
import game.world.GameObject;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameController implements Controller {

    private int currentLevel;
    private Player player;
    private Enemy[] enemies;
    private GameMap map;

    @FXML
    Canvas graphics;

    private MainController mainController;

    double calc = 0;



    @Override
    public void initiate() {
        this.player = new Player(new Image("https://i.pinimg.com/originals/6f/6e/c3/6f6ec310eedfbcb45f300d24d0ea0cda.png"), 4, 4);
        map = new GameMap(100, 100);

        // Adding items to map
        System.out.println(map.setGameObject(new GameObject(Color.RED, 2, 2), 7, 5));
        map.setGameObject(new GameObject(Color.BLUE, 5, 5), 12, 14);
    }


    //TODO: NEED CLEAN/REWRITE
    @Override
    public void render() {
        if(map == null) return; // No map set.

        double size;

        GraphicsContext gc = graphics.getGraphicsContext2D();

        graphics.setWidth(mainController.getWidth()); // Set width of canvas
        graphics.setHeight(mainController.getHeight()); // Set width of canvas

        double offsetX = 0;
        double offsetY = 0;

        double calcX = gc.getCanvas().getWidth() / GameMap.MIN_SIZE_X;
        double calcY = gc.getCanvas().getHeight() / GameMap.MIN_SIZE_Y;

        if(calcX < calcY) {
            size = calcX;
            offsetY = (mainController.getHeight() - mainController.getWidth()) / 2;
        } else {
            size = calcY;
            offsetX = (mainController.getWidth() - mainController.getHeight()) / 2;
        } // end of diry code

        // Clear the canvas
        //gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getWidth());

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        map.render(gc, offsetX, offsetY, true, player);

        // RENDER PLAYER
        gc.setFill(Color.YELLOW);
        gc.fillRect(offsetX + (GameMap.MIN_SIZE_Y/2) * size - player.getSizeX()/2*size, GameMap.MIN_SIZE_Y/2 * size + offsetY - size, player.getSizeX()*size, player.getSizeY()*size);
        gc.drawImage(player.getAvatar(),GameMap.MIN_SIZE_X/2 * calc + offsetY - (calc* player.getSizeX()), GameMap.MIN_SIZE_Y/2 * calc + offsetX - (calc* player.getSizeY()), calc * player.getSizeX(), calc * player.getSizeY());
    }

    @Override
    public void onClose() {

    }

    @Override
    public EventHandler<KeyEvent> getEventHandler() {
        double speed = 6.5d;
        return (event -> {
            switch (event.getCode()) {
                case W:
                    printPlayerPos();
                    player.addPosY(speed);
                    break;
                case A:
                    printPlayerPos();
                    player.addPosX(speed);
                    break;
                case S:
                    printPlayerPos();
                    player.addPosY(-speed);
                    break;
                case D:
                    printPlayerPos();
                    player.addPosX(-speed);
                    break;


                case ESCAPE:
                    mainController.setState(State.MAIN_MENU);
                    break;
            }
        });
    }


    private void printPlayerPos() {
        System.out.println("-------------");
        System.out.println("X :" + player.getPosX());
        System.out.println("Y :" + player.getPosY());
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}