package Game;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Connector.PointBasedConnector;
import Repository.FranticConfigs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointBasedGame extends Game {
    public Map<String, Integer> playerPoints;
    private int losingPoints = FranticConfigs.NUMBER_OF_POINTS_TO_LOSE;
    private PointBasedConnector pointBasedConnector;

    public PointBasedGame(List<String> names, Connector connector, int startCards) {
        super(names, connector, startCards);
        this.pointBasedConnector = (PointBasedConnector) connector;
        playerPoints = new HashMap<>();
        for (String name : names) {
            playerPoints.put(name, 0);
        }
    }

    public void addPointsToPlayer(String player, int points) {
        playerPoints.put(player, playerPoints.get(player) + points);
    }

    @Override
    public void checkGameOver() {
        boolean onePlayerFinished = false;
        for (String player : players.keySet()) {
            if (players.get(player).isEmpty()) {
                onePlayerFinished = true;
                break;
            }
        }
        if (onePlayerFinished) {
            this.gameOver = true;
            boolean oneOverPointLimit = false;
            for (String player : players.keySet()) {
                int points = playerPoints.get(player);
                for (Card card : players.get(player)) {
                    points += card.getValue();
                }
                if (points > losingPoints) {
                    oneOverPointLimit = true;
                }
                playerPoints.put(player, points);
            }
            if (oneOverPointLimit) {
                pointBasedConnector.pointWinners(playerPoints);
            } else {
                this.startGame();
            }
        }
    }

}
