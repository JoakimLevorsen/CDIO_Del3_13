package matador.game;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Test;
import java.io.*;
import static junit.framework.TestCase.assertEquals;

public class CardManagerTest {
    // JSON Objects for testing
    String EN = readFile("default/EN.json");
    String DA = readFile("default/DA.json");
    JSONObject jsonDA = new JSONObject(DA);
    JSONObject jsonEN = new JSONObject(EN);

    @Test
    public void constructorTest() {
        // For visual inspection: building deck with DA.json
        CardManager testCards = new CardManager(jsonDA);

        for (int i = 0; i < testCards.drawPile.size(); i++) {
            System.out.println("At pos " + i + ": " + testCards.drawPile.get(i));

        }
        System.out.println("Construction with language DA complete.\n");

        // Building card deck with EN.json
        CardManager testCardsEnglish = new CardManager(jsonEN);
        System.out.println("Construction with language EN complete.\n");

    }

    @Test
    public void drawTest() {
        CardManager testCards = new CardManager(jsonDA);
        int totalCards = testCards.drawPile.size();

        for (int i = 0; i < totalCards; i++) {
            testCards.draw();
        }
        // discardPile should be all cards - 1 after all cards are picked up,
        // GetOutOfJail kept by player
        assertEquals((totalCards - 1), testCards.discardPile.size());
        // drawPile should be 0
        assertEquals(0, testCards.drawPile.size());

        System.out.println("pickCard test as expected.\n");

    }

    @Test
    public void turnPileTest() {
        CardManager testCards = new CardManager(jsonDA);
        int totalCards = testCards.drawPile.size();

        for (int i = 0; i < (totalCards + 1); i++) {
            testCards.draw();

        }
        // Should be all cards - 2, GetOutOfJail kept by player, one card moved to
        // discardPile
        assertEquals((totalCards - 2), testCards.drawPile.size());
        assertEquals(testCards.discardPile.size(), (1));

        System.out.println("turnPile test as expected.\n");
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
