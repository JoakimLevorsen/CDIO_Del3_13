package matador;

import java.util.Optional;
import matador.GUI.*;

public class Game {
    public Dice dice;
    public Player[] players;
    private int turnCounter;

    public Game(int withPlayers, int diceMax) {

        players = new Player[withPlayers];
        for(int i = 0; i < withPlayers; i++) {
            players[i] = new Player();
        }
        this.dice = new Dice(diceMax);
        this.turnCounter = 0;
    }

    public void executeTurn() {}

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