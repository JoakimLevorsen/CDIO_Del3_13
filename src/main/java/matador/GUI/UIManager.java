package matador.GUI;

import matador.*;
import gui_fields.*;
import gui_main.GUI;
import java.io.*;
import java.util.Optional;
import org.json.*;
import org.apache.commons.io.IOUtils;

public class UIManager {
    private GUI gooey;
    private GUI_Player[] players;
    private Game game;
    private SpaceManager kirk;
    private JSONObject jsonData;
    private GUI_Field[] board;
    private int numberOfPlayers, diceMax = 6, startBalance;

    public static void main(String[] args) {
        new UIManager();
    }

    public UIManager () {
        // Constructor

        // Let user choose language, and get corresponding json language object
        GUI langChooseGui = new GUI(new GUI_Field[0]);

        boolean langChoice = langChooseGui.getUserLeftButtonPressed("Please choose a language:",
                "English", "Dansk");

        langChooseGui.close();

        // Set language and start the wGui GUI for the main game.
        String fileContents = readFile(langChoice ? "EN.json" : "DA.json");
        System.out.println("Got file contents" + fileContents);
        try {
            jsonData = new JSONObject(fileContents);
            // Build game board
            board = BoardBuilder.build(jsonData);
            gooey = new GUI(board);
            kirk = new SpaceManager(jsonData);

            // Get number of players between 2 and 4 (both incl.)
            numberOfPlayers = gooey.getUserInteger(jsonData.getString(JSONKeys.choosePlayerNum));
            if (numberOfPlayers > 4 || numberOfPlayers < 2) {
                gooey.showMessage(jsonData.getString(JSONKeys.invalidPlayerNum));
                numberOfPlayers = gooey.getUserInteger(jsonData.getString(JSONKeys.choosePlayerNum));
            }

        } catch (Exception e) {
            gooey.showMessage("There was a problem with your resources. Try reinstalling the application.\n " +
                    "Der opstod et problem med dine ressourcer. Prøv at reinstallere programmet.");
            System.out.println("Read from JSON failed, check formatting");
            e.printStackTrace();
        }
        startGame();
    }

    public void nextTurn() {}

    public void displayDiceRoll(int roll) {}

    private void startGame() {
        // Make game and keep playing it until won

        gooey.close();
        gooey = new GUI(board);

        game = new Game(numberOfPlayers, diceMax);

        addPlayers(game.players);

        gooey.showMessage(jsonData.getString(JSONKeys.rollDice));
        game.executeTurn();
    }

    public void updateUI(int dice, Player currentPlayer) {}

    public void playerDidLose(Player player) {
        gooey.displayChanceCard(jsonData.getString(JSONKeys.winMessage));
        boolean quit = gooey.getUserLeftButtonPressed(jsonData.getString(JSONKeys.playAgainMessage),
                jsonData.getString(JSONKeys.quit),jsonData.getString(JSONKeys.playAgainButton));
        if (quit){
            System.exit(0);
        } else {
            for (int i = 0; i < board.length; i++) {
                board[i].removeAllCars();
            }
            startGame();
        }
    }

    public String readFile(String fileName) {
        // Helper method for reading json files
        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void addPlayers(Player[] players) {
        // Puts 2 - 4 players on the board and stores an array of players
        this.players = PlayerAdder.add(numberOfPlayers, gooey, players[0].balance.getBalance());
    }
}