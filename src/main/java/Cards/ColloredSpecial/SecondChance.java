package Cards.ColloredSpecial;


import Cards.InterfacesGroundclass.Card;
import Enums.Color;
import Game.GameState;

public class SecondChance extends Card {

    public SecondChance( Color color) {
        super("2nd Chance: " + color, 0, color);
    }

    @Override
    public boolean isPlayable(GameState gs) {
        return gs.getPlayableColor() == this.color || gs.getLastCardName().startsWith("2nd Chance: ");
    }
}
