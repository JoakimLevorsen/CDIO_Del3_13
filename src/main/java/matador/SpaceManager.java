package matador;

import org.json.JSONException;
import org.json.JSONObject;

public class SpaceManager {
    private Space[] fields;

    public SpaceManager(JSONObject data) throws JSONException {
        fields = new Space[data.getJSONArray(JSONKeys.spaces).length()];
        for (int i = 0; i < data.getJSONArray(JSONKeys.spaces).length() ; i++){
            fields[i] = new Space(data.getJSONArray(JSONKeys.spaces).getJSONObject(i));
        }
    }

    public Space getSpace(int index) {
        return fields[index];
    }
}