package Cards.Wishcards;

import Cards.InterfacesGroundclass.BasicWishcard;
import Cards.InterfacesGroundclass.SpecialCard;
import Connector.Connector;
import Game.GameState;

public class Equality extends BasicWishcard implements SpecialCard {

    public Equality() {
        super("Equality", 0, null);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        String target = connector.getPlayerTargets(executorName, "Equality", 1).getFirst();
        int cardDiff = gs.getCards().get(executorName) - gs.getCards().get(target);
        if (cardDiff > 0) connector.makePlayerDraw(target, "Equality from " + executorName, cardDiff);
        super.ExecuteWish(executorName, connector);
    }

}
