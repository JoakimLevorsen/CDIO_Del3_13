package matador.cards;

import matador.game.*;
import matador.spaces.JailSpace;
import matador.spaces.Space;
import org.json.JSONException;
import org.json.JSONObject;

public class GoToJailCard extends ChanceCard {

    public GoToJailCard(JSONObject cardData) throws JSONException {
        super(cardData);
    }

    public void process(Game in, Player with) {
        try {
            with.setBoardPosition(in.sManager.getJailSpaceIndex());
            JailSpace jail = in.sManager.getJailSpace();
            jail.jailPlayer(with);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }
}
