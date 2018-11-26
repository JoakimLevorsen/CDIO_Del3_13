package matador.cards;

import matador.spaces.*;

import org.json.JSONException;
import org.json.JSONObject;

public class GoToSpaceCard extends ChanceCard {
    private String title;
    private int property;

    public GoToSpaceCard(JSONObject JSONCardKey) throws JSONException {
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
