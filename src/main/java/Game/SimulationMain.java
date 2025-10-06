package Game;

import Connector.SimmulationConnector;
import Players.*;

import Players.LogicBots.LogicBot1RandomMove;
import Statistics.StatisticsHandler;

import java.util.ArrayList;
import java.util.List;

public class SimulationMain {
    public static void main(String[] args) throws InterruptedException {
        List<Player> players = new ArrayList<>();
        SimmulationConnector connector = new SimmulationConnector();
        StatisticsHandler statisticsHandler = new StatisticsHandler();
        players.add(new RandomBot("Player 0", connector));
        players.add(new RandomBot("Player 1", connector));
        players.add(new RandomBot("Player 2", connector));
        players.add(new LogicBot1RandomMove("Logic Bot1RandomMove", connector));
        connector.startStatisticGame(players, statisticsHandler);
        statisticsHandler.printStatistics();
    }
}
