package matador.Game;

public class Dice {
    public int maxFaceValue;

    public Dice(int maxFaceValue) {
        this.maxFaceValue = maxFaceValue;
    }

    public int rollDice() {
        double d = Math.random() * maxFaceValue + 1;
        int faceValue = (int) d;
        return faceValue;
    }
}