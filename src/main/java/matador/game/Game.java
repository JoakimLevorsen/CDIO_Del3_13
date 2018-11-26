package matador.game;

import java.util.Optional;
import matador.GUI.*;
import matador.game.*;
import matador.spaces.*;


public class Game {
    public Dice dice;
    public Player[] players;
    private int turnCounter;
    public final SpaceManager sManager;

    public Game(int withPlayers, int diceMax, SpaceManager sManager) {

        players = new Player[withPlayers];
        for(int i = 0; i < withPlayers; i++) {
            //players[i] = new Player();
        }
        this.dice = new Dice(diceMax);
        this.turnCounter = 0;
        this.sManager = sManager;
    }

    public void executeTurn() {
        int roll = dice.rollDice();
        Player player = players[turnCounter];
        player.moveForward(roll);
        int newPosition = player.getBoardPosition();
        Space newSpace = sManager.getSpace(newPosition);
        if (newSpace instanceof StartSpace) { player.balance.increase(((StartSpace) newSpace).getRewardValue());

        }

        incrementTurnCounter();
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
