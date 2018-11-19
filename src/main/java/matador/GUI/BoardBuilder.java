package matador.GUI;

import gui_fields.*;
import matador.JSONKeys;

import java.awt.*;

import org.json.*;

class BoardBuilder {
    static GUI_Field[] build(JSONObject jsonData) throws JSONException {
        // Building a game board.
        try {
            GUI_Field[] board = new GUI_Field[24];
            JSONArray spaces = jsonData.getJSONArray("spaces");
            // GUI_Street(TITLE, SUBTEXT, DESCRIPTION, RENT, BG_COLOR, FG_COLOR);

            board[0] = new GUI_Start(spaces.getJSONObject(0).getString(JSONKeys.NAME),
                spaces.getJSONObject(0).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(0).getString(JSONKeys.MESSAGE),
                Color.LIGHT_GRAY, Color.RED);

            board[1] = new GUI_Street(spaces.getJSONObject(1).getString(JSONKeys.NAME),
                spaces.getJSONObject(1).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(1).getString(JSONKeys.MESSAGE), "1",
                new Color(165, 42, 42), Color.BLACK);

            board[2] = new GUI_Street(spaces.getJSONObject(2).getString(JSONKeys.NAME),
                spaces.getJSONObject(2).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(2).getString(JSONKeys.MESSAGE), "1",
                new Color(165, 42, 42), Color.BLACK);

            board[3] = new GUI_Chance(spaces.getJSONObject(3).getString(JSONKeys.NAME),
                spaces.getJSONObject(3).getString(JSONKeys.NAME),
                spaces.getJSONObject(0).getString(JSONKeys.MESSAGE),
                new Color(204, 204, 204), Color.BLACK);

            board[4] = new GUI_Street(spaces.getJSONObject(4).getString(JSONKeys.NAME),
                spaces.getJSONObject(4).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(4).getString(JSONKeys.MESSAGE), "1",
                Color.CYAN, Color.BLACK);

            board[5] = new GUI_Street(spaces.getJSONObject(5).getString(JSONKeys.NAME),
                spaces.getJSONObject(5).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(5).getString(JSONKeys.MESSAGE), "1",
                Color.CYAN, Color.BLACK);

            board[6] = new GUI_Jail("default", spaces.getJSONObject(6).getString(JSONKeys.NAME),
                "Fængsel",spaces.getJSONObject(6).getString(JSONKeys.MESSAGE),
                new Color(125, 125, 125), Color.BLACK);

            board[7] = new GUI_Street(spaces.getJSONObject(7).getString(JSONKeys.NAME),
                spaces.getJSONObject(7).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(7).getString(JSONKeys.MESSAGE), "2",
                Color.MAGENTA, Color.BLACK);

            board[8] = new GUI_Street(spaces.getJSONObject(8).getString(JSONKeys.NAME),
                spaces.getJSONObject(8).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(8).getString(JSONKeys.MESSAGE), "2",
                Color.MAGENTA, Color.BLACK);

            board[9] = new GUI_Chance(spaces.getJSONObject(9).getString(JSONKeys.NAME),
                spaces.getJSONObject(4).getString(JSONKeys.NAME),
                spaces.getJSONObject(4).getString(JSONKeys.MESSAGE),
                new Color(204, 204, 204), Color.BLACK);

            board[10] = new GUI_Street(spaces.getJSONObject(10).getString(JSONKeys.NAME),
                spaces.getJSONObject(10).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(10).getString(JSONKeys.MESSAGE), "2",
                Color.ORANGE, Color.BLACK);

            board[11] = new GUI_Street(spaces.getJSONObject(11).getString(JSONKeys.NAME),
                spaces.getJSONObject(11).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(11).getString(JSONKeys.MESSAGE), "2",
                Color.ORANGE, Color.BLACK);

            board[12] = new GUI_Refuge("default", spaces.getJSONObject(12).getString(JSONKeys.NAME),
                spaces.getJSONObject(12).getString(JSONKeys.NAME),
                spaces.getJSONObject(12).getString(JSONKeys.MESSAGE),
                Color.WHITE, Color.BLACK);

            board[13] = new GUI_Street(spaces.getJSONObject(13).getString(JSONKeys.NAME),
                spaces.getJSONObject(13).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(13).getString(JSONKeys.MESSAGE), "3",
                Color.RED, Color.BLACK);

            board[14] = new GUI_Street(spaces.getJSONObject(14).getString(JSONKeys.NAME),
                spaces.getJSONObject(14).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(14).getString(JSONKeys.MESSAGE), "3",
                Color.RED, Color.BLACK);

            board[15] = new GUI_Chance(spaces.getJSONObject(15).getString(JSONKeys.NAME),
                spaces.getJSONObject(4).getString(JSONKeys.NAME),
                spaces.getJSONObject(4).getString(JSONKeys.MESSAGE),
                new Color(204, 204, 204), Color.BLACK);

            board[16] = new GUI_Street(spaces.getJSONObject(16).getString(JSONKeys.NAME),
                spaces.getJSONObject(16).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(16).getString(JSONKeys.MESSAGE),"3",
                Color.YELLOW, Color.black);

            board[17] = new GUI_Street(spaces.getJSONObject(17).getString(JSONKeys.NAME),
                spaces.getJSONObject(17).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(17).getString(JSONKeys.MESSAGE), "3",
                Color.YELLOW, Color.BLACK);

            board[18] = new GUI_Jail("default", spaces.getJSONObject(18).getString(JSONKeys.NAME),
                spaces.getJSONObject(18).getString(JSONKeys.NAME),
                spaces.getJSONObject(18).getString(JSONKeys.MESSAGE),
                new Color(125, 125, 125), Color.BLACK);

            board[19] = new GUI_Street(spaces.getJSONObject(19).getString(JSONKeys.NAME),
                spaces.getJSONObject(19).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(19).getString(JSONKeys.MESSAGE), "4",
                Color.GREEN, Color.BLACK);

            board[20] = new GUI_Street(spaces.getJSONObject(20).getString(JSONKeys.NAME),
                spaces.getJSONObject(20).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(20).getString(JSONKeys.MESSAGE), "4",
                Color.GREEN, Color.black);

            board[21] = new GUI_Chance(spaces.getJSONObject(21).getString(JSONKeys.NAME),
                spaces.getJSONObject(4).getString(JSONKeys.NAME),
                spaces.getJSONObject(4).getString(JSONKeys.MESSAGE),
                new Color(204, 204, 204), Color.BLACK);

            board[22] = new GUI_Street(spaces.getJSONObject(22).getString(JSONKeys.NAME),
                spaces.getJSONObject(22).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(22).getString(JSONKeys.MESSAGE),"5",
                Color.BLUE, Color.BLACK);

            board[23] = new GUI_Street(spaces.getJSONObject(23).getString(JSONKeys.NAME),
                spaces.getJSONObject(23).getString(JSONKeys.COST_STRING),
                spaces.getJSONObject(23).getString(JSONKeys.MESSAGE), "5",
                Color.BLUE, Color.BLACK);

            return board;
        } catch (Exception e) {
            System.out.println("Read from JSON failed, check formatting");
            e.printStackTrace();
            throw new JSONException(e);
        }
    }
}

