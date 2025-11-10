package Statistics;

import Connector.PointBasedConnector;
import Players.LogicBots.*;
import Players.Player;

import java.util.ArrayList;
import java.util.List;

public class PointSimulationMain {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        PointBasedConnector connector = new PointBasedConnector();
        players.add(new LogicBot1("LogicBot1", connector));
        players.add(new LogicBot1RandomGiveAways("LogicBot1RandomGiveAways", connector));
        players.add(new LogicBot1RandomMove("LogicBot1RandomMove", connector));
        players.add(new LogicBot1RandomTargets("LogicBot1RandomTargets", connector));
        players.add(new LogicBot1RandomWishes("LogicBot1RandomWishes", connector));
        connector.startGame(players);
    }
}
