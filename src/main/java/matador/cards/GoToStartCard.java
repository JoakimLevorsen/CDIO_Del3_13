package matador.cards;

import org.json.JSONObject;

public class GoToStartCard extends ChanceCard {
    private String title; // Title is a description text!
    private int amount; // Reward for passing start.

    public GoToStartCard(JSONObject JSONCardKey){
            this.title = JSONCardKey.getString("title");
            this.amount = JSONCardKey.getInt("amount");

    }

}
