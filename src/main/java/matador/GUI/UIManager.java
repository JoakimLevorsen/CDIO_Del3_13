package matador.GUI;

import matador.*;
import matador.cards.ChanceCard;
import matador.game.*;
import matador.spaces.*;
import gui_fields.*;
import gui_main.GUI;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.*;

import org.json.*;
import org.apache.commons.io.IOUtils;

public class UIManager {
    private GUI gooey;
    private GUI_Player[] guiPlayers;
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

            // Get number of guiPlayers between 2 and 4 (both incl.)
            numberOfPlayers = gooey.getUserInteger(jsonData.getString(JSONKeys.CHOOSE_PLAYER_NUM));
            while (numberOfPlayers > 4 || numberOfPlayers < 2) {
                gooey.showMessage(jsonData.getString(JSONKeys.INVALID_PLAYER_NUM));
                numberOfPlayers = gooey.getUserInteger(jsonData.getString(JSONKeys.CHOOSE_PLAYER_NUM));
            }
        } catch (Exception e) {
            gooey.showMessage("There was a problem with your resources. Try reinstalling the application.\n "
                    + "Der opstod et problem med dine ressourcer. Prøv at reinstallere programmet.");
            System.out.println("Read from JSON failed, check formatting");
            e.printStackTrace();
            System.exit(0);
        }

        cardManager = kirk.cardManager;
        setStartBalance(numberOfPlayers);
        startGame();
    }

    private void startGame() {
        // Make game and keep playing it until won
        String[] playerNames = new String[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            playerNames[i] = gooey.getUserString(jsonData.getString(JSONKeys.YOUR_NAME_HERE) + (i + 1));
        }

        gooey.close();
        gooey = new GUI(board);

        game = new Game(numberOfPlayers, diceMax, this, playerNames);
        this.guiPlayers = PlayerAdder.add(numberOfPlayers, gooey, startBalance, playerNames);
        gooey.showMessage(jsonData.getString(JSONKeys.ROLL_DICE) + game.players[game.getTurnCounter()].getName());
        game.executeTurn();
    }

    public GUI_Player getPlayerFor(Player player) {
        for (int i = 0; i < guiPlayers.length; i++) {
            if (game.players[i] == player) {
                return guiPlayers[i];
            }
        }
        return null;
    }

    public SpaceManager getSpaceManager() {
        return kirk;
    }

    public void updateUI(Player currentPlayer) {
        for (int i = 0; i < game.players.length; i++) {
            if (game.players[i].balance.getBalance() < 0) {
                Comparator<Player> getRichestPlayer = (a, b) -> a.balance.getBalance() < b.balance.getBalance() ? -1
                        : (a.balance.getBalance() == b.balance.getBalance() ? 0 : 1);
                ArrayList<Player> winnerList = ArrayFunctions.getBiggest(game.players, getRichestPlayer);
                if (winnerList.size() == 1) {
                    gooey.displayChanceCard(jsonData.getString(JSONKeys.WIN_MESSAGE) + winnerList.get(0).getName());
                    endGame();
                } else {
                    for (Player winner : winnerList) {
                        // Sell all players property
                        for (PropertySpace pSpace : winner.getProperty()) {
                            winner.balance.increase(pSpace.value);
                        }
                    }
                    ArrayList<Player> propertyWinners = ArrayFunctions.getBiggest(winnerList, getRichestPlayer);
                    // Tjek på property winners om en eller flere har vundet
                    if (propertyWinners.size() == 1) {
                        gooey.displayChanceCard(jsonData.getString(JSONKeys.WIN_MESSAGE) + propertyWinners.get(0).getName());
                        endGame();
                    } else {
                        // Hvis flere spillere har samme balance efter salg af ejendomme, vindere de spillere med den højeste balance
                        String winners = "\n";
                        for (int index = 0; index < propertyWinners.size(); index++) {
                            winners = winners + propertyWinners.get(index).getName() + "\n";
                        }
                        gooey.showMessage(jsonData.getString(JSONKeys.SEVERAL_WINNERS) + winners);
                        endGame();
                    }
                }
            }
        }

        // Mark sold fields
        for (int i = 0; i < board.length; i++) {
            GUI_Field field = board[i];
            Space space = kirk.getSpace(i);
            if (space instanceof PropertySpace) {
                PropertySpace pSpace = (PropertySpace) space;
                if (pSpace.getOwner().isPresent()) {
                    field.setDescription(pSpace.message + jsonData.getString(JSONKeys.OWNED_BY) + pSpace.getOwner().get().getName());
                }
            }
        }

        // Update all users bankaccount
        for (int i = 0; i < game.players.length; i++) {
            guiPlayers[i].setBalance(game.players[i].balance.getBalance());
        }

        game.incrementTurnCounter();
        gooey.showMessage(jsonData.getString(JSONKeys.ROLL_DICE) + game.players[game.getTurnCounter()].getName());
        game.executeTurn();
    }

    public void displayMessage(ChanceCard card) {
        gooey.displayChanceCard(card.title);
    }

    public GUI getGUI() {
        return gooey;
    }

    public void endGame() {
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

    public void setStartBalance(int numberOfPlayers) {
        if (numberOfPlayers == 2) {
            startBalance = 20;
        } else if (numberOfPlayers == 3) {
            startBalance = 18;
        } else if (numberOfPlayers == 4) {
            startBalance = 16;
        } else {
            System.out.println("Invalid number of players.");
        }
    }

    public int getStartBalance() {
        return startBalance;
    }

    public JSONObject getJSONData() {
        return jsonData;
    }

    public void showDie (int dice) {
        gooey.setDie(dice);
    }
}
