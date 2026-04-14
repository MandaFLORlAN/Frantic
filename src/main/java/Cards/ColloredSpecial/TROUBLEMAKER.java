package Cards.ColloredSpecial;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Enums.Color;
import Game.GameState;

public class TROUBLEMAKER extends BaseColoredSpecial{
    public TROUBLEMAKER(Color color) {
        super("TROUBLEMAKER: " + color, 0, color);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        connector.triggerEvent(executorName);
    }

    @Override
    public Card copyCard() {
        return new TROUBLEMAKER(this.color);
    }
}
