package matador.cards;

import matador.game.*;
import org.json.JSONObject;

public class GetOutOfJailCard extends ChanceCard {
    private Player owner;

    
    public GetOutOfJailCard(JSONObject JSONCardKey){

    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    // TODO: Implementer process
}
