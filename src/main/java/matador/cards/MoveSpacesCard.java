package matador.cards;

import org.json.JSONObject;

public class MoveSpacesCard extends ChanceCard {
    private String title;
    private int spacesMoved;
    
    public MoveSpacesCard(JSONObject JSONCardKey){
        this.title = JSONCardKey.getString("title");
        this.spacesMoved = JSONCardKey.getInt("spacesMoved");
    }

    public void process() {
        // TODO: Implementer process

    }
}
