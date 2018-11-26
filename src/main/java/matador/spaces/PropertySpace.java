package matador.spaces;

import org.json.JSONException;
import org.json.JSONObject;
import matador.*;
import matador.game.*;

import java.util.Optional;

public class PropertySpace extends Space
{
    private Optional<Player> owner;
    private final int value;
    private String color;

    public PropertySpace(JSONObject data) throws JSONException
    {
        super(data);
        try {
            value = data.getInt(JSONKeys.VALUE);
            color = data.getString(JSONKeys.COLOR);
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

