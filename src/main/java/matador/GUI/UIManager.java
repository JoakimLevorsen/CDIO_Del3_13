package matador.GUI;

import matador.*;
import matador.cards.ChanceCard;
import matador.game.*;
import matador.spaces.PropertySpace;
import gui_fields.*;
import gui_main.GUI;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.*;

import org.json.*;
import org.apache.commons.io.IOUtils;

public class UIManager {
    private GUI gooey;
    private GUI_Player[] players;
    private Game game;
    private SpaceManager kirk;
    public final CardManager cardManager;
    private JSONObject jsonData;
    private GUI_Field[] board;
    private int numberOfPlayers, diceMax = 6, startBalance;

    public static void main(String[] args) {
        new UIManager();
    }

    public UIManager() {
        // Constructor

        // Let user choose language, and get corresponding json language object
        GUI langChooseGui = new GUI(new GUI_Field[0]);

        boolean langChoice = langChooseGui.getUserLeftButtonPressed("Please choose a language:", "English", "Dansk");

        langChooseGui.close();

        // Set language and start the wGui GUI for the main game.
        String fileContents = readFile(langChoice ? "default/EN.json" : "default/DA.json");
        System.out.println("Got file contents" + fileContents);
        try {
            jsonData = new JSONObject(fileContents);
            // Build game board
            board = BoardBuilder.build(jsonData);
            gooey = new GUI(board);
            kirk = new SpaceManager(jsonData);
            cardManager = kirk.cardManager;

            // Get number of guiPlayers between 2 and 4 (both incl.)
            numberOfPlayers = gooey.getUserInteger(jsonData.getString(JSONKeys.CHOOSE_PLAYER_NUM));
            if (numberOfPlayers > 4 || numberOfPlayers < 2) {
                gooey.showMessage(jsonData.getString(JSONKeys.INVALID_PLAYER_NUM));
                numberOfPlayers = gooey.getUserInteger(jsonData.getString(JSONKeys.CHOOSE_PLAYER_NUM));
            }

            startGame();
        } catch (Exception e) {
            gooey.showMessage("There was a problem with your resources. Try reinstalling the application.\n "
                    + "Der opstod et problem med dine ressourcer. Prøv at reinstallere programmet.");
            System.out.println("Read from JSON failed, check formatting");
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    private void startGame() {
        // Make game and keep playing it until won

        gooey.close();
        gooey = new GUI(board);

        game = new Game(numberOfPlayers, diceMax, this);
        addPlayers(game.players);

        gooey.showMessage(jsonData.getString(JSONKeys.ROLL_DICE));
        game.executeTurn();
    }

    public SpaceManager getSpaceManager() {
        return kirk;
    }

    public void updateUI(int dice, Player currentPlayer) {

        for (int i = 0; i < game.players.length ; i++ ){
            if (game.players[i].balance.getBalance() < 0)
            {
                Comparator<Player> getRichestPlayer = (a, b) -> a.balance.getBalance() < b.balance.getBalance() ? -1 : (a.balance.getBalance() == b.balance.getBalance() ? 0 : 1);
                ArrayList<Player> winnerList = ArrayFunctions.getBiggest(game.players, getRichestPlayer);
                int listSize = winnerList.size();
                if (winnerList.size() == 1) {
                    playerDidLose(winnerList.get(0));
                } else {
                    // TODO: Find den med mest egendom
                    int[] PropertyValue = new int[listSize];
                    for (Player winner : winnerList) {
                        // Sell all players property
                        for (PropertySpace pSpace: winner.getProperty()) {
                            winner.balance.increase(pSpace.value);
                        }
                    }
                    ArrayList<Player> propertyWinners = ArrayFunctions.getBiggest(winnerList, getRichestPlayer);
                    // Tjek på property winners om en eller flere har vundet
                    if (propertyWinners.size() == 1) {
                        playerDidLose(propertyWinners.get(0));
                    } else {
                        // TODO: Klar hvis flere spillere har samme antal egendom.
                    }
                }
                return;
            }
        }

        // TODO: Tjek bræt for ejere n such

        // TODO: Flyt brik
        PlayerMover.move(players[game.getTurnCounter()], gooey, currentPlayer.getPreviousPosition(), currentPlayer.getBoardPosition());

        // TODO: Display chancecards n such

        // TODO: Increment turn counter fra game
    }

    public void displayMessage(ChanceCard card) {
        gooey.displayChanceCard(card.title);
    }

    public void playerDidLose(Player player) {
        gooey.displayChanceCard(jsonData.getString(JSONKeys.WIN_MESSAGE));
        boolean quit = gooey.getUserLeftButtonPressed(jsonData.getString(JSONKeys.PLAY_AGAIN_MESSAGE),
                jsonData.getString(JSONKeys.QUIT), jsonData.getString(JSONKeys.PLAY_AGAIN_BUTTON));
        if (quit) {
            System.exit(0);
        } else {
            for (int i = 0; i < board.length; i++) {
                board[i].removeAllCars();
            }
            startGame();
        }
    }

    public void setOwned(int ownedSpace, Player player) {
        // Puts a house on it, updates "label" with playername, and sets a border in the
        // guiPlayers color.
        try {
            ((GUI_Street) board[ownedSpace]).setHouses(1);
            ((GUI_Ownable) board[ownedSpace]).setOwnableLabel(jsonData.getString(JSONKeys.OWNED_BY));
            ((GUI_Ownable) board[ownedSpace]).setOwnerName(player.getName());
            ((GUI_Ownable) board[ownedSpace]).setBorder(PlayerAdder.getColors(game.getTurnCounter()));
        } catch (ClassCastException ownableException) {
            System.out.println("Problem with casting space subclasses.");
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
        // Puts 2 - 4 guiPlayers on the board and stores an array of guiPlayers
        this.players = PlayerAdder.add(numberOfPlayers, gooey, players[0].balance.getBalance());
    }
}
