package matador.spaces;

import matador.cards.CardManager;
import matador.cards.ChanceCard;
import org.json.JSONObject;

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
