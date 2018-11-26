package matador.cards;

import matador.JSONKeys;
import matador.game.*;
import matador.spaces.*;

import org.json.JSONException;
import org.json.JSONObject;

public class GoToSpaceCard extends ChanceCard {
    private String title;
    private int property;

    public GoToSpaceCard(JSONObject cardData) throws JSONException {
        super(cardData);
        try {
            this.title = cardData.getString(JSONKeys.TITLE);
            this.property = cardData.getInt(JSONKeys.PROPERTY);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }


    public void process(Game in, Player with) {
        with.setBoardPosition();

    }
}
