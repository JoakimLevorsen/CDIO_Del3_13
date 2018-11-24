package matador.cards;

import matador.spaces.*;
import org.json.JSONObject;

public class GoToSpaceCard extends ChanceCard {
    private String title;
    private int property;

    public GoToSpaceCard(JSONObject JSONCardKey){
        this.title = JSONCardKey.getString("title");
        this.property = JSONCardKey.getInt("property");
    }


    public void process() {
        // TODO: Implementer process

    }
}
