package hac.model.object;

import hac.controller.World;
import hac.model.object.defaults.MainPlayer;
import hac.model.object.sprite.Avatar;
import hac.model.object.sprite.Direction;
import hac.model.object.sprite.animation.MultiAnimation;
import hac.model.object.sprite.animation.SingleAnimation;

import java.sql.Timestamp;

public abstract class MoveableObject extends GameObject {

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());


    public MoveableObject(String filename, double posX, double posY, int frames) {
        super(new Avatar(filename, new SingleAnimation(frames, 0)),posX, posY);
    }


    /**
     * In this method we add the position to the character in width.
     * If the speed to the character is less then 0, then it walks left.
     * If not, it walks right.
     //* @param speed this is the speed to the character walking right or left.
     */
    public boolean addPos(double x, double y, World world) {
        Timestamp now = new Timestamp(System.currentTimeMillis() - 100);

        if(now.after(timestamp)) {
            timestamp = new Timestamp(System.currentTimeMillis());
            ((SingleAnimation)getAvatar().getAnimation()).increaseInterval();
        }



        if(getPosX() + x < 0 || getPosY() + y < 0 || getPosX() + x > world.getGameMap().getWidth() || getPosY() + y > world.getGameMap().getHeight()) return false;


        for(GameObject object : world.getGameObjects()) {
            if(object == this) continue;
            if(willCollide(object,getPosX() + x, getPosY() + y)  && object.isCollideable() && isCollideable() && !(object instanceof MainPlayer)) return false;
        }


        setPosX(getPosX() + x);
        setPosY(getPosY() + y);
        return true;
    }
}
