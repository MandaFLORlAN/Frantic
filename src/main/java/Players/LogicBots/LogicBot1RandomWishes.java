package Players.LogicBots;

import Connector.Connector;
import Enums.Color;
import Enums.FantasticOptions;

import java.util.Random;


public class LogicBot1RandomWishes extends LogicBot1{

    public LogicBot1RandomWishes(String playerName, Connector connector) {
        super(playerName, connector);
    }

    @Override
    public String fantasticWish() {
        return FantasticOptions.values()[new Random().nextInt(FantasticOptions.values().length)].toString();
    }

    @Override
    public String wishColor() {
        return Color.values()[new Random().nextInt(Color.values().length)].toString();
    }

}
