package matador.cards;

import matador.JSONKeys;
import matador.game.*;
import org.json.*;

public class PlayersPayMoneyCard extends ChanceCard {
    private int value;

    public PlayersPayMoneyCard(JSONObject cardData) throws JSONException {
        super(cardData);
        try {
            this.value = cardData.getInt(JSONKeys.AMOUNT);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public void process(Game in, Player with) {
        for (int i = 0; i < in.players.length; i++) {
            in.players[i].balance.deduct(value);
        }
        with.balance.increase(value * in.players.length);
    }
}
