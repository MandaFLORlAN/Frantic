package Players.Bots;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Enums.Color;
import Enums.FantasticOptions;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AmandaBot extends LogicBot1 {
    Random random = new Random();
    public AmandaBot(String playerName, Connector connector) {
        super(playerName, connector);
    }

    @Override
    public void playMove() {
        if(random.nextBoolean()) {
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
            if (connector.wantsToPlay(this.playerName, card)) {
                this.cards.remove(card);
                connector.executeSpecialFunction(this.playerName, card);
            } else {
                connector.wantsToPlay(this.playerName, null);
            }
        } else {
            super.playMove();
        }
    }

    @Override
    public String fantasticWish() {
        if(random.nextBoolean()) {
            return FantasticOptions.values()[new Random().nextInt(FantasticOptions.values().length)].toString();
        }
        return super.fantasticWish();
    }

    @Override
    public String wishColor() {
        if(random.nextBoolean()) {
            return Color.values()[new Random().nextInt(Color.values().length)].toString();
        }
        return super.wishColor();
    }

    @Override
    public List<String> getTargets(String message, int numberOfTargets) {
        if(random.nextBoolean()) {
            List<String> targets = new ArrayList<>();
            List<String> players = new ArrayList<>(this.gameState.getCards().keySet());
            players.remove(this.playerName);
            Random r = new Random();
            for (int i = 0; i< numberOfTargets; i++) {
                targets.add(players.get(r.nextInt(players.size())));
            }
            players.add(playerName);
            return targets;
        }
        return super.getTargets(message, numberOfTargets);
    }

    @Override
    public List<Card> getCardsToGiveAway(int numberOfCards) {
        if(random.nextBoolean()) {
            List<Card> cardsToGiveAway = new ArrayList<>();
            if (this.cards.size()<numberOfCards) numberOfCards = this.cards.size();
            for (int i = 0; i <  numberOfCards; i++) {
                cardsToGiveAway.add(this.cards.remove(new Random().nextInt(this.cards.size())));
            }
            //cards will be taken in the transfer card method, they are removed to not be picked twice
            for (Card card : cardsToGiveAway) {
                this.cards.add(card);
            }
            return cardsToGiveAway;
        }
        return super.getCardsToGiveAway(numberOfCards);
    }
}
