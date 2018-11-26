package matador.cards;

import matador.JSONKeys;
import org.json.*;

public class GetLoseMoneyCard extends ChanceCard {
    private String title;
    private int amount;

    public GetLoseMoneyCard(JSONObject JSONCardKey) throws JSONException {
        try {
            this.title = JSONCardKey.getString(JSONKeys.TITLE);
            this.amount = JSONCardKey.getInt("amount");
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }

    }

    public void process() {
        // TODO: Implementer process

    }
}
