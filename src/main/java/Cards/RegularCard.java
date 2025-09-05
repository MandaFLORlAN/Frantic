package Cards;

import Enums.Color;
import Game.GameState;

public class RegularCard extends Card {
    public RegularCard(int number, Color color) {
        super(
            color.toString().substring(0, 1).toUpperCase() + color.toString().substring(1)+": "+ number
            , number, color);

    }
    @Override
    public boolean isPlayable(GameState gs) {
        return gs.getPlayableColor() == this.getColor() || gs.getPlayableNumber() == this.getNumber();
    }

}
