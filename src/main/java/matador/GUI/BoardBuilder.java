package matador.GUI;

import gui_fields.*;
import matador.JSONKeys;
import java.awt.*;
import org.json.*;

class BoardBuilder {

    public static Color getColor(String JSONColor) throws JSONException {
        switch (JSONColor) {
        case "white":
            return Color.WHITE;
        case "black":
            return Color.BLACK;
        case "red":
            return Color.RED;
        case "darkGray":
            return Color.DARK_GRAY;
        case "lightGray":
            return Color.LIGHT_GRAY;
        case "cyan":
            return Color.CYAN;
        case "magenta":
            return Color.MAGENTA;
        case "orange":
            return Color.ORANGE;
        case "yellow":
            return Color.YELLOW;
        case "green":
            return Color.GREEN;
        case "blue":
            return Color.BLUE;
        case "gray":
            return Color.GRAY;
        case "pink":
            return Color.PINK;
        default:
            throw new JSONException("Read from JSON failed, check formatting. Color not found: " + JSONColor);
        }
    }

    public static GUI_Field[] build(JSONObject JSONData) throws JSONException {
        // Building a game board.
        try {
            GUI_Field[] board = new GUI_Field[24];
            JSONArray spaces = JSONData.getJSONArray(JSONKeys.SPACES);
            // GUI_Street(TITLE, SUBTEXT, DESCRIPTION, RENT, BG_COLOR, FG_COLOR);

            for (int i = 0; i < spaces.length(); i++) {
                JSONObject space = spaces.getJSONObject(i);
                int spaceType = space.getInt(JSONKeys.TYPE);
                String image = space.has(JSONKeys.IMAGE) ? space.getString(JSONKeys.IMAGE) : "";
                String title = space.getString(JSONKeys.NAME);
                String rent = space.has(JSONKeys.VALUE_STRING) ? space.getString(JSONKeys.VALUE_STRING) : "";
                String description = space.getString(JSONKeys.MESSAGE);
                Color BG_COLOR = getColor(space.getString(JSONKeys.COLOR));
                Color FG_COLOR = getColor(space.getString(JSONKeys.TEXT_COLOR));

                switch (spaceType) {
                case 0:
                    board[i] = new GUI_Start(rent, title, description, BG_COLOR, FG_COLOR);
                    break;
                case 1:
                    board[i] = new GUI_Refuge(image, description, title, rent, BG_COLOR, FG_COLOR);
                    break;
                case 2:
                    board[i] = new GUI_Street(title, rent, description, rent, BG_COLOR, FG_COLOR);
                    break;
                case 3:
                    board[i] = new GUI_Jail(image, "", title, description, BG_COLOR, FG_COLOR);
                    break;
                case 4:
                    board[i] = new GUI_Jail(image, "", title, description, BG_COLOR, FG_COLOR);
                    break;
                case 5:
                    board[i] = new GUI_Chance("?", title, description, BG_COLOR, FG_COLOR);
                    break;
                default:
                    throw new JSONException("Read from JSON failed, check formatting.");
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