package matador.spaces;

import org.json.*;

import matador.JSONKeys;
import matador.game.*;
import java.util.ArrayList;

public class JailSpace extends Space {
    private ArrayList<Player> prisoners;
    public final int bail;

    public JailSpace(JSONObject data) {
        super(data);
        this.bail = data.getInt(JSONKeys.VALUE);
        this.prisoners = new ArrayList<Player>();
    }

    public boolean isInJail(Player player) {
        return prisoners.contains(player);
    }

    public void jailPlayer(Player jailee) {
        prisoners.add(jailee);
    }

    public void releasePlayer(Player jailee) throws PlayerNotInJailException {
        try {
            prisoners.remove(jailee);
        } catch (Exception e) {
            throw new PlayerNotInJailException("This player is not in jail");
        }
    }
}
