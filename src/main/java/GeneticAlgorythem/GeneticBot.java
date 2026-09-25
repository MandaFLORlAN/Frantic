package GeneticAlgorythem;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Players.Bots.LogicBot1;

import java.util.List;

import static Players.CardSorter.sortCardsByGenes;


public class GeneticBot extends LogicBot1 {

    private CardGenetic cardGenetic;

    public GeneticBot(String playerName, Connector connector, CardGenetic cardGenetic) {
        super(playerName, connector);
        this.cardGenetic = cardGenetic;
    }


    @Override
    public void playMove() {
        for (Card card : cards) {
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
    public void addCard(Card card) {
/*        float newValue = 0;
        try {
            newValue = cardGenetic.getGenes().get(card.getName());
        } catch (NullPointerException e) {
            System.out.println("Break");
        }

        *//*
        for (int i = 0; i < this.cards.size(); i++) {
            if (newValue > cardGenetic.getGenes().get(cards.get(i).getName())) {
                this.cards.add(i, card);
                return;
            }
        }*/
        this.cards = sortCardsByGenes(this.cards, this.cardGenetic);
        this.cards.add(card);
    }
}
