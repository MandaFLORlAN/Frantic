package Connector;

import Events.SurpriseParty;
import Game.Game;
import Players.Player;

import java.util.Map;

public class TestConnector extends ConsoleConnector {
    public TestConnector() {
    }

    public Game getGame() {
        return this.game;
    }
    public Map<String, Player> getPlayers() {
        return this.players;
    }

    public void setState(Game game, Map<String, Player> players) {
        this.game = game;
        this.players = players;
    }

    @Override
    public void triggerEvent(String executorName) {
        new SurpriseParty().executeEvent(this, executorName, this.game.getGameState());
    }

}
