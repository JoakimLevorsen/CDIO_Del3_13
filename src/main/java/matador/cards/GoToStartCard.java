package matador.cards;

import matador.JSONKeys;
import matador.game.Game;
import matador.game.Player;
import org.json.JSONException;
import org.json.JSONObject;

public class GoToStartCard extends ChanceCard {
    private String title;
    private int amount;

    public GoToStartCard(JSONObject cardData) throws JSONException {
        super(cardData);
        try {
            this.title = cardData.getString(JSONKeys.TITLE);
            this.amount = cardData.getInt(JSONKeys.AMOUNT);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }
    public void process(Game in, Player with) {
        with.setBoardPosition(0);

    }
}
