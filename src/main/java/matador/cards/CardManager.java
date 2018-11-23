package matador.cards;

import matador.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;


public class CardManager {
    ArrayList<ChanceCard> drawPile = new ArrayList<ChanceCard>();
    ArrayList<ChanceCard> discardPile = new ArrayList<ChanceCard>();
    JSONObject cards;

    public CardManager(JSONObject jsonData) throws JSONException {
        // Constructor makes the deck and shuffles it to a draw pile.
        cards = jsonData;
        int i = 0;
        drawPile.addAll(
            // TODO: the arguments here are pseudocode! Get info from json-file!
            // Cards that move or pay probably get an copy of diceroll or price?
            new GoToStartCard(type 0, cards.getJSONObject(i).getString(JSONKeys.CARDS),
            new MoveSpacesCard(type 1, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GoToSpaceCard(type 2, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GetLoseMoneyCard(type 3, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new PlayersPayMoneyCard(type 4, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GetOutOfJailCard(type 5, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GoToJailCard(type 2, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GoToSpaceCard(type 2, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GoToSpaceCard(type 2, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GoToSpaceCard(type 2, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new MoveSpacesCard(type 1, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GetLoseMoneyCard(type 3, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GetLoseMoneyCard(type 3, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GoToSpaceCard(type 2, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GoToSpaceCard(type 2, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new PlayersPayMoneyCard(type 4, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GetLoseMoneyCard(type 3, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GetLoseMoneyCard(type 3, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new GetLoseMoneyCard(type 3, cards.getJSONObject(i++).getString(JSONKeys.CARDS),
            new MoveSpacesCard(type 1, cards.getJSONObject(i++).getString(JSONKeys.CARDS)
            );
        shuffleCards();

    }
    private ChanceCard pickCard(){
        // Turn pile and shuffle if needed
        if (drawPile.isEmpty()){
            drawPile.addAll(discardPile);
            discardPile.clear();
            shuffleCards();

        }
        // Pick up card
        ChanceCard cardPickedUp = drawPile.get(0);

        // Move card to discard if not "Get out of jail" (Remember to add back to discard when played)
        // TODO: Pseudocode!, get the TYPE from json
        if (NOT TYPE 5){
            discardPile.add(drawPile.get(0));
        }
        // Removing the card from drawPile
        drawPile.remove(0);

        return cardPickedUp;  // Pick up card

    }
    private void shuffleCards(){
        Collections.shuffle(drawPile);

    }
    public void draw(){
        // Run pickCard and do all the card's actions
        pickCard().doCardActions();
    }
}
