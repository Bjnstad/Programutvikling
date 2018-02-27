package game.world;

import game.character.Character;
import game.character.Enemy;
import game.character.Player;
import game.utils.Offset;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameWorld {
    private int currentLevel;
    private Player player;
    private Enemy[] enemies;
    private GameMap map;


    public GameWorld(GameMap map) {
        if(map == null) throw new NullPointerException(); // Map cannot be null
        this.map = map;
        this.player = new Player();
        this.enemies = new Enemy[0];
        this.currentLevel = 0;
    }

    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Offset offset = calcOffset(canvas); // Calculate values for offset

        // Make screen black
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Render map with objects
        map.render(gc, offset, true, player);

        // Render characters
        for (Enemy enemy : enemies) enemy.render(gc, offset);
        player.render(gc, offset);
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

    public boolean movePlayer(double x, double y) {
        return move(player, x, y);
    }

    public boolean move(Character character, double x, double y) {
        if(!map.willCollide((int)(character.getPosX() + x), (int)(character.getPosY() + y))) {
            character.addPosX(x);
            character.addPosY(y);
            System.out.println("Adding");
            return true;
        }
        return false;
    }
}
