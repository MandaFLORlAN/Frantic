package Cards.ColloredSpecial;

import Cards.InterfacesGroundclass.Card;
import Cards.InterfacesGroundclass.SpecialCard;
import Connector.Connector;
import Enums.Color;
import Game.GameState;


public abstract class BaseColoredSpecial extends Card implements SpecialCard {
    public BaseColoredSpecial(String name, int number, Color color) {
        super(name, number, color);
    }

    @Override
    public boolean isPlayable(GameState gs) {
        return gs.getPlayableColor() == this.color || gs.getLastCardName().split(": ")[0].equals(this.name.split(": ")[0]);
    }
}
