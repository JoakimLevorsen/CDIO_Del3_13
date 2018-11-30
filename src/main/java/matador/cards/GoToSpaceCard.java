package matador.cards;

import matador.JSONKeys;
import matador.game.*;

import org.json.JSONException;
import org.json.JSONObject;

public class GoToSpaceCard extends ChanceCard {
    private int property;

    public GoToSpaceCard(JSONObject cardData) throws JSONException {
        super(cardData);
        try {
            this.property = cardData.getInt(JSONKeys.PROPERTY);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public void process(Game in, Player with) {
        int withBoardPosition = with.getBoardPosition();
        if (withBoardPosition != property) {
            int spaces = property - withBoardPosition;
            if (spaces < 0) {
                spaces = 24 - Math.abs(spaces);
            }
            with.moveForwardAlsoInUI(spaces);
        }
    }
}
