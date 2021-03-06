package matador.game;

import java.util.Optional;
import matador.JSONKeys;
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
        if (currentSpace instanceof JailSpace && ((JailSpace) currentSpace).isInJail(player)) {
            JailSpace j = (JailSpace) currentSpace;
            if (player.getMyJailCard().isPresent()) {
                try {
                    j.releasePlayer(player);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                cardManager.discardCard(player.getMyJailCard().get());
            } else {
                player.balance.deduct(j.bail);
                try {
                    j.releasePlayer(player);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            int roll = dice.rollDice();
            uiManager.showDie(roll);
            player.moveForwardAlsoInUI(roll);
        }
        uiManager.updateUI(player);
    }

    public void handleLandingOn(Space newSpace, Player player) {
        if (newSpace instanceof PropertySpace) {
            Optional<Player> optOwner = ((PropertySpace) newSpace).getOwner();
            if (optOwner.isPresent() && optOwner.get() != player) {
                uiManager.displayMessage(uiManager.getJSONData().getString(JSONKeys.SPACE_OWNED));
                player.balance.deduct(((PropertySpace) newSpace).value);
                optOwner.get().balance.increase(((PropertySpace) newSpace).value);
            } else {
                // TODO: Du køber dit eget felt igen
                uiManager.displayMessage(uiManager.getJSONData().getString(JSONKeys.SPACE_UNOWNED));
                ((PropertySpace) newSpace).buy(player);
            }
        } else if (newSpace instanceof GoToJailSpace) {
            try {
                uiManager.displayMessage(uiManager.getJSONData().getString(JSONKeys.GO_TO_JAIL));
                player.setBoardPosition(sManager.getJailSpaceIndex());
                sManager.getJailSpace().jailPlayer(player);
            } catch (SpaceNotFoundException e) {
                System.out.println(e.getMessage());
                System.err.println(e.getMessage());
            }
        } else if (newSpace instanceof ChanceSpace) {
            ChanceCard card = cardManager.draw();
            uiManager.showChanceCard(card);
            card.process(this, player);
        }
    }

    public void movePlayerTo(int toPosition, int fromPosition, Player player) {
        PlayerMover.move(uiManager.getPlayerFor(player), uiManager.getGUI(), fromPosition, toPosition);
        handleLandingOn(this.sManager.getSpace(toPosition), player);
    }

    public void incrementTurnCounter() {
        turnCounter++;
        if (turnCounter == players.length) {
            turnCounter = 0;
        }
        System.out.println("Incrementing turn to " + turnCounter + ".");
    }

    public int getTurnCounter() {
        return turnCounter;
    }
}
