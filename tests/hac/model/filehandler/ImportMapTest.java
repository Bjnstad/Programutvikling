package hac.model.filehandler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by henrytran1 on 16/05/2018.
 */
class ImportMapTest {

    private ImportMap importMap;


    @BeforeEach
    void setUp() {
        importMap = new ImportMap();
    }

    @AfterEach
    void tearDown() {
        importMap = null;

    }

    @Test
    void splitString() {

        //Test will pass
        String testString ="!MapObject,6.0,4.0,?3,0";
        String[] expectedReturn= new String[5];
        expectedReturn[0] = "MapObject";
        expectedReturn[1] = "6.0";
        expectedReturn[2] = "4.0";
        expectedReturn[3] = "?3";
        expectedReturn[4] ="0";

        assertArrayEquals(expectedReturn, importMap.splitString(testString));

        //Test will fail
        String testFailString="!MapObject,6.0,4.0.?3,0";

        assertArrayEquals(expectedReturn, importMap.splitString(testString));
        assertNotEquals(Arrays.toString(expectedReturn), Arrays.toString(importMap.splitString(testFailString)));


    }

    @Test
    void parseFile() {

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