package matador.cards;

import org.json.JSONObject;

public class PlayersPayMoneyCard extends ChanceCard {
    private String title;

    public PlayersPayMoneyCard(JSONObject JSONCardKey) {
        this.title = JSONCardKey.getString("title");

    }

    public void process() {
        // TODO: Implementer process

    }
}
