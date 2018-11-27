package matador.game;

import org.json.JSONException;
import org.json.JSONObject;
import matador.*;
import matador.spaces.*;

public class SpaceManager {
    private Space[] fields;
    public final CardManager cardManager;

    public SpaceManager(JSONObject data) throws JSONException {
        cardManager = new matador.game.CardManager(data);
        fields = new Space[data.getJSONArray(JSONKeys.SPACES).length()];

        for (int i = 0; i < data.getJSONArray(JSONKeys.SPACES).length(); i++) {
            // fields[i] = new Space(data.getJSONArray(JSONKeys.TYPE).getJSONObject(i));
            data.getJSONArray(JSONKeys.TYPE).getJSONObject(i);
            int type = data.getJSONArray(JSONKeys.SPACES).getJSONObject(i).getInt(JSONKeys.TYPE);

            switch (type) {
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
                fields[i] = new ChanceSpace(data.getJSONArray(JSONKeys.SPACES).getJSONObject(i), cardManager);

            }
        }
    }

    public Space getSpace(int index) {
        return fields[index];
    }

    public StartSpace getStartSpace() throws SpaceNotFoundException {
        for (Space s : fields) {
            if (s instanceof StartSpace)
                return (StartSpace) s;
        }
        throw new SpaceNotFoundException("Startspace not found on this board");
    }

    public JailSpace getJailSpace() throws SpaceNotFoundException {
        for (Space s : fields) {
            if (s instanceof JailSpace)
                return (JailSpace) s;
        }
        throw new SpaceNotFoundException("Jail space not found on this board");
    }

    public int getJailSpaceIndex() throws SpaceNotFoundException {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] instanceof JailSpace)
                return i;
        }
        throw new SpaceNotFoundException("Jail space not found on this board");
    }
}
