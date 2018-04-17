package HAC.filehandler;

import HAC.Camera;
import HAC.character.Enemy;
import HAC.character.Player;
import HAC.world.GameMap;
import javafx.animation.Timeline;

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
