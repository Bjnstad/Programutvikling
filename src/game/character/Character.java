package game.character;

public class Character {
    private double posX = 0;
    private double posY = 0;

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }


    public void addPosX(double speed) {
        this.posX += speed;
    }

    public void addPosY(double speed) {
        this.posY += speed;
    }

    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }
}
