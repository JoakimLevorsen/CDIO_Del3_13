package matador.cards;

import org.json.*;

import matador.*;
import matador.game.*;

abstract public class ChanceCard {

    final String title;

    public ChanceCard(JSONObject cardData) throws JSONException {
        try {
            this.title = cardData.getString(JSONKeys.TITLE);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    abstract public void process(Game in, Player with);
    
}
