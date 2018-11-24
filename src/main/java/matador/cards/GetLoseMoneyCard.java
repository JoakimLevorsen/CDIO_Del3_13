package matador.cards;

import org.json.*;

public class GetLoseMoneyCard extends ChanceCard {
    private String title;
    private int amount;

    public GetLoseMoneyCard(JSONObject JSONCardKey){
        this.title = JSONCardKey.getString("title");
        this.amount = JSONCardKey.getInt("amount");

    }

    public void process() {
        // TODO: Implementer process

    }
}
