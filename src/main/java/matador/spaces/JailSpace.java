package matador.spaces;

import org.json.JSONObject;
import matador.game.*;
import java.util.ArrayList;

public class JailSpace extends Space {
    private ArrayList<Player> prisoners;

    public JailSpace(JSONObject data) {
        super(data);
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
