package matador.cards;

import matador.spaces.*;
import org.json.JSONObject;

public class GoToSpaceCard extends ChanceCard {
    private String title; // Description!
    private int property;

    public GoToSpaceCard(JSONObject JSONCardKey){
        this.title = JSONCardKey.getString("type");
        this.property = JSONCardKey.getInt("property");
    }


    // TODO: Implementer process

}
