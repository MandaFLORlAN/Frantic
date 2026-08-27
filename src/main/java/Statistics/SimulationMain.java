package Statistics;

import Connector.SimmulationConnector;
import GeneticAlgorythem.CardGenetic;
import GeneticAlgorythem.GeneticBot;
import Players.*;

import Players.Bots.*;

import java.util.ArrayList;
import java.util.List;

public class SimulationMain {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        SimmulationConnector connector = new SimmulationConnector();
        StatisticsHandler statisticsHandler = new StatisticsHandler();
        players.add(new GeneticBot("Gen1", connector, CardGenetic.getRandomeCardGenetic()));
        players.add(new GeneticBot("Gen2", connector, CardGenetic.getRandomeCardGenetic()));
        players.add(new GeneticBot("Gen3", connector, CardGenetic.getRandomeCardGenetic()));
        players.add(new GeneticBot("Gen4", connector, CardGenetic.getRandomeCardGenetic()));
        players.add(new GeneticBot("Gen5", connector, CardGenetic.getRandomeCardGenetic()));
        players.add(new GeneticBot("Gen6", connector, CardGenetic.getRandomeCardGenetic()));
        players.add(new GeneticBot("Gen7", connector, CardGenetic.getRandomeCardGenetic()));
        players.add(new GeneticBot("Gen8", connector, CardGenetic.getRandomeCardGenetic()));
        players.add(new GeneticBot("Gen9", connector, CardGenetic.getRandomeCardGenetic()));
        players.add(new GeneticBot("Gen0", connector, CardGenetic.getRandomeCardGenetic()));


        connector.startStatisticGame(players, statisticsHandler);
        statisticsHandler.printStatistics();//134,516 ->
    }
}
