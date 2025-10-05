package Events.Basegame;

import Connector.Connector;
import Events.BaseEvent;
import Game.GameState;

import java.util.List;

public class ThirdTimeLucky extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> players = super.playersInOrders(connector, executor);
        for (int i = 0; i < 3; i++) {
            for (String player : players) {
                connector.makePlayerDraw(player, "Third Time Lucky", 1);
            }
        }
    }
}
