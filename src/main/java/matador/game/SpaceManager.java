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
            int type;
            JSONObject myData;
            try {
                myData = data.getJSONArray(JSONKeys.SPACES).getJSONObject(i);
                type = myData.getInt(JSONKeys.TYPE);
            } catch (Exception e) {
                throw new JSONException("Could not read type from space at index " + i);
            }

            try {
                switch (type) {
                case 0:
                    fields[i] = new StartSpace(myData);
                    break;
                case 1:
                    fields[i] = new ParkingSpace(myData);
                    break;
                case 2:
                    fields[i] = new PropertySpace(myData);
                    break;
                case 3:
                    fields[i] = new JailSpace(myData);
                    break;
                case 4:
                    fields[i] = new GoToJailSpace(myData);
                    break;
                case 5:
                    fields[i] = new ChanceSpace(myData, cardManager);
                    break;
                }
            } catch (JSONException e) {
                System.out.println("Could not create space at index " + i);
                throw e;
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
