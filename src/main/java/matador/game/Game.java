package matador.game;

import java.util.Optional;
import matador.GUI.*;
import matador.game.*;
import matador.spaces.*;


public class Game {
    public final Dice dice;
    public final Player[] players;
    public final SpaceManager sManager;
    private int turnCounter;
    private UIManager uiManager;

    public Game(int withPlayers, int diceMax, UIManager uiManager) {

        players = new Player[withPlayers];
        for(int i = 0; i < withPlayers; i++) {
            //players[i] = new Player();
        }
        this.dice = new Dice(diceMax);
        this.turnCounter = 0;
        this.uiManager = uiManager;
        this.sManager = uiManager.getSpaceManager();
    }

    public void executeTurn() {
        int roll = dice.rollDice();
        Player player = players[turnCounter];
        player.moveForward(roll);
        int newPosition = player.getBoardPosition();
        Space newSpace = sManager.getSpace(newPosition);

        if (newSpace instanceof PropertySpace) {
            ((PropertySpace) newSpace).buy(player);

        } else if (newSpace instanceof GoToJailSpace) {
            try
            {
                player.setBoardPosition(sManager.getJailSpaceIndex());
            } catch (SpaceNotFoundException e)
            {
                System.out.println(e.getMessage());
            }

        } else if (newSpace instanceof ChanceSpace) {
            ((ChanceSpace) newSpace).draw();

        }
        
        uiManager.updateUI(roll,player);
    }

    public void incrementTurnCounter() {
        turnCounter++;
        if (turnCounter == players.length) {
            turnCounter = 0;
        }
        System.out.println("Incrementing turn to" + turnCounter);
    }

    public int getTurnCounter() {
        return turnCounter;
    }
}
