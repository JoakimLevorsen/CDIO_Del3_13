package matador.cards;

import org.json.JSONObject;

public class GoToJailCard extends ChanceCard {
    private String title;
    private int property;

    public GoToJailCard(JSONObject JSONCardKey){
        this.title = JSONCardKey.getString("title");
        this.property = JSONCardKey.getInt("property");
    }

    public void process() {
        // TODO: Implementer process

    }
}
