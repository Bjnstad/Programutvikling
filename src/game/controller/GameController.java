package game.controller;

import game.character.Enemy;
import game.character.Player;
import game.world.GameMap;
import game.world.GameObject;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameController implements Controller {

    private int currentLevel;
    private Player player;
    private Enemy[] enemies;
    private GameMap map;

    @FXML
    Canvas graphics;

    private MainController mainController;



    @Override
    public void initiate() {
        this.player = new Player(Color.YELLOW);

        map = new GameMap(100, 100);
        System.out.println(map.setGameObjects(new GameObject(Color.RED, 2, 2), 5, 5));
        map.setGameObjects(new GameObject(Color.BLUE, 5, 5), 12, 14);

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

            int calc;
            if(calcX < calcY) {
                calc = calcX;
            } else {
                calc = calcY;
            }




            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getWidth());
            for(int y = (int)player.getPosY(); y < GameMap.MIN_SIZE_Y + player.getPosY(); y++) {
                for (int x = (int)player.getPosY(); x < GameMap.MIN_SIZE_X + player.getPosX(); x++) {
                    GameObject gameObject = map.getObject(x, y);
                    if(gameObject == null) {
                        // No object

                        gc.setFill(map.getBackgroundColor());
                        gc.fillRect(x * calc, y * calc, calc, calc);
                    } else {
                        // Is object

                        gc.setFill(gameObject.getAsset());
                        gc.fillRect(x * calc, y * calc, calc, calc);
                    }
                }
            }

            // RENDER PLAYER
            gc.setFill(Color.YELLOW);
            gc.fillRect(GameMap.MIN_SIZE_X/2 * calc, GameMap.MIN_SIZE_Y/2 * calc, calc, calc);


        }
    }

    @Override
    public void onClose() {

    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
