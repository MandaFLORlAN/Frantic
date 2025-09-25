package Cards.Wishcards;

import Cards.InterfacesGroundclass.BasicWishcard;
import Cards.InterfacesGroundclass.SpecialCard;
import Connector.Connector;
import Game.GameState;

public class Counterattack extends BasicWishcard implements SpecialCard {

    public Counterattack() {
        super("Counterattack", 0, null);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        connector.wishUpdate(executorName, connector.getPlayerColorWish(executorName));
    }
}
