package Cards.ColloredSpecial;

import Connector.Connector;
import Enums.Color;
import Game.GameState;

public class TROUBLEMAKER extends BaseColoredSpecial{
    public TROUBLEMAKER(Color color) {
        super("TROUBLEMAKER:" + color, 0, color);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {

    }
}
