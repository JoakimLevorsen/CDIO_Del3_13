package matador.game;

import matador.*;
import matador.cards.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;


    public class CardManager {
        ArrayList<ChanceCard> drawPile = new ArrayList<ChanceCard>();
        ArrayList<ChanceCard> discardPile = new ArrayList<ChanceCard>();
        JSONArray cards;

        public CardManager(JSONObject jsonData) throws JSONException {
            // Constructor makes the deck and shuffles it to a draw pile.
            JSONArray cards = jsonData.getJSONArray(JSONKeys.CARDS);

            for (int i = 0; i < cards.length(); i++){
                ChanceCard newCard;
                switch(cards.getJSONObject(i).getInt("type")){
                    case 0:
                        newCard = new GoToStartCard(cards.getJSONObject(i));
                        break;
                    case 1:
                        newCard = new MoveSpacesCard(cards.getJSONObject(i));
                        break;
                    case 2:
                        newCard = new GoToSpaceCard(cards.getJSONObject(i));
                            break;
                    case 3:
                        newCard = new GetLoseMoneyCard(cards.getJSONObject(i));
                        break;
                    case 4:
                        newCard = new PlayersPayMoneyCard(cards.getJSONObject(i));
                        break;
                    case 5:
                        newCard = new GetOutOfJailCard(cards.getJSONObject(i));
                        break;
                    case 6:
                        newCard = new GoToJailCard(cards.getJSONObject(i));
                        break;
                    default:
                        new JSONException("Could not interpret card.\"type\" index from JSON");
                }
                drawPile.add(newCard);

            }
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
            if (cardPickedUp instanceof GetOutOfJailCard){
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
            //pickCard().doCardActions(); //or something like that
        }
    }


}
