package matador.cards;

import org.json.JSONObject;

public class GoToStartCard extends ChanceCard {
    private String title;
    private int amount;

    public GoToStartCard(JSONObject JSONCardKey){
            this.title = JSONCardKey.getString("title");
            this.amount = JSONCardKey.getInt("amount");

    }
    public void process() {
        // TODO: Implementer process

    }
}
