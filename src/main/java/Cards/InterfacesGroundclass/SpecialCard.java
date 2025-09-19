package Cards.InterfacesGroundclass;

import Connector.Connector;
import Game.GameState;

public interface SpecialCard {
    void executeSpecialFunction(String executorName, Connector connector, GameState gs);
}
