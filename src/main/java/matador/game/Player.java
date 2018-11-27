package matador.game;

import java.util.ArrayList;

import matador.spaces.PropertySpace;

public class Player {
    public final Account balance;
    private int boardPosition;
    private int previousPosition;
    private String name;
    public final Game game;
    private ArrayList<PropertySpace> property;

    public Player(int cash, String name, Game game) {
        this.name = name;
        this.boardPosition = 0;
        this.previousPosition = 0;
        this.balance = new Account(cash);
        this.game = game;
        this.property = new ArrayList<PropertySpace>();
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

    public int getBoardPosition() {
        return boardPosition;
    }

    public int getPreviousPosition() {
        return previousPosition;
    }

    public void setBoardPosition(int position) {
        boardPosition = position;
    }

    public String getName() {
        return name;
    }
}
