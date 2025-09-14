package Cards;

import Connector.Connector;
import ConsolePlayers.Player;
import ConsolePlayers.RandomBot;
import Enums.Color;

public class Gift extends Card implements SpecialCard{
    public Gift(String name, Color color) {
        super("Gift: " + color.name(), 7, color);
    }

    @Override
    public void executeSpecialFunction(Player executor, Connector connector) {

    }
}
