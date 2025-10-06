package Players;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestHelpingBot extends RandomBot {

    public TestHelpingBot(String playerName, Connector connector) {
        super(playerName, connector);
    }

    public boolean play(Card card) {
        if(connector.wantsToPlay(this.getPlayerName(), card.getName())) {
            this.cards.remove(card);
            connector.executeSpecialFunction(this.playerName, card.toString());
            return true;
        }
        return false;
    }

    @Override
    public String fantasticWish() {
        return "SEVEN";
    }

    @Override
    public String wishColor() {
        return "BLUE";
    }

    @Override
    public List<String> getTargets(String message, int numberOfTargets) {
        List<String> targets = new ArrayList<>();
        List<String> players = new ArrayList<>(this.gameState.getCards().keySet());
        int i = 0;
        while (targets.size() < numberOfTargets) {
            String target = players.get(i%players.size());
            if (!Objects.equals(target, this.playerName)) targets.add(target);
            i++;
        }
        return targets;
    }

    @Override
    public List<String> getCardsToGiveAway(int numberOfCards) {
        List<String> cardsToGiveAway = new ArrayList<>();
        if (this.cards.size()<numberOfCards) numberOfCards = this.cards.size();
        for (int i = 0; i < numberOfCards; i++) {
            cardsToGiveAway.add(this.cards.get(i).toString());
        }
        return cardsToGiveAway;
    }

    @Override
    public String drawRandomCard() {
        if (this.cards.isEmpty()) {
            return null;
        }
        return this.cards.getFirst().toString();
    }

}
