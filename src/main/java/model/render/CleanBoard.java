package main.java.model.render;

import main.java.model.object.GameObject;

public class CleanBoard extends Thread {

    private byte[][] board;
    private GameObject[] gameObjects;
    private final int offsetX;
    private final int offsetY;

    public CleanBoard(byte[][] board, GameObject[] gameObjects, int offsetX, int offsetY) {
        this.board = board;
        this.gameObjects = gameObjects;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void run() {
        if(board == null || gameObjects == null)return;
        for (GameObject gameObject : gameObjects) {
            if(gameObject.getPosY() > offsetY && gameObject.getPosY() < offsetY + board.length) {
                if(gameObject.getPosX() > offsetX && gameObject.getPosX() < offsetX + board[0].length) {
                    board[(int)gameObject.getPosY()][(int)gameObject.getPosY()] = 1;
                }
            }
        }
    }
}
