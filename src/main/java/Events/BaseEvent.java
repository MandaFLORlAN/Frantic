package Events;

import Game.GameState;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseEvent implements Event{
    public BaseEvent() {
    }

    protected List<String> playersWithMostCards(GameState gameState) {
        List<String> maxPlayers = new ArrayList<>();
        int currentMax = 0;
        for (String player: gameState.getCards().keySet()) {
            if (gameState.getCards().get(player) > currentMax) {
                currentMax = gameState.getCards().get(player);
                maxPlayers.clear();
                maxPlayers.add(player);
            } else if (gameState.getCards().get(player) == currentMax) {
                maxPlayers.add(player);
            }
        }
        return maxPlayers;
    }

    protected List<String> playersWithLeastCards(GameState gameState) {
        List<String> maxPlayers = new ArrayList<>();
        int currentMin = Integer.MAX_VALUE;
        for (String player: gameState.getCards().keySet()) {
            if (gameState.getCards().get(player) < currentMin) {
                currentMin = gameState.getCards().get(player);
                maxPlayers.clear();
                maxPlayers.add(player);
            } else if (gameState.getCards().get(player) == currentMin) {
                maxPlayers.add(player);
            }
        }
        return maxPlayers;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
