package Players.LogicBots;

import Cards.InterfacesGroundclass.Card;
import Cards.NormalAndCurses.BlackCard;
import Connector.Connector;
import Enums.Color;

import Enums.FantasticOptions;
import Players.RandomBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Players.CardSorter.groupByColor;
import static Players.CardSorter.sortCardsLikeMe;

public class DummyBot extends LogicBot1 {

    public DummyBot(String playerName, Connector connector) {
        super(playerName, connector);
    }

    @Override
    public void playMove() {
        this.cards = sortCardsLikeMe(this.cards);
        for (int i = 1; i <= this.cards.size(); i++) {
            Card card = this.cards.get(cards.size() - i);
            if (card.isPlayable(this.gameState, this.playerName)) {
                if (connector.wantsToPlay(this.playerName, card)) {
                    this.cards.remove(card);
                    connector.executeSpecialFunction(this.playerName, card);
                    return;
                } else {
                    connector.wantsToPlay(this.playerName, null);
                    return;
                }
            }
        }
        connector.wantsToPlay(this.playerName, null);
    }

    @Override
    public boolean wantToUseEffect() {
        return true;//TODO as soon as Special Favours in game
    }

    @Override
    public String fantasticWish() {
        for (Card card : cards) {
            if (card instanceof BlackCard) {
                return FantasticOptions.values()[card.getNumber()-1].toString();
            }
        }
        return this.wishColor();//TODO improve to be mean later
    }

    @Override
    public String wishColor() {
        Map<Color, List<Card>> groupedCards = groupByColor(this.cards);
        int min = this.cards.size();
        Color minColor = null;
        for (Color c : groupedCards.keySet()) {
            if (groupedCards.get(c).size() < min) {
                min = groupedCards.get(c).size();
                minColor = c;
            }
        }
        if (minColor == null) {
            return super.wishColor();
        }
        return minColor.toString();
    }

    @Override
    public List<String> getTargets(String message, int numberOfTargets) {
        List<String> players = new ArrayList<>(this.gameState.getCards().keySet());
        players.remove(this.playerName);
        if (message.equals("Merry christmas")) { //secure no one ends with Christmas
            players.add(playerName);
            return super.getTargets(message, numberOfTargets);
        } else {
            int maxCards = 0;
            String maxPlayer = "";
            for (String player : players) {
                if (gameState.getCards().get(player) > maxCards) {
                    maxCards = gameState.getCards().get(player);
                    maxPlayer = player;
                }
            }
            List<String> targets = new ArrayList<>();
            for (int i=0; i<numberOfTargets; i++) {
                targets.add(maxPlayer);//TODO make more precise to attack multiple persons with low cards
            }
            players.add(playerName);
            return targets;
        }
    }

    @Override
    public List<Card> getCardsToGiveAway(int numberOfCards) {
        if (this.cards.size()<numberOfCards) numberOfCards = this.cards.size();
        List<Card> cardsToGiveAway = new ArrayList<>();
        for (int i=0; i<numberOfCards; i++) {
            cardsToGiveAway.add(this.cards.get(this.cards.size() - i - 1));
        }
        return cardsToGiveAway;
    }

}
