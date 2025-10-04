package Events;

import Connector.Connector;
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

    protected List<String> playersInOrders(Connector connecter, String startPlayer) {
        List<String> players = connecter.getAllPlayerNames();
        List<String> playersInOrders = new ArrayList<>();
        int start = players.indexOf(startPlayer);
        for (int i = start; i < players.size()+start; i++) {
            playersInOrders.add(players.get(i % players.size()));
        }
        return playersInOrders;
    }

    protected List<String> getPlayersInOrder(List<String> players, List<String> order) {
        List<String> playersInOrders = new ArrayList<>();
        for (String player: order) {
            if (players.contains(player)) {
                playersInOrders.add(player);
            }
        }
        return playersInOrders;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
