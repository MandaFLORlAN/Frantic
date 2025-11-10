package Statistics;

import Connector.SimmulationConnector;
import Players.*;

import Players.LogicBots.*;

import java.util.ArrayList;
import java.util.List;

public class SimulationMain {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        SimmulationConnector connector = new SimmulationConnector();
        StatisticsHandler statisticsHandler = new StatisticsHandler();
        players.add(new LogicBot1("LogicBot1", connector));
        players.add(new LogicBot1RandomGiveAways("LogicBot1RandomGiveAways", connector));
        players.add(new LogicBot1RandomMove("LogicBot1RandomMove", connector));
        players.add(new LogicBot1RandomTargets("LogicBot1RandomTargets", connector));
        players.add(new LogicBot1RandomWishes("LogicBot1RandomWishes", connector));
        connector.startStatisticGame(players, statisticsHandler);
        statisticsHandler.printStatistics();//53192 -> 37374 ->
    }
}
