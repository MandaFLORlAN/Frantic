package Connector;

import Game.PointBasedGame;
import Players.Player;
import Repository.FranticConfigs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointBasedConnector extends BaseConnector {

    public PointBasedGame game;

    public void addPointsToPlayer(String playerName, int points) {
        if (game instanceof PointBasedGame pointBasedGame) {
            pointBasedGame.addPointsToPlayer(playerName, points);
        }
    }

    @Override
    public void startGame(List<Player> players) {
        this.playOrder = new ArrayList<>();
        this.players = new HashMap<>();
        for (Player p : players) {
            this.players.put(p.getPlayerName(), p);
            this.playOrder.add(p.getPlayerName());
        }
        super.game = new PointBasedGame(this.playOrder, this, FranticConfigs.NUMBER_OF_START_CARDS);
        this.game = (PointBasedGame) super.game;
        for (Player player : players) {
            player.clearCards();
        }
        this.game.startGame();
    }

    public void pointWinners(Map<String, Integer> playerPoints) {
        playerPoints.entrySet().stream().sorted(Map.Entry.comparingByValue()).
                forEach(entry ->
                System.out.println(entry.getKey() + ": " + entry.getValue())
        );
    }

    public void finishLine() {
        game.finishLine();
    }

    public void timeBomb() {
        game.timeBomb();
    }
}
