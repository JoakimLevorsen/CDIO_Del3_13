package matador.cards;

import matador.game.*;
import org.json.JSONException;
import org.json.JSONObject;

public class GetOutOfJailCard extends ChanceCard {
    private Player owner;

    public GetOutOfJailCard(JSONObject cardData) throws JSONException {
        super(cardData);
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        owner.setMyJailCard(this);
    }

    public void process(Game in, Player with) {
    }
}
