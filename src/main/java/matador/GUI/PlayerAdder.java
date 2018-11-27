package matador.GUI;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;

class PlayerAdder {
    static Color[] colors = { Color.yellow, Color.RED, Color.blue, Color.green };;

    static GUI_Player[] add(int numOfPlayers, GUI wGui, int defaultBalance) {
        // Make 3 arrays to build players from, then makes 'numOfPlayers' players with
        // unique color. Max 4.
        GUI_Player[] players = new GUI_Player[numOfPlayers];
        GUI_Car[] cars = new GUI_Car[numOfPlayers];

        for (int i = 0; i < players.length; i++) {
            cars[i] = new GUI_Car(colors[i], Color.black, GUI_Car.Type.UFO, GUI_Car.Pattern.FILL);
            String playerNo = "Player " + (i + 1);
            players[i] = new GUI_Player(playerNo, defaultBalance, cars[i]);
            wGui.addPlayer(players[i]);
            wGui.getFields()[0].setCar(players[i], true);
        }
        return players;
    }

    static Color getColors(int x) {
        return colors[x];

    }
}
