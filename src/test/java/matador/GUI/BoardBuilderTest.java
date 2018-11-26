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
        JSONArray[] spaces = {jsonDA.getJSONArray(JSONKeys.SPACES), jsonEN.getJSONArray(JSONKeys.SPACES)};
        for (JSONArray a : spaces) {
            int length = a.length();
            for (int i = 0; i < length; i++) {
                String colorString = a.getJSONObject(i).getString(JSONKeys.COLOR);
                System.out.println(colorString);
                BoardBuilder.getColor(colorString);
            }
        }
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
