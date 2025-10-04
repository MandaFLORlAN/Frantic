package Events;


import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Game.GameState;

import java.util.List;

public class RobinHood extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> order = super.playersInOrders(connector, executor);
        String maxCardPlayer = super.getPlayersInOrder(super.playersWithMostCards(gameState), order ).getFirst();
        String minCardPlayer = super.getPlayersInOrder(super.playersWithLeastCards(gameState), order ).getFirst();
        List<Card> minCards = connector.getAllCardsOfPlayer(minCardPlayer).stream().toList();
        List<Card> maxCards = connector.getAllCardsOfPlayer(maxCardPlayer).stream().toList();
        connector.transferCardFromPlayerToPlayer(minCards, minCardPlayer, maxCardPlayer);
        connector.transferCardFromPlayerToPlayer(maxCards, maxCardPlayer, minCardPlayer);
    }
}
