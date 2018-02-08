package game;

import game.FileHandler.Asset;

public class Map {

    private Asset[][] board;

    public Map() {
        board = new Asset[10][10];
    }


    public Asset[][] getBoard() {
        return board;
    }

    public boolean addAsset(Asset asset, int posX, int posY) {
        if (asset == null || posX > getWidth() || posY > getHeight()) return false;


        return true;
    }

    /**
     * Get width of board
     * @return width of board, -1 if the board is not set
     */
    public int getWidth() {
        if(board == null && board.length > 0) return -1;
        return board[0].length;
    }

    /**
     * Get height of board
     * @return height of board, -1 if the board is not set
     */
    public int getHeight() {
        if(board == null) return -1;
        return board.length;
    }
}
