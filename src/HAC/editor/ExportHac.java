package HAC.editor;

import HAC.world.GameObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by henrytran1 on 06/03/2018.
 */
public class ExportHac {
    private HacFile hacFile;
    private ArrayList<String> elements = new ArrayList<>();


    public void addElement(GameObject gameObject){

        StringBuilder sb = new StringBuilder();
        sb.append("{" + System.getProperty("line.separator"));
        sb.append("image_asset: " + gameObject.getAsset() +"," + System.getProperty("line.separator"));
        sb.append("GameObject_SizeX: " + gameObject.getSizeX() +"," + System.getProperty("line.separator"));
        sb.append("GameObject_SizeY: " + gameObject.getSizeY() +"," + System.getProperty("line.separator"));
        sb.append("Position_X: " + gameObject.getPosX()+ "," + System.getProperty("line.separator"));
        sb.append("Position_Y: " + gameObject.getPosY()+ "," + System.getProperty("line.separator"));
        sb.append("}," + System.getProperty("line.separator"));


        elements.add(sb.toString());
    }

    public void createFile(){
        StringBuilder sb = new StringBuilder();
        for(String content : elements){
            sb.append(content);
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
