package matador.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    @Test
    public void rollDice() {
        matador.game.Dice john = new matador.game.Dice(6);
        int[] theCount;
        theCount = new int[6];

        for (int i = 0; i < 60000; i++)
            theCount[john.rollDice() - 1]++;
        for (int i = 0; i < 6 ; i++)
            assertEquals(true, theCount[i] > 9600 && theCount[i] < 10400);
    }
}
