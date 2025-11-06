package Events;

import Connector.Connector;
import Connector.PointBasedConnector;
import Game.GameState;

public class DoomsDay extends PointBasedEvent{
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        if (connector instanceof PointBasedConnector pointConnector) {
            for (String playerName : connector.getAllPlayerNames()) {
                pointConnector.discardCards(playerName, connector.getAllCardsOfPlayer(playerName));
                pointConnector.addPointsToPlayer(playerName, 50);
            }
        }
    }
}
