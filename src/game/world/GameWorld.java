package game.world;

import game.GameState;
import game.State;
import game.character.Enemy;
import game.character.Player;
import game.world.GameMap;

/**
 * Created by henrytran1 on 06/02/2018.
 */
public class GameWorld extends GameState {
    private int currentLevel;
    private Player player;
    private Enemy[] enemies;
    private GameMap map;

    public GameWorld() {
        super(State.GAME);
    }


    @Override
    public void initiate() {

    }

    @Override
    public void render() {

    }

    @Override
    public void onClose() {

    }
}
