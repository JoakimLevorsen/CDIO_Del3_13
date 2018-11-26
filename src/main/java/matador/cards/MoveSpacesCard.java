package matador.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class MoveSpacesCard extends ChanceCard {
    private String title;
    private int spacesMoved;
    
    public MoveSpacesCard(JSONObject JSONCardKey) throws JSONException {
        try {
            this.title = JSONCardKey.getString("title");
            this.spacesMoved = JSONCardKey.getInt("spacesMoved");
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public void process() {
        // TODO: Implementer process

    }
}
