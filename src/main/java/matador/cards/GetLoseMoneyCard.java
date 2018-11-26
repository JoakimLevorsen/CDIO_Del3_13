package matador.cards;

import org.json.*;

import matador.JSONKeys;
import matador.game.*;

public class GetLoseMoneyCard extends ChanceCard {
    private int value;

    public GetLoseMoneyCard(JSONObject cardData) throws JSONException {
        super(cardData);
        try {
            this.value = cardData.getInt(JSONKeys.AMOUNT);
        } catch (Exception e) {
            throw new JSONException("Read from JSON failed, check formatting.");
        }
    }

    public void process(Game in, Player with) {
        if(value > 0) {
            with.balance.increase(value);
        } else if(value < 0) {
            with.balance.deduct(value);
        }
    }
}
