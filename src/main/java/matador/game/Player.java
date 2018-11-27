package matador.game;

import java.util.*;
import matador.cards.*;

public class Player {
    public final Account balance;
    private int boardPosition;
    private int previousPosition;
    private String name;
    public final Game game;
    private Optional<GetOutOfJailCard> myJailCard;

    public Player(int cash, String name, Game game) {
        this.name = name;
        this.boardPosition = 0;
        this.previousPosition = 0;
        this.balance = new Account(cash);
        this.game = game;
    }

    public void moveForward(int spaces) {
        if (spaces > 0) {
            previousPosition = boardPosition;
            int newposition = boardPosition += spaces;
            if (newposition > 23) {
                newposition = newposition - 24;
                try {
                    balance.increase(game.sManager.getStartSpace().rewardValue);
                } catch (SpaceNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            boardPosition = newposition;
        }
    }

    public int getPreviousPosition() {
        return previousPosition;
    }

    public int getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(int position) {
        boardPosition = position;
    }

    public Optional<GetOutOfJailCard> getMyJailCard() {
        return myJailCard;
    }

    public void setMyJailCard(GetOutOfJailCard jailCard) {
        myJailCard = Optional.of(jailCard);
    }

    public String getName() {
        return name;
    }
}
