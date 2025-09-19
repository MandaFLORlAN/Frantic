package Cards.NormalCurses;

import Cards.InterfacesGroundclass.Card;
import Game.GameState;

public class BlackCard extends Card {
    public BlackCard(int number) {
        super("Black" + ": " + number, number, null);
    }

    @Override
    public boolean isPlayable(GameState gs) {
        return gs.getPlayableNumber() == this.getNumber();
    }
}

