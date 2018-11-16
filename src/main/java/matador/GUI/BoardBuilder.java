package matador.GUI;

import matador.JSONKeys;

import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

import java.awt.*;

import org.json.*;

class BoardBuilder {
    static GUI_Field[] build(JSONObject content) throws JSONException {
        // Building a game board.
        try {
            GUI_Field[] board = new GUI_Field[24];
            JSONArray spaces = content.getJSONArray("spaces");
            // GUI_Street(TITLE, SUBTEXT, DESCRIPTION, RENT, BG_COLOR, FG_COLOR);
            for (int i = 0; i < 12; i++) {
                String name = spaces.getJSONObject(i).getString(JSONKeys.name);
                String cost = spaces.getJSONObject(i).getString(JSONKeys.costString);
                String message = spaces.getJSONObject(i).getString(JSONKeys.message);
                board[i] = new GUI_Street(name, "", message, cost, Color.white, Color.black);
            }
            return board;
        } catch (Exception e) {
            System.out.println("Read from JSON failed, check formatting");
            e.printStackTrace();
            throw new JSONException(e);
        }
    }
}