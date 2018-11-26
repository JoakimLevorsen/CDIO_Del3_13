package matador.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class GoToJailCard extends ChanceCard {
    private String title;
    private int property;

    public GoToJailCard(JSONObject JSONCardKey) throws JSONException {
        try {
            this.title = JSONCardKey.getString("title");
            this.property = JSONCardKey.getInt("property");
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public void process() {
        // TODO: Implementer process

    }
}
