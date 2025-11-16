package Game;

import Connector.*;
import Players.HumanPlayer;
import Players.Player;
import Players.RandomBot;

import java.util.ArrayList;
import java.util.List;

public class MainPoints {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        Connector connector = new PointBasedConnector();
        players.add(new RandomBot("Player 0", connector));
        players.add(new RandomBot("Player 1", connector));
        players.add(new RandomBot("Player 2", connector));
        players.add(new HumanPlayer("Human 3", connector));
        connector.startGame(players);
    }
}
