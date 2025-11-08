package Events.Basegame;

import Connector.Connector;
import Connector.PointBasedConnector;
import Events.BaseEvent;
import Game.GameState;

public class FinishLine extends BaseEvent {

    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        if (connector instanceof PointBasedConnector pointConnector) {
            pointConnector.finishLine();
        }
    }
}
