package main.java.model.filehandler;

import main.java.model.object.GameObject;
import main.java.model.object.sprite.animation.MultiAnimation;
import main.java.model.object.sprite.animation.SingleAnimation;
import main.java.model.object.sprite.animation.StaticAnimation;
import main.java.model.world.World;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @see FileHandler
 */
public class ExportGame extends FileHandler {
    private ArrayList<String> mapList = new ArrayList<>();
    //private ArrayList<String> gameObject = new ArrayList<>();

    private World world;
    private StringBuilder sb = new StringBuilder();


    /**
     * Exports the game.
     * Contains gameMap, camera, enemies and player.
     * @param world
     */
    public ExportGame(World world) {
        this.world = world;

        saveGame(world);

        createFile(new File("assets/maps/newMap.txt"), sb.toString());

    }

    /**
     *
     * @param world
     */
    public void saveGame(World world) {
        sb.append("@");
        sb.append(world.getGameMap().getWidth());
        sb.append(",");
        sb.append(world.getGameMap().getHeight());
        sb.append(",");
        sb.append(world.getGameMap().getBackgroundFileName());
        sb.append(",");
        sb.append(world.getCurrentLevel());
        sb.append(",");
        sb.append(world.isGodmode());
        sb.append(",");
        sb.append(world.getCamera().getTranslateX());
        sb.append(",");
        sb.append(world.getCamera().getTranslateY());
        sb.append("@");


        for (GameObject object : world.getGameObjects()) {
            sb.append("§");
            String type = object.getClass().getSimpleName();
            sb.append(type);
            sb.append(",");
            sb.append(object.getPosX());
            sb.append(",");
            sb.append(object.getPosY());
            sb.append(",");
            sb.append(object.getSizeX());
            sb.append(",");
            sb.append(object.getSizeY());
            sb.append(",");
            if(object.getAvatar().getAnimation() instanceof MultiAnimation) {
                MultiAnimation animation = (MultiAnimation)object.getAvatar().getAnimation();
                sb.append("#");
                sb.append(",");
                sb.append(object.getAvatar().getFilename());
                sb.append(",");
                sb.append(animation.getDirection());
                sb.append(",");
                sb.append(animation.getFrames());
                sb.append(",");
                sb.append(animation.getX());
                sb.append(",");
                sb.append(animation.getY());

                // HENT UT DATA
            }

            if(object.getAvatar().getAnimation() instanceof SingleAnimation) {
                SingleAnimation animation = (SingleAnimation)object.getAvatar().getAnimation();
                sb.append("/");
                sb.append(animation.getFrames());
                sb.append(",");
                sb.append(animation.getY());
                // HENT UT DATA
            }

            if(object.getAvatar().getAnimation() instanceof StaticAnimation) {
                StaticAnimation animation = (StaticAnimation)object.getAvatar().getAnimation();
                sb.append("$");
                sb.append(animation.getX());
                sb.append(",");
                sb.append(animation.getY());

                // HENT UT DATA
            }
        }
    }
}
