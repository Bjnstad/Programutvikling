package FileHandler;

import assets.Object;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by henrytran1 on 06/02/2018.
 */
public class FileHandler {
    private final Image IMAGE_RUBY  = new Image("https://upload.wikimedia.org/wikipedia/commons/f/f1/Ruby_logo_64x64.png");
    private final Image IMAGE_APPLE  = new Image("http://findicons.com/files/icons/832/social_and_web/64/apple.png");
    private final Image IMAGE_VISTA  = new Image("http://antaki.ca/bloom/img/windows_64x64.png");
    private final Image IMAGE_TWITTER = new Image("http://files.softicons.com/download/social-media-icons/fresh-social-media-icons-by-creative-nerds/png/64x64/twitter-bird.png");

    private Image[] listOfImages = {IMAGE_RUBY, IMAGE_APPLE, IMAGE_VISTA, IMAGE_TWITTER};

    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp", "jpg"
    };

    // File representing the folder that you select using a FileChooser
    static final File dir = new File("assets");

    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };


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

    public ListCell<String> getAllAssets() {
        //File folder = new File("assets");
        //if(folder == null) return null;

         return (new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (dir.isDirectory()){
                        for (final File f : dir.listFiles(IMAGE_FILTER)){
                                if (name.equals(f.getName())) {
                                    Image image = new Image(f.toURI().toString());
                                    imageView.setImage(image);
                                   // imageView.setImage(image);
                                    setText(f.getName());
                                }
                        }
                        setGraphic(imageView);
                    }
                }
            }
        });

        //return list;

    }

    public ObservableList<String> getAllNames() {
        ObservableList<String> res = FXCollections.observableArrayList();

        if (dir.isDirectory()) for (final File f : dir.listFiles(IMAGE_FILTER)) {
            System.out.println(getFileExtension(f));

            if(getFileExtension(f).equals("png")) {
                System.out.println(f.getName());
                res.add(f.getName());
            }
        }

        return res;
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

}
