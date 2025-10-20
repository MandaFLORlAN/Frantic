package Connector;

import Events.Event;
import Events.Basegame.SurpriseParty;
import Game.Game;
import Players.Player;

import java.util.List;
import java.util.Map;

public class TestConnector extends BaseConnector {
    public TestConnector() {
    }

    public Game getGame() {
        return this.game;
    }
    public Map<String, Player> getPlayers() {
        return this.players;
    }

    public void setState(Game game, Map<String, Player> players, List<String> playOrder) {
        this.game = game;
        this.players = players;
        this.playOrder = playOrder;
    }

    @Override
    public void triggerEvent(String executorName) {
        new SurpriseParty().executeEvent(this, executorName, this.game.getGameState());
    }

    public void triggerEvent(String executorName, Event event) {
        event.executeEvent(this, executorName, this.game.getGameState());
    }
}
