package GeneticAlgorythem;

import Connector.SimmulationConnector;
import Players.Bots.LogicBot1;
import Players.Player;
import Players.RandomBot;
import Statistics.StatisticsHandler;

import java.util.ArrayList;
import java.util.List;

public class GeneticMain {
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
        players.add(new RandomBot("Gen9", connector));
        players.add(new LogicBot1("Logic Bot", connector));


        connector.startStatisticGame(players, statisticsHandler);
        statisticsHandler.printStatistics();//134,516 ->
        for (Player player : statisticsHandler.getRanking()) {
            System.out.println(player.getPlayerName());
        }
    }
}
