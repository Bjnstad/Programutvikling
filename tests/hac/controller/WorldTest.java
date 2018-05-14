package hac.controller;

import hac.model.object.character.MainPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// TODO: Fill out
class WorldTest {

    private World world = new World();

    @Test
    void getMainPlayer() {
        // Not added player yet should be null
        assertNull(world.getMainPlayer());

        // Add player and trying to fetch it
        MainPlayer mainPlayer = new MainPlayer("default_player", 0,0);
        assertTrue(world.addGameObject(mainPlayer));
        assertEquals(mainPlayer, world.getMainPlayer());

        // Should be the first player even if we add a new one.
        MainPlayer mainPlayer2 = new MainPlayer("default_player", 0,0);
        assertFalse(world.addGameObject(mainPlayer2));
        assertEquals(mainPlayer, world.getMainPlayer());

        // And should not be able to remove
        assertFalse(world.removeObject(mainPlayer));
        assertEquals(mainPlayer, world.getMainPlayer());
    }

    @Test
    void getPlayers() {
    }

    @Test
    void getEnemies() {
    }

    @Test
    void getMapObjects() {
    }

    @Test
    void getBullets() {
    }

    @Test
    void getObjects() {
    }

    @Test
    void removeObject() {
    }
}