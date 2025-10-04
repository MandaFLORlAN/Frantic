package Events;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Game.GameState;

import java.util.List;

public class Recession extends BaseEvent {

    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> players = super.playersInOrders(connector, executor);
        for (int i = 1; i <= players.size(); i++) {
            String playerName = players.get(i-1);
            List<Card> cardsToDiscard = connector.getCardToGiveAway(playerName, i);
            connector.discardCards(playerName, cardsToDiscard);
        }
    }

}
