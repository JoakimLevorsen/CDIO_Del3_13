package matador.game;

public class Player {
    public Account balance;
    private int boardPosition;
    private String name;

    public Player(int cash, String name) {
        this.name = name;
        boardPosition = 0;
        balance = new Account(cash);
    }
    public void moveForward(int spaces) {

        if (spaces > 0) {
            int newposition = boardPosition += spaces;
            if (newposition > 23) {
                newposition = newposition - 24;
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

