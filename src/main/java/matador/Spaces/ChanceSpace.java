package matador.spaces;

import org.json.JSONObject;
import matador.game.*;
import matador.cards.*;

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
