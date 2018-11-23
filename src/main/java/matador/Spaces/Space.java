package matador.Spaces;

import org.json.JSONException;
import org.json.JSONObject;
import matador.*;

public class Space {
    public String title;

    public Space(JSONObject data) throws JSONException
    {
        try
        {
            title = data.getString(JSONKeys.NAME);
        }
        catch (Exception e)
        {
            System.out.println("Read from JSON failed, check formatting");
            e.printStackTrace();
            throw new JSONException(e);
        }

    }

}
