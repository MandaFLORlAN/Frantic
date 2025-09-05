package Statistics;

import ConsolePlayers.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsHandler {
    private List<GameEndStats> games;
    private int currentGameMoves;

    public StatisticsHandler() {
        this.games = new ArrayList<>();
        this.currentGameMoves = 0;
    }

    public void addMove(String move) {
        currentGameMoves++;
    }

    public void endGame(List<Player> winners) {
        GameEndStats endStats = new GameEndStats(winners, this.currentGameMoves, null);
        this.currentGameMoves = 0;
        this.games.add(endStats);
    }

    public void printStatistics() {
        Map<Player, Integer> players = new HashMap<>();
        int allMoves = 0;
        for (GameEndStats game : this.games) {
            allMoves += game.getNumberOfMoves();
            for (Player winner: game.getWinners()){
                if (!players.containsKey(winner)){
                    players.put(winner, 1);
                }else {
                    players.put(winner, players.get(winner) + 1);
                }
            }
        }
        for (Player winner: players.keySet()) {
            System.out.println(winner + ": " + players.get(winner));
        }
        System.out.println("average moves:" + allMoves/this.games.size());
    }
}
