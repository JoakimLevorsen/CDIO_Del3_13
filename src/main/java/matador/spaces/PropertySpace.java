package matador.spaces;

import matador.JSONKeys;
import matador.Player;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

public class PropertySpace extends Space
{
    private Optional<Player> owner;
    private final int value;
    private String colour;

    public PropertySpace(JSONObject data) throws JSONException
    {
        super(data);
        try {
            value = data.getInt(JSONKeys.COST);
            colour = data.getString(JSONKeys.COLOUR);
            this.owner = Optional.empty();
            }
            catch (Exception e)
            {
                System.out.println("Read from JSON failed, check formatting");
                e.printStackTrace();
                throw new JSONException(e);
            }
    }

    public boolean buy(Player buyer)
    {
        if (buyer.balance.getBalance() < value || !owner.isPresent())
        {
            return false;
        }
        owner = Optional.of(buyer);
        buyer.balance.deduct(value);
        return true;
    }

    public void reset()
    {
         this.owner = Optional.empty();
    }

}

