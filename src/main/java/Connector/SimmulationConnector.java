package Connector;

import Cards.InterfacesGroundclass.Card;
import ConsolePlayers.Player;
import Game.Game;
import Repository.FranticConfigs;
import Statistics.StatisticsHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimmulationConnector extends ConsoleConnector {
    private StatisticsHandler statistics;
    public void startStatisticGame(List<Player> players, StatisticsHandler statistics) throws InterruptedException {
        List<String> names = new ArrayList<>();
        this.players = new HashMap<>();
        for (Player p : players) {
            this.players.put(p.getPlayerName(), p);
            names.add(p.getPlayerName());
        }
        this.statistics = statistics;
        for (int i = 0; i < 10000; i++) {
            if (i%100==0) System.out.println(i);
            this.game = new Game(names, this, FranticConfigs.NUMBER_OF_START_CARDS);
            for (Player player : players) {
                player.newRound();
            }
            this.game.startGame();
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
