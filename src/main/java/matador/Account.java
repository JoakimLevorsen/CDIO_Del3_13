package matador;

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
        if (balance < 0) {
            balance = 0;
        }
    }

    public int getBalance() {
        return balance;
    }
}