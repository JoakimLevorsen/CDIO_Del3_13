package matador;

public class Player {
    public Account balance;
    private int boardPosition;
    private String name;

    public Player(int cash, String name) {
        this.name = name;
        boardPosition = 0;
        balance = new Account(cash);
    }

    public int getBoardPosition() {
        return boardPosition;
    }

    public String getName(){
        return name;
    }
}

