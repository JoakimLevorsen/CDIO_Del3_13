package matador.cards;

import matador.JSONKeys;
import matador.game.Game;
import matador.game.Player;
import org.json.JSONException;
import org.json.JSONObject;

public class MoveSpacesCard extends ChanceCard {
    private int spacesMoved;
    
    public MoveSpacesCard(JSONObject cardData) throws JSONException {
        super(cardData);
        try {
            this.spacesMoved = cardData.getInt(JSONKeys.SPACES_MOVED);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public void process(Game in, Player with) {
        with.moveForward(spacesMoved);
    }
}
