package main.java.HAC;

import main.java.controller.GameController;
import main.java.model.filehandler.SpriteSheet;
import main.java.model.world.GameMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by henrytran1 on 03/05/2018.
 */
class HACTest {
    private GameController game;
    private GameMap map;


    @BeforeEach
    void setUp() {
        map = new GameMap(20, 20, new SpriteSheet("background", 64));
        //game = new GameController(map);


    }

    @AfterEach
    void tearDown() {
        game = null;

    }

    @Test
    void play() {


    }

    @Test
    void pause() {

    }

    @Test
    void move() {

    }

    @Test
    void isRunning() {
        game.pause();
        assertEquals(false, game.isRunning());
    }

    @Test
    void getCamera() {

        assertNotNull(game.getCamera());

    }

    @Test
    void getPlayer() {

    }

    @Test
    void getGameMap() {
       // assertEquals(map, game.getGameMap());

    }

    @Test
    void getEnemies() {
       // assertEquals(10, game.getEnemies().length);


    }

}