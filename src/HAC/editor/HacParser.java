package HAC.editor;

import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class content
 *
 * @author ceciliethoresen
 */
public class HacParser {
    private ArrayList<HacFile> hacContent = new ArrayList<>();

    /**
     * This method adds a object.
     * @param image in the game.
     * @param sizeX The size of image in width.
     * @param sizeY The size of image in height.
     * @param posX The position of image in width.
     * @param posY The position og image in height.
     * @author ceciliethoresen
     */
    public void addObject(Image image, int sizeX, int sizeY, int posX, int posY){
        HacFile hf = new HacFile(image,sizeX,sizeY,posX,posY);

        hacContent.add(hf);

    }

    /**
     * This method helps us to parse a file.
     * ... kommer mer
     * ...
     * @param file .......
     * @author ceciliethoresen
     */
    public void parseFile(File file){

        String pattern = ":([^,]*),";

        Pattern r = Pattern.compile(pattern);  // Create a Pattern object
        try {

            BufferedReader b = new BufferedReader(new FileReader(file));
            String readLine = ":";
            System.out.println("Reading file using Buffered Reader");
            int count = -1;
            String[] strArr = new String[5];
            while ((readLine = b.readLine()) != null) {
                Matcher m = r.matcher(readLine);
                if (m.find() && count < 5) {
                    strArr[count++] = m.group(1);
                }else if(count == 5){
                    //hacContent.add(addObject(strArr[0], Integer.valueOf(strArr[1]), Integer.valueOf(strArr[2]),Integer.valueOf(strArr[3]), Integer.valueOf(strArr[4])));
                    //addObject(strArr[0], Integer.valueOf(strArr[1]), Integer.valueOf(strArr[2]),Integer.valueOf(strArr[3]), Integer.valueOf(strArr[4]));
                    System.out.println(Arrays.toString(strArr));

                    count = 0;
                }
                else {
                    count = 0;
                    System.out.println("NO MATCH");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
