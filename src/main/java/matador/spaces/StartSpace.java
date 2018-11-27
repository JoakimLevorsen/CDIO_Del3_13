package matador.spaces;

import org.json.JSONObject;

public class StartSpace extends Space {
    public final int rewardValue;

    public StartSpace(JSONObject data) {
        super(data);
        rewardValue = 2;
    }
}
