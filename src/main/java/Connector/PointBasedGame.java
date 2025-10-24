package Connector;

import Cards.InterfacesGroundclass.Card;
import Players.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointBasedGame extends BaseConnector {
    private Map<Player, Integer> playerPoints;
    private int losingPoints = 0;

    public PointBasedGame(int losingPoints) {
        this.losingPoints = losingPoints;
    }

    @Override
    public void startGame(List<Player> players) {
        playerPoints = new HashMap<>();
        for (Player player : players) {
            playerPoints.put(player, 0);
        }
        super.startGame(players);
    }

    @Override
    public void winners(List<String> winnerNames) {
        int pointsThisRound;
        for (Player player : super.players.values()) {
            pointsThisRound = 0;
            for (Card card : player.getCards()) {
                pointsThisRound += card.getValue();
            }
            playerPoints.put(player, pointsThisRound);
        }
        boolean gameOver = false;
        int minimumPoints = Integer.MAX_VALUE;
        List<Player> winners = new ArrayList<>();
        for (Player player : this.playerPoints.keySet()) {
            if (playerPoints.get(player) < minimumPoints) {
                minimumPoints = playerPoints.get(player);
                winners.clear();
                winners.add(player);
            } else if (playerPoints.get(player) == minimumPoints) {
                winners.add(player);
            }
            if (playerPoints.get(player) >= this.losingPoints) {
                gameOver = true;
            }
        }
        if (gameOver) {
            super.winners(winners.stream().map(Player::getPlayerName).toList());
        }
    }
}
