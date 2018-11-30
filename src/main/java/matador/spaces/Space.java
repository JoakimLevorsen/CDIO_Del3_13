package matador.spaces;

import org.json.JSONException;
import org.json.JSONObject;
import matador.*;

abstract public class Space {
    public String name;

    public Space(JSONObject data) throws JSONException {
        try {
            name = data.getString(JSONKeys.NAME);
        } catch (Exception e) {
            System.out.println("Read from JSON failed, check formatting");
            e.printStackTrace();
            throw new JSONException(e);
        }
    }
}
