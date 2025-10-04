package Events;

import Connector.Connector;
import Game.GameState;

import java.util.List;

public class Expansion extends BaseEvent {

    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> players = super.playersInOrders(connector, executor);
        for (int i = 1; i <= players.size(); i++) {
            connector.makePlayerDraw(players.get(i - 1), "Executor", i);
        }
    }
}
