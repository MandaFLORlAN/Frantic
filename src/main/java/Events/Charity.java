package Events;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Game.GameState;

import java.util.List;

public class Charity extends BaseEvent {

    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> playersWithMaxCards = playersWithMostCards(gameState);
        for (String player : connector.getAllPlayerNames()) {
            if (!playersWithMaxCards.contains(player)) {
                for (String playerToDraw : playersWithMaxCards) {
                    List<Card> card = connector.drawRandomCardFromPlayer(playerToDraw, 1);
                    connector.transferCardFromPlayerToPlayer(card, playerToDraw, player);
                }
            }
        }
    }
}
