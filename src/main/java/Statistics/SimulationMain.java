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
        players.add(new LogicBot1("LogicBot2", connector));
        players.add(new LogicBot1("LogicBot3", connector));
        players.add(new DummyBot("DummyBot1", connector));
        players.add(new DummyBot("DummyBot2", connector));


        connector.startStatisticGame(players, statisticsHandler);
        statisticsHandler.printStatistics();//134,516 ->
    }
}
