package FileHandler;

import assets.Object;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by henrytran1 on 06/02/2018.
 */
public class FileHandler {

    private static final String PATH_FOR_ASSETS = "assets";

    public boolean saveAsset(Object object) {

        BufferedWriter writer = null;
        try {
            BufferedImage bi = ImageIO.read(object.getImage());

            File outputfile = new File(PATH_FOR_ASSETS + "/" + object.getName() + ".png");
            ImageIO.write(bi, "png", outputfile);



                writer = new BufferedWriter(new FileWriter(PATH_FOR_ASSETS + "/" + object.getName() + ".txt"));
                writer.write("width:" + object.getWidth());
                writer.write("\n");
                writer.write("height:" + object.getHeight());



        } catch (IOException e) {
            e.printStackTrace();
        } finally
        {
            try
            {
                if ( writer != null)
                    writer.close( );
            }
            catch ( IOException e)
            {
            }
        }



        return true;
    }

    public ObservableList<String> getAllAssets() {
        File folder = new File(PATH_FOR_ASSETS);
        if(folder == null) return null;

        ObservableList<String>  list = FXCollections.observableArrayList ();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                // TODO: Maybe handle dir
            } else {
                list.add(fileEntry.getName());
                System.out.println(fileEntry.getName());
            }
        }

        return list;

    }
}
