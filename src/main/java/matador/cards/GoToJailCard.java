package matador.cards;

import matador.game.*;
import matador.JSONKeys;
import org.json.JSONException;
import org.json.JSONObject;

public class GoToJailCard extends ChanceCard {
    private String title;
    private int property;

    public GoToJailCard(JSONObject cardData) throws JSONException {
       super(cardData);
        try {
            this.title = cardData.getString(JSONKeys.TITLE);
            this.property = cardData.getInt(JSONKeys.PROPERTY);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public void process(Game in, Player with) {
        with.setBoardPosition(5);

    }
}
