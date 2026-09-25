package Cards.NormalAndCurses;

import Cards.InterfacesGroundclass.Card;
import Enums.Color;

public abstract class BasicCurse extends Card {
    public BasicCurse(String name, int number, int value, Color color) {
        super(name, number, value, color);
    }
}
