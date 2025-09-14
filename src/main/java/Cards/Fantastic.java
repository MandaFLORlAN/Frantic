package Cards;

import Connector.Connector;
import ConsolePlayers.Player;
import ConsolePlayers.RandomBot;
import Enums.Color;
import Enums.FantasticOptions;
import Game.GameState;

public class Fantastic extends Card implements SpecialCard {

    public Fantastic() {
        super("Fantastic", 0, null);
    }

    @Override
    public void executeSpecialFunction(Player executor, Connector connector) {
        FantasticOptions wish = executor.fantasticWish();
        connector.wishUpdate(executor, wish);
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

}
