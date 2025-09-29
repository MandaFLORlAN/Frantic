package Cards.Wishcards;

import Cards.InterfacesGroundclass.BasicWishcard;
import Connector.Connector;
import Game.GameState;

public class NiceTry extends BasicWishcard {

    public NiceTry() {
        super("Nice Try", 0, null);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        connector.wishUpdate(executorName, connector.getPlayerColorWish(executorName));
    }
}
