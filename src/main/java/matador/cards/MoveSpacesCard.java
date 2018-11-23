package matador.cards;

import org.json.JSONObject;

public class MoveSpacesCard extends ChanceCard {
    private String title; // Description
    private int amount; // Amount of spaces moved
    
    public MoveSpacesCard(JSONObject JSONCardKey){
        this.title = JSONCardKey.getString("type");
        this.amount = JSONCardKey.getInt("amount");
    }

    // TODO: Implementer process
}
