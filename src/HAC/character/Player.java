package HAC.character;

public class Player extends Character {
    /**
     * Player in the game
     */
    public Player() {
        super(new CharacterAvatar("player_animations_walking", 64), 1, 1);
    }

    public boolean willCollide(Enemy enemy) {
        double a = enemy.getPosX() - enemy.getSizeX();
        double b = enemy.getPosX() + enemy.getSizeX();
        double c = getPosX();
        boolean xval = b > a ? c > a && c < b : c > b && c < a;

        a = enemy.getPosY() - enemy.getSizeY();
        b = enemy.getPosY() + enemy.getSizeY();
        c = getPosY();
        boolean yval = b > a ? c > a && c < b : c > b && c < a;

        return xval && yval;

    }
}
