package Connector;

import Cards.InterfacesGroundclass.Card;
import Game.Game;
import Players.Player;
import Repository.FranticConfigs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointBasedConnector extends BaseConnector {
    public Map<Player, Integer> playerPoints;
    private int losingPoints = 0;

    public PointBasedConnector(int losingPoints) {
        this.losingPoints = losingPoints;
    }

    public void addPointsToPlayer(String playerName, int points) {
        Player player = players.get(playerName);
        playerPoints.put(player, playerPoints.get(player) + points);
    }

    @Override
    public void startGame(List<Player> players) {
        playerPoints = new HashMap<>();
        for (Player player : players) {
            playerPoints.put(player, 0);
        }
        this.playOrder = new ArrayList<>();
        this.players = new HashMap<>();
        for (Player p : players) {
            this.players.put(p.getPlayerName(), p);
            this.playOrder.add(p.getPlayerName());
        }
        this.game = new Game(this.playOrder, this, FranticConfigs.NUMBER_OF_START_CARDS);
        for (Player player : players) {
            player.newRound();
        }
        this.game.startGame();
    }

    @Override
    public void winners(List<String> winnerNames) {
        int pointsThisRound;
        for (Player player : super.players.values()) {
            pointsThisRound = 0;
            for (Card card : player.getCards()) {
                pointsThisRound += card.getValue();
            }
            playerPoints.put(player, playerPoints.get(player) + pointsThisRound);
        }
        boolean gameOver = false;
        int minimumPoints = Integer.MAX_VALUE;
        List<Player> pointWinners = new ArrayList<>();
        for (Player player : this.playerPoints.keySet()) {
            if (playerPoints.get(player) < minimumPoints) {
                minimumPoints = playerPoints.get(player);
                pointWinners.clear();
                pointWinners.add(player);
            } else if (playerPoints.get(player) == minimumPoints) {
                pointWinners.add(player);
            }
            if (playerPoints.get(player) >= this.losingPoints) {
                gameOver = true;
            }
        }
        if (gameOver) {
            super.winners(pointWinners.stream().map(Player::getPlayerName).toList());
        } else {
            for (Player player : super.players.values()) {
                player.newRound();
            }
            game.startGame();
        }
    }
}
