package main.java.model.filehandler;


import javafx.animation.Timeline;
import main.java.model.Camera;
import main.java.model.character.Enemy;
import main.java.model.character.Player;
import main.java.model.world.GameMap;

/**
 * Created by henrytran1 on 17/04/2018.
 */
public class Save {
    private GameMap gameMap;
    private Camera camera;
    private Enemy[] enemies = new Enemy[1];
    private Timeline timeline;
    private Player player;


    public Save(GameMap gameMap, Camera camera, Enemy[] enemies, Timeline timeline, Player player) {
        this.gameMap = gameMap;
        this.camera = camera;
        this.enemies = enemies;
        this.timeline = timeline;
        this.player = player;
    }





}
