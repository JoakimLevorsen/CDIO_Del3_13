package matador.game;

import matador.GUI.*;
import matador.cards.*;
import matador.spaces.*;

public class Game {
    public final Dice dice;
    public final Player[] players;
    public final SpaceManager sManager;
    public final CardManager cardManager;
    public final UIManager uiManager;
    private int turnCounter;

    public Game(int withPlayers, int diceMax, UIManager uiManager, String[] playerNames) {
        players = new Player[withPlayers];
        for (int i = 0; i < withPlayers; i++) {
            players[i] = new Player(uiManager.getStartBalance(), playerNames[i], this);
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
                if(player.getMyJailCard().isPresent()) {
                    try {
                        j.releasePlayer(player);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    cardManager.discardCard(player.getMyJailCard().get());
                    return;
                }
                player.balance.deduct(1);
                try {
                    j.releasePlayer(player);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return;
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
            ChanceCard card = cardManager.draw();
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
