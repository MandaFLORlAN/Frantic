package Events;

import Connector.Connector;
import Game.GameState;

public interface Event {
    void executeEvent(Connector connector, String executor, GameState gameState);
}
