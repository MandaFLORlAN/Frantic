package Cards;

import Connector.Connector;
import Enums.Color;

public class Gift extends Card implements SpecialCard{
    public Gift(String name, Color color) {
        super("Gift: " + color.name(), 7, color);
    }


    @Override
    public void executeSpecialFunction(String executorName, Connector connector) {

    }
}
