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


            board[0] = new GUI_Start(spaces.getJSONObject(0).getString(JSONKeys.NAME), "+1 M","Du får 1M når du passerer start.", Color.LIGHT_GRAY, Color.RED);
            board[1] = new GUI_Street(spaces.getJSONObject(1).getString(JSONKeys.NAME), "Pris: 1 M","En god burger til en god pris.", "1", new Color(165, 42, 42), Color.BLACK);
            board[2] = new GUI_Street(spaces.getJSONObject(2).getString(JSONKeys.NAME), "Pris: 1 M","Ægte italiensk pizza for næsten ingen penge.", "1", new Color(165, 42, 42), Color.BLACK);
            board[3] = new GUI_Chance(spaces.getJSONObject(3).getString(JSONKeys.NAME), "Prøv lykken", "Tag et chancekort.", new Color(204, 204, 204), Color.BLACK);
            board[4] = new GUI_Street(spaces.getJSONObject(4).getString(JSONKeys.NAME), "Pris: 1 M","Billigere slik fås ikke i byen.", "1", Color.CYAN, Color.BLACK);
            board[5] = new GUI_Street(spaces.getJSONObject(5).getString(JSONKeys.NAME), "Pris: 1 M","Gammeldaws is med det hele.", "1", Color.CYAN, Color.BLACK);
            board[6] = new GUI_Jail("default", spaces.getJSONObject(6).getString(JSONKeys.NAME), "Fængsel","På besøg i fængslet", new Color(125, 125, 125), Color.BLACK);
            board[7] = new GUI_Street(spaces.getJSONObject(7).getString(JSONKeys.NAME), "Pris: 2 M","Hold børnefødselsdag i museet og få kloge børn!", "2", Color.MAGENTA, Color.BLACK);
            board[8] = new GUI_Street(spaces.getJSONObject(8).getString(JSONKeys.NAME), "Pris: 2 M","Betal dine gebyrer, når du alligevel er forbi!", "2", Color.MAGENTA, Color.BLACK);
            board[9] = new GUI_Chance(spaces.getJSONObject(9).getString(JSONKeys.NAME), "Prøv lykken","Tag et chancekort.", new Color(204, 204, 204), Color.BLACK);
            board[10] = new GUI_Street(spaces.getJSONObject(10).getString(JSONKeys.NAME), "Pris: 2 M","Skateparken er gratis, men sodavandet koster.", "2", Color.ORANGE, Color.BLACK);
            board[11] = new GUI_Street(spaces.getJSONObject(11).getString(JSONKeys.NAME), "Pris: 2 M","Tag dig en svømmer for 2 M", "2", Color.ORANGE, Color.BLACK);
            board[12] = new GUI_Refuge("default", spaces.getJSONObject(12).getString(JSONKeys.NAME),"Pause","Tag dig en Red Bull og læs om Swing på Stack Overflow!", Color.WHITE, Color.BLACK);
            board[13] = new GUI_Street(spaces.getJSONObject(13).getString(JSONKeys.NAME), "Pris: 3 M","Her kan man spille de sidste spil, som eks DTU-matador!", "3", Color.RED, Color.BLACK);
            board[14] = new GUI_Street(spaces.getJSONObject(14).getString(JSONKeys.NAME), "Pris: 3 M","Biografen med 43 sale. Der er penge i de popcorn!", "3", Color.RED, Color.BLACK);
            board[15] = new GUI_Chance(spaces.getJSONObject(15).getString(JSONKeys.NAME), "Prøv lykken","Tag et chancekort.", new Color(204, 204, 204), Color.BLACK);
            board[16] = new GUI_Street(spaces.getJSONObject(16).getString(JSONKeys.NAME), "Pris: 3 M","Flot legetøjsbutik med masser af aktiviteter for de mindste.","3", Color.YELLOW, Color.black);
            board[17] = new GUI_Street(spaces.getJSONObject(17).getString(JSONKeys.NAME), "Pris: 3 M","Kæledyr i alle prisklasser.", "3", Color.YELLOW, Color.BLACK);
            board[18] = new GUI_Jail("default", spaces.getJSONObject(18).getString(JSONKeys.NAME), "Gå i fængsel","Du fængsles.\nFlyt til feltet \"fængsel\".", new Color(125, 125, 125), Color.BLACK);
            board[19] = new GUI_Street(spaces.getJSONObject(19).getString(JSONKeys.NAME), "Pris: 4 M","Bowling går aldri helt af mode.", "4", Color.GREEN, Color.BLACK);
            board[20] = new GUI_Street(spaces.getJSONObject(20).getString(JSONKeys.NAME), "Pris: 4 M","Zoo er en af de helt store turistattraktioner i byen.", "4", Color.GREEN, Color.black);
            board[21] = new GUI_Chance(spaces.getJSONObject(21).getString(JSONKeys.NAME), "Prøv lykken","Tag et chancekort.", new Color(204, 204, 204), Color.BLACK);
            board[22] = new GUI_Street(spaces.getJSONObject(22).getString(JSONKeys.NAME), "Pris: 5 M","Der er ikke sparet på noget i det nye vandland midt i København!","5", Color.BLUE, Color.BLACK);
            board[23] = new GUI_Street(spaces.getJSONObject(23).getString(JSONKeys.NAME), "Pris: 5 M","Byens fineste gade. Frisk luft, hav og økologisk kaffe.", "5", Color.BLUE, Color.BLACK);
            return board;
        } catch (Exception e) {
            System.out.println("Read from JSON failed, check formatting");
            e.printStackTrace();
            throw new JSONException(e);
        }
    }
}

