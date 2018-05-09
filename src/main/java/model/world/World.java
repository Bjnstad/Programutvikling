package main.java.model.world;

import main.java.model.character.Enemy;
import main.java.model.character.Player;

/**
 * Created by henrytran1 on 09/05/2018.
 */
public class World {
    private GameMap gameMap;
    private Player player;
    private Enemy enemies[];

    public World(GameMap gameMap, Player player, Enemy[] enemies) {
        this.gameMap = gameMap;
        this.player = player;
        this.enemies = enemies;
    }
}
