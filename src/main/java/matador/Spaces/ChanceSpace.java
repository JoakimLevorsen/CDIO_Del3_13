package matador.Spaces;

import org.json.JSONObject;
import matador.Game.*;
import matador.Cards.*;

public class ChanceSpace extends Space
{
    private CardManager manager;

    public ChanceSpace (JSONObject data, CardManager manager)
    {
        super(data);
        this.manager = manager;
    }
    public ChanceCard draw()
    {
        return manager.draw();
    }
}
