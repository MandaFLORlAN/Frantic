package Connector;

import Cards.InterfacesGroundclass.Card;
import Players.Player;
import Statistics.StatisticsHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimmulationConnector extends BaseConnector {
    private StatisticsHandler statistics;
    public void startStatisticGame(List<Player> players, StatisticsHandler statistics) throws InterruptedException {
        this.statistics = statistics;
        for (int i = 0; i < 10_000; i++) {
            if (i%1_000 == 0) System.out.println(i);
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
}
