package Cards.Wishcards;

import Cards.InterfacesGroundclass.Card;
import Cards.InterfacesGroundclass.SpecialCard;
import Cards.InterfacesGroundclass.WishCard;
import Connector.Connector;
import Enums.Color;
import Enums.FantasticOptions;
import Game.GameState;

public class Fantastic extends Card implements SpecialCard, WishCard {

    public Fantastic() {
        super("Fantastic", 0, null);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        FantasticOptions wish = connector.getPlayerFantasticWish(executorName);
        connector.wishUpdate(executorName, wish);
    }

    @Override
    public boolean isPlayable(GameState gs) {
        return true;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
