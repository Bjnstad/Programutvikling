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

    int calc = 0;

    private Image tempImage = new Image("https://i.pinimg.com/originals/6f/6e/c3/6f6ec310eedfbcb45f300d24d0ea0cda.png");
    @Override
    public void initiate() {
        this.player = new Player(new Image("https://i.pinimg.com/originals/6f/6e/c3/6f6ec310eedfbcb45f300d24d0ea0cda.png"), 4, 4);
        map = new GameMap(100, 100);

        // Adding items to map
        System.out.println(map.setGameObject(new GameObject(tempImage, 2, 2), 7, 5));
        map.setGameObject(new GameObject(tempImage, 5, 5), 12, 14);
    }


    //TODO: NEED CLEAN/REWRITE
    @Override
    public void render() {
        if(map == null) return; // No map set.

        GraphicsContext gc = graphics.getGraphicsContext2D();

        graphics.setWidth(mainController.getWidth()); // Set width of canvas
        graphics.setHeight(mainController.getHeight()); // Set width of canvas

        int calcX = (int)(mainController.getWidth() / GameMap.MIN_SIZE_X);
        int calcY = (int)(mainController.getHeight() / GameMap.MIN_SIZE_Y);

        double offsetX = 0;
        double offsetY = 0;

        if(calcX < calcY) {
            calc = calcX;
            offsetX = (mainController.getHeight() - mainController.getWidth()) / 2;
        } else {
            calc = calcY;
            offsetY = (mainController.getWidth() - mainController.getHeight()) / 2;
        }

        // Clear the canvas
        //gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getWidth());

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        map.render(gc, offsetX, offsetY, true, player);

        // RENDER PLAYER
        gc.setFill(Color.YELLOW);
        gc.fillRect(GameMap.MIN_SIZE_X/2 * calc + offsetY - calc/2, GameMap.MIN_SIZE_Y/2 * calc + offsetX - calc/2, player.getSizeX()*calc, player.getSizeY()*calc);
        gc.drawImage(player.getAvatar(),GameMap.MIN_SIZE_X/2 * calc + offsetY - (calc)/2, GameMap.MIN_SIZE_Y/2 * calc + offsetX - (calc)/2, calc * player.getSizeX(), calc * player.getSizeY());
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
