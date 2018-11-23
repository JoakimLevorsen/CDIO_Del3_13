package matador.cards;

import org.json.JSONObject;

public class GoToJailCard extends ChanceCard {
    private String title; // Title er en beskrivelse!

    public GoToJailCard(JSONObject JSONCardKey){
        this.title = JSONCardKey.getString("type");
    }

    // TODO: Implementer process
}
