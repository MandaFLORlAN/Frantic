package Cards.ColloredSpecial;

import Cards.InterfacesGroundclass.Card;
import Cards.InterfacesGroundclass.SpecialCard;
import Connector.Connector;
import Enums.Color;
import Game.GameState;

import java.util.List;

public class Gift extends BaseColoredSpecial {
    public Gift(Color color) {
        super("Gift: " + color.name(), 7, color);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        String target = connector.getPlayerTargets(executorName, "Gift", 1).getFirst();
        List<Card> cardsToGive = connector.getCardToGiveAway(executorName, 2);
        connector.transferCardFromPlayerToPlayer(cardsToGive, executorName, target);
    }

}
