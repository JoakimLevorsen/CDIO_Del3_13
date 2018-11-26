package matador.cards;

import matador.JSONKeys;
import matador.game.Game;
import matador.game.Player;
import org.json.JSONException;
import org.json.JSONObject;

public class MoveSpacesCard extends ChanceCard {
    private String title;
    private int spacesMoved;
    
    public MoveSpacesCard(JSONObject JSONCardKey) throws JSONException {
        try {
            this.title = JSONCardKey.getString(JSONKeys.TITLE);
            this.spacesMoved = JSONCardKey.getInt(JSONKeys.SPACES_MOVED);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public void process(Game in, Player with) {


    }
}
