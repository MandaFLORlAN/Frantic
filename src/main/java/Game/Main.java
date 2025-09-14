package Game;

import Connector.*;
import ConsolePlayers.HumanPlayer;
import ConsolePlayers.Player;
import ConsolePlayers.RandomBot;
import Statistics.StatisticsHandler;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Player> players = new ArrayList<>();
        Connector connector = new ConsoleConnector();
        StatisticsHandler statisticsHandler = new StatisticsHandler();
        players.add(new RandomBot("Player 0", connector));
        players.add(new RandomBot("Player 1", connector));
        players.add(new RandomBot("Player 2", connector));
        players.add(new HumanPlayer("Human 3", connector));
        connector.startGame(players, statisticsHandler);
    }
}

