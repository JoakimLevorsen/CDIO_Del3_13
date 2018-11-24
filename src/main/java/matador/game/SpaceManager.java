package matador.game;

import org.json.JSONException;
import org.json.JSONObject;
import matador.*;
import matador.spaces.*;

public class SpaceManager {
    private Space[] fields;


    public SpaceManager(JSONObject data) throws JSONException {
        matador.game.CardManager manager = new matador.game.CardManager(data);
        fields = new Space[data.getJSONArray(JSONKeys.SPACES).length()];

        for (int i = 0; i < data.getJSONArray(JSONKeys.SPACES).length() ; i++){
            //fields[i] = new Space(data.getJSONArray(JSONKeys.TYPE).getJSONObject(i));
            data.getJSONArray(JSONKeys.TYPE).getJSONObject(i);
            int type = data.getJSONArray(JSONKeys.SPACES).getJSONObject(i).getInt(JSONKeys.TYPE);

            switch(type) {
                case 0:
                    fields[i] = new StartSpace(data.getJSONArray(JSONKeys.SPACES).getJSONObject(i));
                    break;
                case 1:
                    fields[i] = new ParkingSpace(data.getJSONArray(JSONKeys.SPACES).getJSONObject(i));
                case 2:
                    fields[i] = new PropertySpace(data.getJSONArray(JSONKeys.SPACES).getJSONObject(i));
                case 3:
                    fields[i] = new JailSpace(data.getJSONArray(JSONKeys.SPACES).getJSONObject(i));
                case 4:
                    fields[i] = new GoToJailSpace(data.getJSONArray(JSONKeys.SPACES).getJSONObject(i));
                case 5:
                    fields[i] = new ChanceSpace(data.getJSONArray(JSONKeys.SPACES).getJSONObject(i), manager);

            }
        }
    }

    public Space getSpace(int index) {
        return fields[index];
    }
}
