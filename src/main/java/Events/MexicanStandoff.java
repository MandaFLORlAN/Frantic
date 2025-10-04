package Events;

import Connector.Connector;
import Game.GameState;

public class MexicanStandoff extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        for (String playerName : connector.getAllPlayerNames()) {
            connector.discardCards(playerName, connector.getAllCardsOfPlayer(playerName).stream().toList());
            connector.makePlayerDraw(playerName, "Mexican Standoff", 3);
        }
    }
}
