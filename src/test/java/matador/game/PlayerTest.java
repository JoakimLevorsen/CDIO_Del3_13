package matador.game;

import matador.GUI.UIManager;
import matador.Game.Game;
import matador.game.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void moveForward() {
        // Positiv test hehe
        UIManager uiManager = new UIManager();
        String[] name = {"Asger"};
        Game game = new Game(1,6,uiManager,name);
        game.players[0].moveForward(5);
        assertEquals(5,game.players[0].getBoardPosition());
        assertEquals(0,game.players[0].getPreviousPosition());

        // Negativ test hehe
        game.players[0].setBoardPosition(0);
        game.players[0].moveForward(-5);
        assertNotEquals(-5,game.players[0].getBoardPosition());


    }
}
