package Cards.NormalAndCurses;

import Cards.InterfacesGroundclass.Card;
import Enums.Color;
import Game.GameState;

public class RegularCard extends Card {
    public RegularCard(int number, Color color) {
        super(color.toString()+": "+ number
            , number, number, color);
    }
    @Override
    public boolean isPlayable(GameState gs, String executor) {
        if (gs.getPlayableNumber() == this.number) return true;
        if (gs.getPlayableColor() == this.color) return true;
        return false;
    }

}
