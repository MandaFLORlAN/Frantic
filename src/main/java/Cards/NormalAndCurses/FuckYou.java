package Cards.NormalAndCurses;

import Cards.InterfacesGroundclass.Card;
import Game.GameState;


public class FuckYou extends Card {
    public FuckYou() {
        super("Fuck You", 0, null);
    }

    @Override
    public boolean isPlayable(GameState gs, String executor) {
        return gs.getCards().get(executor).equals(10);
    }

}
