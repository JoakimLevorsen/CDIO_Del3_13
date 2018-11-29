package matador.GUI;

import gui_fields.GUI_Player;
import gui_main.GUI;

public class PlayerMover {
    public static void move(GUI_Player currentPlayer, GUI wGui, int fromPosition, int newPosition) {
        for (int i = fromPosition; i < (newPosition); i++) {
            int j = i;
            if (j > 23) {
                j = i - 24; // If at position 24 (out of bounds) go 24 back
            }
            wGui.getFields()[j].setCar(currentPlayer, false);
            if ((j + 1) > 23) {
                j = i - 24;
            }
            wGui.getFields()[j + 1].setCar(currentPlayer, true);
            waitForIt();
        }
    }

    private static void waitForIt() {
        // Sleep timer, takes a 1 second pause
        try {
            Thread.sleep(444);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}