package Cards;

import Connector.Connector;
import Enums.Color;
import Enums.FantasticOptions;
import Game.GameState;

public class Fantastic extends Card implements SpecialCard {

    public Fantastic() {
        super("Fantastic", 0, null);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector) {
        FantasticOptions wish = connector.getPlayerFantasticWish(executorName);
        connector.wishUpdate(executorName, wish);
    }

    @Override
    public boolean isPlayable(GameState gs) {
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        return o instanceof Fantastic;
    }

}
