package matador.cards;

import matador.game.*;

import org.json.JSONException;
import org.json.JSONObject;

public class GetOutOfJailCard extends ChanceCard {
    private String title;
    private Player owner;


    public GetOutOfJailCard(JSONObject JSONCardKey) throws JSONException {
        try {
            this.title = JSONCardKey.getString("title");
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

    public void process() {
        // TODO: Implementer process

    }
}
