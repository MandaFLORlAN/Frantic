package Cards.InterfacesGroundclass;

import Connector.Connector;
import Enums.Color;
import Game.GameState;

public abstract class BasicWishcard extends Card implements WishCard{
    public BasicWishcard(String name, int number, Color color) {
        super(name, number, color);
    }

    protected void ExecuteWish(String executorName, Connector connector) {
        Color color = connector.getPlayerColorWish(executorName);
        connector.wishUpdate(executorName, color);
    }

    protected boolean wantExecute(String executorName, Connector connector) {
        return connector.checkIfPlayerWantEffect(executorName);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean isPlayable(GameState gs) {
        return true;
    }
}
