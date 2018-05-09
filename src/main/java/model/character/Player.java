package main.java.model.character;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.model.Camera;

import java.util.ArrayList;

/**
 * This class represents the player in the game.
 * Player extends the qualities from character.
 * @author
 */
public class Player extends Character {
    private ArrayList<Bullet> bullets = new ArrayList<>();

    /**
     * This method contains the player in the game.
     * It extends its characteristics from character, and it´s coordinates.
     */
    public Player() {
        super("player_animations_walking", 1, 1);
        setPosX(1);
        setPosY(1);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void shoot(double scale, double startX, double startY, double endX, double endY){
        Bullet bullet = new Bullet(scale, getPosX(), getPosY(), endX, endY);
        bullets.add(bullet);
    }


    // Render bullets
    public void renderOptional(Camera camera) {
        GraphicsContext gc = camera.getGraphicsContext();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b =  bullets.get(i);
            b.update();
            gc.setFill(Color.YELLOW);
            gc.fillRect(camera.scale(b.getX()), camera.scale(b.getY()), 10, 5);
        }

    }
}
