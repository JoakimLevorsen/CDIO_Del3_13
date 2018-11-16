package matador;

public class Player {
    public Account balance;
    private int boardPosition;

    public Player(int cash) {
        boardPosition = 0;
        balance = new Account(cash);
    }

    public int getBoardPosition() {
        return boardPosition;
    }
}