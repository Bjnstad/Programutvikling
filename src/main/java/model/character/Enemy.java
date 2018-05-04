package main.java.model.character;

/**
 * In this class Enemy extends the qualities from character.
 * @author
 */
public class Enemy extends Character {

    private float speed = 1;

    /**
     * This method represents position, size and qualities to the enemy in the game.
     * @param spriteFileName is the name of the file that can be downloaded.
     * @param sizeX is the size of the enemy in width(x).
     * @param sizeY is the size of the enemy in height(y).
     * @param posX is the position to the enemy in width(x).
     * @param posY is the position to the enemy in heigth(y).
     */
    public Enemy(String spriteFileName, int sizeX, int sizeY, int posX, int posY) {
        super(spriteFileName, sizeX, sizeY);
        super.setPosX(posX);
        super.setPosY(posY);

    }

    /**
     * This method calculates the movement to the player.
     * @param player is the animation in the game.
     */
    public void calculateMove(Player player) {
        double angle = Math.atan2(player.getPosX() - getPosX(), player.getPosY() - getPosY());
        addPosY(speed * Math.cos(angle) / 100);
        addPosX(speed * Math.sin(angle) / 100);
    }



}