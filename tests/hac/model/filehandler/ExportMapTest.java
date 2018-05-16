package hac.model.filehandler;

import hac.controller.World;
import hac.model.object.GameMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by henrytran1 on 16/05/2018.
 */
class ExportMapTest {

    private ExportMap exportMap;


    @BeforeEach
    void setUp() {
        World world = new World();

        exportMap = new ExportMap(world);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addElement() {

    }

    @Test
    void addMapSize() {

    }

    @Test
    void saveGame() {

    }

    @Test
    void saveObjects() {

    }

    @Test
    void appendAhac() {

    }

    @Test
    void saveGameState() {

    }

    @Test
    void handleSaveMapName() {

    }

    @Test
    void getElements() {

    }

    @Test
    void encodeImageToString() {

    }

    @Test
    void encodeStringToImage() {

    }

    @Test
    void createFile() {

    }

    @Test
    void saveSpriteInput() {

    }

    @Test
    void showAlert() {

    }

}