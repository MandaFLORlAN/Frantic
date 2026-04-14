package Connector;

import Cards.InterfacesGroundclass.Card;
import Players.Player;
import Statistics.StatisticsHandler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class SimmulationConnector extends BaseConnector {
    private StatisticsHandler statistics;
    private String logs;
    public void startStatisticGame(List<Player> players, StatisticsHandler statistics) {
        this.statistics = statistics;
        LocalDateTime lastTimestamp = LocalDateTime.now();
        LocalDateTime now;
        for (int i = 0; i < 100_000; i++) {

            if (i%100 == 0) {
                System.out.println(i);
                now = LocalDateTime.now();
                Duration duration = Duration.between(lastTimestamp, now);
                System.out.println(duration.getSeconds() + "," + duration.getNano());
                lastTimestamp = LocalDateTime.now();
            }
            super.startGame(players);
        }
    }

    @Override
    public void winners(List<String> winnerNames) {
        List<Player> winners = new ArrayList<>();
        Map<String, List<Card>> endcards = new HashMap<>();
        for (String winnerName : winnerNames) {
            winners.add(this.players.get(winnerName));
        }
        for (Player player : this.players.values()) {
            endcards.put(player.getPlayerName(), player.getCards());
        }
        statistics.endGame(winners, endcards);
    }

    @Override
    public boolean wantsToPlay(String playerName, String cardName) {
        this.statistics.addMove();
        return super.wantsToPlay(playerName, cardName);
    }

    @Override
    public void tellAllPlayers(String message) {
        for (Player player : players.values()) {
            player.updateGameActions(message);
        }
        logs += message + "\n";
    }
}
