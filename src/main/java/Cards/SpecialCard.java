package Cards;

import Connector.Connector;
import ConsolePlayers.Player;

public interface SpecialCard {
    void executeSpecialFunction(String executorName, Connector connector);
}
