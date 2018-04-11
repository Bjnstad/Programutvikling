package HAC.editor;

import HAC.world.GameObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class makes it possible to export a file.
 * @author henrytran
 */
public class ExportHac {
    private HacFile hacFile;
    private ArrayList<String> elements = new ArrayList<>();

    /**
     * This method adds a element to the game.
     * @param gameObject is the element that is being added to the game.
     * @author ceciliethoresen
     */
    public void addElement(GameObject gameObject){

        StringBuilder sb = new StringBuilder(); // creates empty builder
        sb.append("{" + System.getProperty("line.separator")); // adds a line separator
        sb.append("image_asset: " + gameObject.getAsset() +"," + System.getProperty("line.separator"));
        sb.append("GameObject_SizeX: " + gameObject.getSizeX() +"," + System.getProperty("line.separator"));
        sb.append("GameObject_SizeY: " + gameObject.getSizeY() +"," + System.getProperty("line.separator"));
        sb.append("Position_X: " + gameObject.getPosX()+ "," + System.getProperty("line.separator"));
        sb.append("Position_Y: " + gameObject.getPosY()+ "," + System.getProperty("line.separator"));
        sb.append("}," + System.getProperty("line.separator"));


        elements.add(sb.toString());
    }

    /**
     * Here we created a file that adds elements to the builder.
     * @author ceciliethoresen
     */
    public void createFile(){
        StringBuilder sb = new StringBuilder(); // creates empty builder.
        for(String content : elements){
            sb.append(content); // adds elements to the builder.
        }
        String content = sb.toString();
        File file = new File("/Users/henrytran1/Documents/Github/Programutvikling/newFile.txt");

        try(FileOutputStream outputStream = new FileOutputStream(file)){
            if(!file.exists()){
                file.createNewFile();
            }

            byte[] contentInBytes = content.getBytes();

            outputStream.write(contentInBytes);
            outputStream.flush();
            outputStream.close();
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
