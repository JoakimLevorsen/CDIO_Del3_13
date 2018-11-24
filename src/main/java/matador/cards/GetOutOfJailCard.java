package matador.cards;

import matador.game.*;
import org.json.JSONObject;

public class GetOutOfJailCard extends ChanceCard {
    private String title;
    private Player owner;


    public GetOutOfJailCard(JSONObject JSONCardKey) {
        this.title = JSONCardKey.getString("title");
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
