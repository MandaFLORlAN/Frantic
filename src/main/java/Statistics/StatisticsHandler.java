package Statistics;

import Cards.Card;
import ConsolePlayers.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsHandler {
    private List<GameEndStats> games;
    private int currentGameMoves;
    private Map<String, Integer> cardsRemaining;
    private int maxEndCards = 0;

    public StatisticsHandler() {
        this.games = new ArrayList<>();
        this.currentGameMoves = 0;
    }

    public void endGame(List<Player> winners, Map<String,List<Card>> remainingCards ) {
        GameEndStats endStats = new GameEndStats(winners, this.currentGameMoves, null);
        this.currentGameMoves = 0;
        for(List<Card> cards : remainingCards.values()){
            if (cards.size() > maxEndCards) {
                maxEndCards = cards.size();
            }
        }
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
        System.out.println("max end hand:" + this.maxEndCards);
    }
}
