package main.java.HAC.character;


import main.java.model.object.character.Enemy;
import main.java.model.object.character.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by henrytran1 on 03/05/2018.
 */
class PlayerTest {
    private Player player;
    private Enemy enemy;




    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @BeforeEach
    public void setUp() {
        player = new Player("player_animations_walking", 1,1,1,1);

    }

    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @AfterEach
    public void tearDown() {
        player = null;

    }

    @Test
    void willCollide() {
        enemy = new Enemy("BODY_skeleton", 2,2,2,2);
        player.setPosX(2);
        player.setPosY(2);
        assertEquals(true, player.willCollide(enemy));
    }


    @Test
    void setPosX() {
        player.setPosX(4);
        assertEquals(4, player.getPosX());

    }

    @Test
    void setPosY() {
        player.setPosY(4);
        assertEquals(4, player.getPosY());

    }

    @Test
    void addPosX() {
        //player.addPos(2.2, 0);
        assertEquals(2.2, player.getPosX());

    }

    @Test
    void addPosY() {
        //player.addPos(0, 5);
        assertEquals(5, player.getPosY());

    }

    @Test
    void getPosX() {
        //player.addPos(2, 0);
        //assertEquals(2,player.getPosX());

    }

    @Test
    void getPosY() {
        //player.setPos(0, 2);
        //assertEquals(2, player.getPosY());

    }

    @Test
    void getSizeX() {
        assertEquals(1,player.getSizeX());
    }

    @Test
    void getSizeY() {
        assertEquals(1,player.getSizeY());

    }

    @Test
    void render() {

    }

    @Test
    void getSpriteFileName() {
        //assertEquals("player_animations_walking", player.getSpriteFileName());

    }


}