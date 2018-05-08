package main.java.HAC.character;

import main.java.model.character.Enemy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by henrytran1 on 03/05/2018.
 */
class EnemyTest {

    private Enemy enemy;

    @BeforeEach
    void setUp() {
        enemy = new Enemy("BODY_skeleton", 2,2,5,5);

    }

    @AfterEach
    void tearDown() {
        enemy = null;

    }

    @Test
    void calculateMove() {

    }

    @Test
    void setPosX() {
        enemy.setPosX(2);
        assertEquals(2, enemy.getPosX());
    }

    @Test
    void setPosY() {
        enemy.setPosY(3);
        assertEquals(3, enemy.getPosY());

    }

    @Test
    void addPosX() {
        enemy.addPos(4, 0);
        assertEquals(9, enemy.getPosX());
    }

    @Test
    void addPosY() {
        enemy.addPos(0, 5);
        assertEquals(10, enemy.getPosY());
    }

    @Test
    void getPosX() {
        enemy.setPosX(3);
        assertEquals(3, enemy.getPosX());
    }

    @Test
    void getPosY() {
        enemy.setPosY(3);
        assertEquals(3, enemy.getPosY());

    }

    @Test
    void getSizeX() {
        assertEquals(2, enemy.getSizeX());

    }

    @Test
    void getSizeY() {
        assertEquals(2, enemy.getSizeY());

    }

    @Test
    void render() {

    }

    @Test
    void getSpriteFileName() {

    }

}