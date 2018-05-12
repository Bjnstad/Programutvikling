package main.java.model.render;

import main.java.model.object.character.Enemy;

import java.util.Random;

public class EnemyGeneration implements Runnable {

    private final Enemy[] enemies;
    private final int start;
    private final int end;

    public EnemyGeneration(Enemy[] enemies, int start, int end) {
        this.enemies = enemies;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        Random rand = new Random();
        for (int i = start; i < end; i++) {
            //enemies[i] = new Enemy("BODY_skeleton", 1,1,rand.nextInt(20),rand.nextInt(20));
            enemies[i] = new Enemy("BODY_skeleton", 1,1,6, 5);
            enemies[i].setSpeed(1 + rand.nextInt(4));
        }
    }
}
