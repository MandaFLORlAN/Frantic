package Cards.Wishcards;

import Cards.InterfacesGroundclass.BasicWishcard;
import Cards.InterfacesGroundclass.Card;
import Cards.InterfacesGroundclass.SpecialCard;
import Cards.InterfacesGroundclass.WishCard;
import Connector.Connector;
import Enums.Color;
import Enums.FantasticOptions;
import Game.GameState;

public class Fantastic extends BasicWishcard implements SpecialCard {

    public Fantastic() {
        super("Fantastic", 0, null);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        FantasticOptions wish = connector.getPlayerFantasticWish(executorName);
        connector.wishUpdate(executorName, wish);
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
