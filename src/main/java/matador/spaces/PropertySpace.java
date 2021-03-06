package matador.spaces;

import org.json.JSONException;
import org.json.JSONObject;
import matador.*;
import matador.game.*;

import java.util.Optional;

public class PropertySpace extends Space {
    private Optional<Player> owner;
    public final int value;
    public final String color;
    public final String message;

    public PropertySpace(JSONObject data) throws JSONException {
        super(data);
        try {
            this.value = data.getInt(JSONKeys.VALUE);
            this.color = data.getString(JSONKeys.COLOR);
            this.message = data.getString(JSONKeys.MESSAGE);
            this.owner = Optional.empty();
        } catch (Exception e) {
            System.out.println("Read from JSON failed, check formatting");
            e.printStackTrace();
            throw new JSONException(e);
        }
    }

    public void buy(Player buyer) {
        owner = Optional.of(buyer);
        buyer.balance.deduct(value);
        buyer.buyProperty(this);
    }

    public Optional<Player> getOwner() {
        return owner;
    }

    public void reset() {
        this.owner = Optional.empty();
    }
}
