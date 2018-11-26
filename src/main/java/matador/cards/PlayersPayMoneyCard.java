package matador.cards;

import matador.JSONKeys;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayersPayMoneyCard extends ChanceCard {
    private String title;

    public PlayersPayMoneyCard(JSONObject JSONCardKey) throws JSONException {
        try {
            this.title = JSONCardKey.getString(JSONKeys.TITLE);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public void process() {
        // TODO: Implementer process

    }
}
