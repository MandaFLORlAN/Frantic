package Events.Basegame;

import Connector.Connector;
import Events.BaseEvent;
import Game.GameState;

import java.util.List;

public class Communism extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> playerWithMostCards = super.playersWithMostCards(gameState);
        int maxCards = gameState.getCards().get(playerWithMostCards.getFirst());
        for (String playerName : connector.getAllPlayerNames()) {
            if (!playerWithMostCards.contains(playerName)) {
                connector.makePlayerDraw(playerName, "Communism",
                        maxCards-gameState.getCards().get(playerName));
            }
        }
    }
}
