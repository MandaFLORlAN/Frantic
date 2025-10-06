package Players.LogicBots;

import Connector.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicBot1RandomTargets extends LogicBot1{

    public LogicBot1RandomTargets(String playerName, Connector connector) {
        super(playerName, connector);
    }

    @Override
    public List<String> getTargets(String message, int numberOfTargets) {
        List<String> targets = new ArrayList<>();
        List<String> players = new ArrayList<>(this.gameState.getCards().keySet());
        players.remove(this.playerName);
        Random r = new Random();
        for (int i = 0; i< numberOfTargets; i++) {
            targets.add(players.get(r.nextInt(players.size())));
        }
        players.add(playerName);
        return targets;
    }

}
