package matador.cards;

import matador.JSONKeys;
import matador.game.*;

import org.json.JSONException;
import org.json.JSONObject;

public class GetOutOfJailCard extends ChanceCard {
    private String title;
    private Player owner;


    public GetOutOfJailCard(JSONObject cardData) throws JSONException {
        super(cardData);
        try {
            this.title = cardData.getString(JSONKeys.TITLE);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void process(Game in, Player with) {


    }
}
