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

public class LogicBot1 extends RandomBot {
    private Card cardInPlay;


    public LogicBot1(String playerName, Connector connector) {
        super(playerName, connector);
    }

    @Override
    public void playMove() {
        this.cards = sortCardsLikeMe(this.cards);
        for (Card card : cards) {
            if (card.isPlayable(this.gameState, this.playerName)) {
                if (connector.wantsToPlay(this.playerName, card.toString())) {
                    this.cards.remove(card);
                    this.cardInPlay = card;
                    connector.executeSpecialFunction(this.playerName, card.toString());
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
        int max = 0;
        Color maxColor = null;
        for (Color c : groupedCards.keySet()) {
            if (groupedCards.get(c).size() > max) {
                max = groupedCards.get(c).size();
                maxColor = c;
            }
        }
        if (maxColor == null) {
            return super.wishColor();
        }
        return maxColor.toString();
    }

    @Override
    public List<String> getTargets(String message, int numberOfTargets) {
        List<String> players = new ArrayList<>(this.gameState.getCards().keySet());
        players.remove(this.playerName);
        if (message.equals("Merry christmas")) { //secure no one ends with Christmas
            players.add(playerName);
            return super.getTargets(message, numberOfTargets);
        } else {
            int minCards = Integer.MAX_VALUE;
            String minPlayer = "";
            for (String player : players) {
                if (gameState.getCards().get(player) < minCards) {
                    minCards = gameState.getCards().get(player);
                    minPlayer = player;
                }
            }
            List<String> targets = new ArrayList<>();
            for (int i=0; i<numberOfTargets; i++) {
                targets.add(minPlayer);//TODO make more precise to attack multiple persons with low cards
            }
            players.add(playerName);
            return targets;
        }
    }

    @Override
    public List<String> getCardsToGiveAway(int numberOfCards) {
        if (this.cards.size()<numberOfCards) numberOfCards = this.cards.size();
        List<String> cardsToGiveAway = new ArrayList<>();
        for (int i=0; i<numberOfCards; i++) {
            cardsToGiveAway.add(this.cards.get(i).toString());
        }
        return cardsToGiveAway;
    }

}
