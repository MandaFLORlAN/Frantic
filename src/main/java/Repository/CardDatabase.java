package Repository;

import Cards.ColloredSpecial.Exchange;
import Cards.ColloredSpecial.Gift;
import Cards.ColloredSpecial.SecondChance;
import Cards.ColloredSpecial.Skip;
import Cards.InterfacesGroundclass.Card;
import Cards.NormalAndCurses.BlackCard;
import Cards.NormalAndCurses.RegularCard;
import Cards.Wishcards.Equality;
import Cards.Wishcards.Fantastic;
import Cards.Wishcards.FantasticFour;
import Enums.Color;


import java.util.*;

import static Repository.FranticConfigs.ratio;

public class CardDatabase {
    public static List<Card> getAllCards() {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(createRegularCards());
        allCards.addAll(createBlackCards());
        allCards.addAll(createFantasticCards());
        allCards.addAll(createColorefullSpecialCards());
        allCards.addAll(createColoredSpecialCards());
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
        for (int r = 0; r < ratio("FantasticFour"); r++) {
            fantasticCards.add(new FantasticFour());
        }
        return fantasticCards;
    }

    public static List<Card> createColorefullSpecialCards() {
        List<Card> coloredSpecialCards = new ArrayList<>();
        for (int r = 0; r < ratio("Equality"); r++) {
            coloredSpecialCards.add(new Equality());
        }
        return coloredSpecialCards;
    }

    public static List<Card> createColoredSpecialCards() {
        List<Card> coloredSpecialCards = new ArrayList<>();
        for (Color color: Color.values()) {
            for (int i = 0; i<= ratio("Gift"); i++) {
                coloredSpecialCards.add(new Gift(color));
            }
            for (int i = 0; i<= ratio("Exchange"); i++) {
                coloredSpecialCards.add(new Exchange(color));
            }
            for (int i = 0; i<= ratio("SecondChance"); i++) {
                coloredSpecialCards.add(new SecondChance(color));
            }
            for (int i = 0; i<= ratio("Skip"); i++) {
                coloredSpecialCards.add(new Skip(color));
            }
        }
        return coloredSpecialCards;
    }
}
