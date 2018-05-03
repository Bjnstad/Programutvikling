package main.java.HAC.character;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by henrytran1 on 03/05/2018.
 */
class PlayerTest {
    public Player player;


    @Test
    void willCollide() {

    }


    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @BeforeEach
    public void setUp() {
        player = new Player();

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
        player.addPosX(2.2);
        assertEquals(2.2, player.getPosX());

    }

    @Test
    void addPosY() {
        player.addPosY(5);
        assertEquals(5, player.getPosY());

    }

    @Test
    void getPosX() {
        player.setPosX(2);
        assertEquals(2,player.getPosX());

    }

    @Test
    void getPosY() {
        player.setPosY(2);
        assertEquals(2, player.getPosY());

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
        assertEquals("player_animations_walking", player.getSpriteFileName());

    }


}