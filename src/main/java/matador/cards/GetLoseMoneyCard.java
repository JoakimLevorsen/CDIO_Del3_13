package matador.cards;

import org.json.*;

public class GetLoseMoneyCard extends ChanceCard {
    private String title; // Description
    private int amount;

    public GetLoseMoneyCard(JSONObject JSONCardKey){
        this.title = JSONCardKey.getString("type");
        this.amount = JSONCardKey.getInt("amount");

    }

    // TODO: Implementer process
}
