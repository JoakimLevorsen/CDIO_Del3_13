package matador;

import org.json.JSONObject;

import java.util.ArrayList;

public class JailSpace extends Space
{
    private ArrayList<Player> prisoners;

    public JailSpace (JSONObject data)
    {
        super(data);
    }

    public void jailPlayer(Player jailee)
    {
        prisoners.add(jailee);
    }

    public void releasePlayer(Player jailee)
    {
        try
        {
            prisoners.remove(jailee);
        }
        catch (Exception e)
        {
        }
    }
}
