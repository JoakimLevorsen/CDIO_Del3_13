package matador.game;

public class Account {
    private int balance;

    public Account(int withBalance) {
        balance = withBalance;
    }

    public void increase(int by) {
        balance += Math.abs(by);
    }

    public void deduct(int by) {
        balance -= Math.abs(by);
    }

    public int getBalance() {
        return balance;
    }
}