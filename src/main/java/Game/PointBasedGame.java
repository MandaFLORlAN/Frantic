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

    public void removePointsFromPlayer(String player, int points) {
        playerPoints.put(player, playerPoints.get(player) - points);
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
            if (addAllPointsInHand()) pointBasedConnector.pointWinners(playerPoints);
            else
                this.resetGame();
        }
    }

    private boolean addAllPointsInHand() {
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
        return oneOverPointLimit;
    }

    public void timeBomb() {
        for (int i = 0; i < 3 * players.size(); i++) {
            String nextPlayer = players.keySet().stream().toList().get((movesPlayed + startOffset) % players.size());
            if (playersToSkip.contains(nextPlayer)) {
                playersToSkip.remove(nextPlayer);
                connector.tellAllPlayers(nextPlayer + " has been skipped");
                startOffset++;
            } else {
                connector.itsTurn(nextPlayer);
                movesPlayed++;
                for (String player : players.keySet()) {
                    if (players.get(player).isEmpty()) {
                        removePointsFromPlayer(player, 20);
                        for (String p : players.keySet()) {
                            addPointsToPlayer(p, 10);
                        }
                        if (addAllPointsInHand()) pointBasedConnector.pointWinners(playerPoints);
                        else super.resetGame();
                    }
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            if (addAllPointsInHand()) pointBasedConnector.pointWinners(playerPoints);
            else this.resetGame();
        }
    }

    public void finishLine() {
        this.gameOver = true;
        if (addAllPointsInHand()) pointBasedConnector.pointWinners(playerPoints);
        else this.startGame();
    }
}
