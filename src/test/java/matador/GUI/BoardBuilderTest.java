package matador.GUI;

import matador.JSONKeys;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class BoardBuilderTest {
    // Make JSON Objects for testing
    String EN = readFile("default/EN.json");
    String DA = readFile("default/DA.json");
    JSONObject jsonDA = new JSONObject(DA);
    JSONObject jsonEN = new JSONObject(EN);

    @Test
    public void getColorTest() {
        BoardBuilder bob = new BoardBuilder();
        JSONArray spacesDA = jsonDA.getJSONArray(JSONKeys.SPACES);
        JSONArray spacesEN = jsonEN.getJSONArray(JSONKeys.SPACES);
        bob.getColor(spacesDA);
        bob.getColor(spacesEN);

    }
    @Test
    public void buildTest() {
        BoardBuilder.build(jsonDA);
        BoardBuilder.build(jsonEN);

    }
    // Helper method for reading json files
    public String readFile(String fileName) {
        String result = "";
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
