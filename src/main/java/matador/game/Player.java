package matador.game;

public class Player {
    public Account balance;
    private int boardPosition;
    private String name;
    public final Game game;

    public Player(int cash, String name, Game game) {
        this.name = name;
        this.boardPosition = 0;
        this.balance = new Account(cash);
        this.game = game;

    }
    public void moveForward(int spaces) {

        if (spaces > 0) {
            int newposition = boardPosition += spaces;
            if (newposition > 23) {
                newposition = newposition - 24;
                balance.increase(game.sManager.getStartSpace().rewardValue);
            }
            boardPosition = newposition;
        }
    }

    public int getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(int position)
    {
        boardPosition = position;
    }

    public String getName(){
        return name;
    }
}

