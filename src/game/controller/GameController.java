package game.controller;

import game.State;
import game.character.Enemy;
import game.character.Player;
import game.world.GameMap;
import game.world.GameObject;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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



    @Override
    public void initiate() {
        this.player = new Player(Color.YELLOW);

        map = new GameMap(100, 100);
        System.out.println(map.setGameObject(new GameObject(Color.RED, 2, 2), 5, 5));
        map.setGameObject(new GameObject(Color.BLUE, 5, 5), 12, 14);

    }


    //TODO: NEED CLEAN/REWRITE
    @Override
    public void render() {
        if(graphics != null && map != null) {
            GraphicsContext gc = graphics.getGraphicsContext2D();

            graphics.setWidth(mainController.getWidth());
            graphics.setHeight(mainController.getHeight());

            int calcX = (int)(mainController.getWidth() / GameMap.MIN_SIZE_X);
            int calcY = (int)(mainController.getHeight() / GameMap.MIN_SIZE_Y);

            double restX = 0;
            double restY = 0;

            if(calcX < calcY) {
                calc = calcX;
                restX = (mainController.getHeight() - mainController.getWidth()) / 2;
            } else {
                calc = calcY;
                restY = (mainController.getWidth() - mainController.getHeight()) / 2;
            }

            // Clear the canvas
            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getWidth());

            map.render(gc, restX + player.getPosY(), restY + player.getPosX(), true);

            // RENDER PLAYER
            gc.setFill(Color.YELLOW);
            gc.fillRect(GameMap.MIN_SIZE_X/2 * calc + restY - calc/2, GameMap.MIN_SIZE_Y/2 * calc + restX - calc/2, calc, calc);



            for(int y = 0; y < map.getHeight(); y++) {
                for (int x = 0; x < map.getWidth(); x++) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x * calc + restY + player.getPosX(), y * calc + restX + player.getPosY(), calc, 1);
                    gc.fillRect(x * calc + restY + player.getPosX(), y * calc + restX + player.getPosY(), 1, calc);
                }
            }

        }
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
