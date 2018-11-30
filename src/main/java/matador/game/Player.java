package matador.game;

import java.util.ArrayList;

import matador.spaces.PropertySpace;
import java.util.*;
import matador.cards.*;

public class Player {
    public final Account balance;
    private int boardPosition;
    private int previousPosition;
    private String name;
    public final Game game;
    private ArrayList<PropertySpace> property;
    private Optional<GetOutOfJailCard> myJailCard;

    public Player(int cash, String name, Game game) {
        this.name = name;
        this.boardPosition = 0;
        this.previousPosition = 0;
        this.balance = new Account(cash);
        this.game = game;
        this.property = new ArrayList<PropertySpace>();
        this.myJailCard = Optional.empty();
    }

    public void buyProperty(PropertySpace item) {
        property.add(item);
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

    public void moveForwardAlsoInUI(int spaces) {
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
        game.movePlayerTo(boardPosition, previousPosition, this);
    }

    public int getPreviousPosition() {
        return previousPosition;
    }

    public int getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(int position) {
        previousPosition = boardPosition;
        boardPosition = position;
        game.movePlayerTo(boardPosition, previousPosition, this);
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

    public ArrayList<PropertySpace> getProperty() {
        return property;
    }
}
