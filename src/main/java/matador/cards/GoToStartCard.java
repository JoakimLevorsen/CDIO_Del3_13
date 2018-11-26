package matador.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class GoToStartCard extends ChanceCard {
    private String title;
    private int amount;

    public GoToStartCard(JSONObject JSONCardKey) throws JSONException {
        try {
            this.title = JSONCardKey.getString("title");
            this.amount = JSONCardKey.getInt("amount");
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }
    public void process() {
        // TODO: Implementer process

    }
}
