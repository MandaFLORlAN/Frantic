package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Events.BaseEvent;
import Game.GameState;

import java.util.List;

public class Market extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> players = super.playersInOrders(connector, executor);
        List<Card> cardsToSale = connector.drawRandomCards(players.size());
        for (String player : players) {
            Card card = Card.fromString(connector.getChosenCardsFromPlayer(player,cardsToSale,1).getFirst());
            connector.addCardToPlayer(player, card);
            cardsToSale.remove(card);
        }
    }
}
