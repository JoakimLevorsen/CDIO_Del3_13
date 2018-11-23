package matador.cards;

import org.json.JSONObject;

public class PlayersPayMoneyCard extends ChanceCard {
    private String title; // Description

    
    public PlayersPayMoneyCard(JSONObject JSONCardKey){
        this.title = JSONCardKey.getString("title");
    }

    // TODO: Implementer process
}
