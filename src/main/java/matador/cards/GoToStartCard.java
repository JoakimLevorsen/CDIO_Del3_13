package matador.cards;

import matador.JSONKeys;
import org.json.JSONException;
import org.json.JSONObject;

public class GoToStartCard extends ChanceCard {
    private String title;
    private int amount;

    public GoToStartCard(JSONObject JSONCardKey) throws JSONException {
        try {
            this.title = JSONCardKey.getString(JSONKeys.TITLE);
            this.amount = JSONCardKey.getInt(JSONKeys.AMOUNT);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }
    public void process() {
        // TODO: Implementer process

    }
}
