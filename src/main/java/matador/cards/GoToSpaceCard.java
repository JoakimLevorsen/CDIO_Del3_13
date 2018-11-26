package matador.cards;

import matador.JSONKeys;
import matador.spaces.*;

import org.json.JSONException;
import org.json.JSONObject;

public class GoToSpaceCard extends ChanceCard {
    private String title;
    private int property;

    public GoToSpaceCard(JSONObject JSONCardKey) throws JSONException {
        try {
            this.title = JSONCardKey.getString(JSONKeys.TITLE);
            this.property = JSONCardKey.getInt(JSONKeys.PROPERTY);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }


    public void process() {
        // TODO: Implementer process

    }
}
