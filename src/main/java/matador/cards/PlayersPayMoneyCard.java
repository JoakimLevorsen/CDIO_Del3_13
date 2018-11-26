package matador.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class PlayersPayMoneyCard extends ChanceCard {
    private String title;

    public PlayersPayMoneyCard(JSONObject JSONCardKey) throws JSONException {
        try {
            this.title = JSONCardKey.getString("title");
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public void process() {
        // TODO: Implementer process

    }
}
