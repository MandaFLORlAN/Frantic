package Repository;

import Cards.Card;
import Cards.RegularCard;
import Enums.Color;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardDatabase {
    public static List<Card> getAllCards() {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(createRegularCards());

        return allCards;
    }

    public static List<Card> getShuffledCards() {
        List<Card> shuffledCards = new ArrayList<>();
        List<Card> allCards = getAllCards();
        Random rand = new Random();
        while(allCards.size() > 1){
            shuffledCards.add(allCards.remove(rand.nextInt(allCards.size()-1)));
        }
        shuffledCards.add(allCards.getFirst());
        return shuffledCards;
    }

    private static List<Card> createRegularCards() {
        List<Card> regularCards = new ArrayList<>();
        for (Color color : Color.values()) {
            for (int i = 1; i <= 10; i++) {
                regularCards.add(new RegularCard(i, color));
            }
        }
        return regularCards;
    }
}
