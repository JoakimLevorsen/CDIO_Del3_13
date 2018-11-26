package matador.game;

import matador.JSONKeys;
import matador.cards.*;
import org.json.*;
import java.util.*;

    public class CardManager {
        ArrayList<ChanceCard> drawPile = new ArrayList<ChanceCard>();
        ArrayList<ChanceCard> discardPile = new ArrayList<ChanceCard>();

        public CardManager(JSONObject jsonData) throws JSONException {
            // Constructor makes the deck and shuffles it to a draw pile.

            JSONArray cards = jsonData.getJSONArray(JSONKeys.CARDS);

            for (int i = 0; i < cards.length(); i++){

                switch(cards.getJSONObject(i).getInt(JSONKeys.TYPE)){
                    case 0: drawPile.add(new GoToStartCard(cards.getJSONObject(i))); break;
                    case 1: drawPile.add(new MoveSpacesCard(cards.getJSONObject(i))); break;
                    case 2: drawPile.add(new GoToSpaceCard(cards.getJSONObject(i))); break;
                    case 3: drawPile.add(new GetLoseMoneyCard(cards.getJSONObject(i))); break;
                    case 4: drawPile.add(new PlayersPayMoneyCard(cards.getJSONObject(i))); break;
                    case 5: drawPile.add(new GetOutOfJailCard(cards.getJSONObject(i))); break;
                    case 6: drawPile.add(new GoToJailCard(cards.getJSONObject(i))); break;
                    default: throw new JSONException("Read from JSON failed, check formatting.");
                }
            }
            shuffleCards();
        }

        public ChanceCard pickCard(){
            // turn pile if needed
            turnPile();
            // Pick up a card
            ChanceCard cardPickedUp = drawPile.get(0);

            // Move card to discard if not "Get out of jail" (Remember to add back to discard when played)
            if (!(cardPickedUp instanceof GetOutOfJailCard)){
                discardPile.add(drawPile.get(0));
            }
            drawPile.remove(0);
            return cardPickedUp;
        }

        public void shuffleCards(){
            Collections.shuffle(drawPile);
        }

        public void turnPile(){
            if (drawPile.isEmpty()){
                drawPile.addAll(discardPile);
                discardPile.clear();
                shuffleCards();
            }
        }

        public void draw(){
            // pickCard().process(); eller sådan noget!
        }
    }


