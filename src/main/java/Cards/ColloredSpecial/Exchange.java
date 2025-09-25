package Cards.ColloredSpecial;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Enums.Color;
import Game.GameState;

import java.util.List;

public class Exchange extends BaseColoredSpecial {
    public Exchange(Color color) {
        super("Exchange: " + color, 0, color);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        String target = super.getTarget(executorName, "Exchange", connector, this, gs);
        if (target == null) return;
        List<Card> cardsToDraw = connector.drawRandomCardFromPlayer(target, 2);
        List<Card> cardsToGive = connector.getCardToGiveAway(executorName, 2);
        connector.transferCardFromPlayerToPlayer(cardsToDraw, target, executorName);
        connector.transferCardFromPlayerToPlayer(cardsToGive, executorName, target);
    }
}
