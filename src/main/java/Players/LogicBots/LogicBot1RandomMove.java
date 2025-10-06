package Players.LogicBots;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicBot1RandomMove extends LogicBot1 {
    public LogicBot1RandomMove(String playerName, Connector connector) {
        super(playerName, connector);
    }

    @Override
    public void playMove() {
        List<Card> playableCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.isPlayable(this.gameState, this.playerName)) {
                playableCards.add(card);
            }
        }
        if (playableCards.isEmpty()) {
            connector.wantsToPlay(this.playerName, null);
            return;
        }
        Card card = playableCards.get(new Random().nextInt(playableCards.size()));
        if (connector.wantsToPlay(this.playerName, card.toString())) {
            this.cards.remove(card);
            connector.executeSpecialFunction(this.playerName, card.toString());
        } else {
            connector.wantsToPlay(this.playerName, null);
        }
    }
}
