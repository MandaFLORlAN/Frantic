package Events.Basegame;

import Connector.Connector;
import Connector.PointBasedConnector;
import Events.PointBasedEvent;
import Game.GameState;

public class TimeBomb extends PointBasedEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        if (connector instanceof PointBasedConnector pointConnector) {
            pointConnector.timeBomb();
        }
    }
}
