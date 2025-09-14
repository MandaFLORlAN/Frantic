package Repository;

import Cards.BlackCard;
import Cards.Card;
import Cards.Fantastic;
import Cards.RegularCard;
import Enums.Color;


import java.util.*;

import static Repository.FranticConfigs.ratio;

public class CardDatabase {
    public static List<Card> getAllCards() {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(createRegularCards());
        allCards.addAll(createBlackCards());
        allCards.addAll(createFantasticCards());
        return allCards;
    }

    public static List<Card> getShuffledCards() {
        List<Card> shuffledCards = new ArrayList<>();
        List<Card> allCards = getAllCards();
        Random rand = new Random();
        while (allCards.size() > 1) {
            shuffledCards.add(allCards.remove(rand.nextInt(allCards.size() - 1)));
        }
        shuffledCards.add(allCards.getFirst());
        return shuffledCards;
    }

    public static List<Card> createRegularCards() {
        List<Card> regularCards = new ArrayList<>();
        for (int r = 0; r < ratio("RegularCard"); r++) {
            for (Color color : Color.values()) {
                for (int i = 1; i <= 10; i++) {
                    regularCards.add(new RegularCard(i, color));
                }
            }
        }
        return regularCards;
    }

    public static List<Card> createBlackCards() {
        List<Card> blackCards = new ArrayList<>();
        for (int r = 0; r < ratio("BlackCard"); r++) {
            for (int i = 1; i <= 10; i++) {
                blackCards.add(new BlackCard(i));
            }
        }
        return blackCards;
    }

    public static List<Card> createFantasticCards() {
        List<Card> fantasticCards = new ArrayList<>();
        for (int r = 0; r < ratio("Fantastic"); r++) {
            fantasticCards.add(new Fantastic());
        }
        return fantasticCards;
    }

}
