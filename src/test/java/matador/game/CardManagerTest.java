package matador.game;

import matador.cards.ChanceCard;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class CardManagerTest {
    // Make json Objects for testing
    String EN = readFile("default/EN.json");
    String DA = readFile("default/DA.json");
    JSONObject jsonDA = new JSONObject(DA);
    JSONObject jsonEN = new JSONObject(EN);


    @Test
    public void constructorTest() {
        // For visual inspection of building deck with json
        CardManager dCards1 = new CardManager(jsonDA);
        for (int i = 0; i < dCards1.drawPile.size(); i++) {
            System.out.println("At pos " + i + ": " + dCards1.drawPile.get(i));

        }
        System.out.println("Construction DA complete.\n");

        // Building deck with EN.json
        CardManager eCards1 = new CardManager(jsonEN);
        System.out.println("Construction EN complete.");
    }

    @Test
    public void pickCardTest() {
        CardManager dCards2 = new CardManager(jsonDA);

        for (int i = 0; i < 20; i++) {
            dCards2.pickCard();
        }
        // Number should be all cards - 1 after all cards are picked up, GetOutOfJail kept by player
        assertEquals(19, dCards2.discardPile.size());

    }

    @Test
    public void turnPileTest() {
        CardManager dCards3 = new CardManager(jsonDA);

        for (int i = 0; i < 21; i++) {
            dCards3.pickCard();
        }
        // Number should be all cards - 2 after all cards are picked up, GetOutOfJail kept by player
        assertEquals(18, dCards3.drawPile.size());
    }

    @Test
    public void shuffleTest() {
        CardManager dCards4 = new CardManager(jsonDA);

        ChanceCard randomCard1 = dCards4.drawPile.get(0);
        ChanceCard randomCard2 = dCards4.drawPile.get(1);

        // We have chosen a fisher-yates shuffle that moves every card every time
        dCards4.shuffleCards();
        for (int i = 0; i < 1000; i++) {
            assert (!randomCard1.equals(dCards4.drawPile.get(0)));
            assert (!randomCard2.equals(dCards4.drawPile.get(1)));
        }
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
