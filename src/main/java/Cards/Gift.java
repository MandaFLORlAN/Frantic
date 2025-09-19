package Cards;

import Connector.Connector;
import Enums.Color;
import Game.GameState;

import java.util.List;

public class Gift extends Card implements SpecialCard{
    public Gift(Color color) {
        super("Gift: " + color.name(), 7, color);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector) {
        String target = connector.getPlayerTarget(executorName, "Gift");
        List<Card> cardsToGive = connector.getCardToGiveAway(executorName, 2);
        connector.transferCardFromPlayerToPlayer(cardsToGive, executorName, target);
    }

    @Override
    public boolean isPlayable(GameState gs) {
        return gs.getPlayableColor() == this.color || gs.getLastCardName().startsWith("Gift:");
    }
}
