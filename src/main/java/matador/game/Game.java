package matador.game;

import matador.GUI.*;
import matador.cards.ChanceCard;
import matador.spaces.*;

public class Game {
    public final Dice dice;
    public final Player[] players;
    public final SpaceManager sManager;
    public final CardManager cardManager;
    private int turnCounter;
    private UIManager uiManager;

    public Game(int withPlayers, int diceMax, UIManager uiManager) {
        players = new Player[withPlayers];
        for (int i = 0; i < withPlayers; i++) {
            // players[i] = new Player();
        }
        this.dice = new Dice(diceMax);
        this.turnCounter = 0;
        this.uiManager = uiManager;
        this.sManager = uiManager.getSpaceManager();
        this.cardManager = uiManager.cardManager;
    }

    public void executeTurn() {
        Player player = players[turnCounter];
        // Check if current space is jail and if player is in jail
        Space currentSpace = sManager.getSpace(player.getBoardPosition());
        if (currentSpace instanceof JailSpace) {
            JailSpace j = (JailSpace)currentSpace;
            if (j.isInJail(player)) {
                // TODO: Do thing if player in jail?
            }
        }

        int roll = dice.rollDice();
        player.moveForward(roll);
        int newPosition = player.getBoardPosition();
        Space newSpace = sManager.getSpace(newPosition);

        if (newSpace instanceof PropertySpace) {
            ((PropertySpace) newSpace).buy(player);

        } else if (newSpace instanceof GoToJailSpace) {
            try {
                player.setBoardPosition(sManager.getJailSpaceIndex());
                sManager.getJailSpace().jailPlayer(player);
            } catch (SpaceNotFoundException e) {
                System.out.println(e.getMessage());
            }

        } else if (newSpace instanceof ChanceSpace) {
            ChanceCard card = ((ChanceSpace) newSpace).draw();
            uiManager.displayMessage(card);
            card.process(this, player);
        }

        uiManager.updateUI(roll, player);
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
