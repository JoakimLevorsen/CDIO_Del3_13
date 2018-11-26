package matador.GUI;

import gui_fields.*;
import matador.JSONKeys;
import java.awt.*;
import org.json.*;

class BoardBuilder {

    public static Color getColor(String JSONColor) throws JSONException {
        switch (JSONColor) {
            case "white": return Color.WHITE;
            case "black": return Color.BLACK;
            case "red": return Color.RED;
            case "darkGray": return Color.DARK_GRAY;
            case "lightGray": return Color.LIGHT_GRAY;
            case "cyan": return Color.CYAN;
            case "magenta": return Color.MAGENTA;
            case "orange": return Color.ORANGE;
            case "yellow": return Color.YELLOW;
            case "green": return Color.GREEN;
            case "blue": return Color.BLUE;
            case "gray": return Color.GRAY;
            case "pink": return Color.PINK;
            default: throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public static GUI_Field[] build(JSONObject jsonData) throws JSONException {
        // Building a game board.
        try {
            GUI_Field[] board = new GUI_Field[24];
            JSONArray spaces = jsonData.getJSONArray(JSONKeys.SPACES);
            // GUI_Street(TITLE, SUBTEXT, DESCRIPTION, RENT, BG_COLOR, FG_COLOR);

            for (int i = 0; i < spaces.length(); i++) {
                int spaceType = spaces.getJSONObject(i).getInt(JSONKeys.TYPE);
                String image = spaces.getJSONObject(i).getString(JSONKeys.IMAGE);
                String title = spaces.getJSONObject(i).getString(JSONKeys.NAME);
                String rent = spaces.getJSONObject(i).getString(JSONKeys.COST_STRING);
                String description = spaces.getJSONObject(i).getString(JSONKeys.MESSAGE);
                Color BG_COLOR = getColor(spaces.getJSONObject(i).getString(JSONKeys.COLOR));
                Color FG_COLOR = getColor(spaces.getJSONObject(i).getString(JSONKeys.TEXT_COLOR));

                switch (spaceType) {
                    case 0: board[i] = new GUI_Start(title, description, rent, BG_COLOR, FG_COLOR); break;
                    case 1: board[i] = new GUI_Refuge(image, title, description, rent, BG_COLOR, FG_COLOR); break;
                    case 2: board[i] = new GUI_Street(title, rent, description, rent, BG_COLOR, FG_COLOR); break;
                    case 3: board[i] = new GUI_Jail(image, title, description, rent, BG_COLOR, FG_COLOR); break;
                    case 4: board[i] = new GUI_Jail(image, title, description, rent, BG_COLOR, FG_COLOR); break;
                    case 5: board[i] = new GUI_Chance(title, description, rent, BG_COLOR, FG_COLOR); break;
                    default: throw new JSONException("Read from JSON failed, check formatting.");
                }
            }
            return board;
        } catch (Exception e) {
            System.out.println("Read from JSON failed, check formatting");
            e.printStackTrace();
            throw new JSONException(e);
        }
    }
}