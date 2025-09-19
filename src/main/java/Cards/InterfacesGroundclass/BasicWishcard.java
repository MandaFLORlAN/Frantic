package Cards.InterfacesGroundclass;

import Connector.Connector;
import Enums.Color;

public class BasicWishcard extends Card implements WishCard{
    public BasicWishcard(String name, int number, Color color) {
        super(name, number, color);
    }

    protected void ExecuteWish(String executorName, Connector connector) {
        connector.getPlayerColorWish(executorName);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
