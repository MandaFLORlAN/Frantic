package Players;

import Cards.ColloredSpecial.Exchange;
import Cards.ColloredSpecial.Gift;
import Cards.InterfacesGroundclass.Card;
import Cards.InterfacesGroundclass.SpecialCard;
import Cards.NormalAndCurses.BasicCurse;
import Cards.NormalAndCurses.BlackCard;
import Cards.NormalAndCurses.RegularCard;
import Cards.Wishcards.Counterattack;
import Cards.Wishcards.NiceTry;
import Enums.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardSorter {

    public static List<Card> sortCardsLikeMe(List<Card> cards) {
        List<Card> sortedCards = new ArrayList<>();

        List<Card> blackCards = new ArrayList<>();
        List<Card> specialCards = new ArrayList<>();
        List<Card> regularCards = new ArrayList<>();

        for (Card card : cards) {
            if (card instanceof BlackCard) {
                blackCards.add(card);
            }
            if (card instanceof SpecialCard && !(card instanceof  BlackCard)) {
                specialCards.add(card);
            }
        }
        for (Card card : blackCards) {
            cards.remove(card);
        }
        for (Card card : specialCards) {
            cards.remove(card);
        }
        blackCards = sortByNumber(blackCards);
        specialCards = sortByColor(specialCards);
        regularCards = sortByColor(cards);

        sortedCards.addAll(blackCards);
        sortedCards.addAll(regularCards);
        sortedCards.addAll(specialCards);
        return sortedCards;
    }

    public static Map<String, List<Card>> sortCardsInPriorityCategory(List<Card> cards) {
        Map<String, List<Card>> sortedCards = new HashMap<>();
        List<Card> terribleCards = new ArrayList<>();
        List<Card> giftCards = new ArrayList<>();
        List<Card> regularCards = new ArrayList<>();
        List<Card> specialCards = new ArrayList<>();
        List<Card> neverPlayCards = new ArrayList<>();

        for (Card card : cards) {
            if (card instanceof BasicCurse) {
                terribleCards.addFirst(card);
            } else if (card instanceof BlackCard) {
                terribleCards.add(card);
            } else if (card instanceof Exchange || card instanceof Gift) {
                giftCards.add(card);
            } else if (card instanceof RegularCard) {
                regularCards.add(card);
            } else if (card instanceof NiceTry || card instanceof Counterattack) {
                neverPlayCards.add(card);
            } else if (card instanceof SpecialCard) {
                specialCards.add(card);
            }
        }
        //TODO Later for LogicBot2
        sortedCards.put("terribleCards", terribleCards);
        sortedCards.put("giftCards", giftCards);
        sortedCards.put("regularCards", regularCards);
        sortedCards.put("specialCards", specialCards);
        sortedCards.put("neverPlayCards", neverPlayCards);
        return sortedCards;
    }

    public static List<Card> sortByNumber(List<Card> cardsToSort) {
        List<Card> sortedCards = new ArrayList<>();
        int currentSelector = 1;
        while (!cardsToSort.isEmpty()) {
            List<Card> newFoundCards = new ArrayList<>();
            for (Card card : cardsToSort) {
                if (card.getNumber() == currentSelector) {
                    sortedCards.add(card);
                    newFoundCards.add(card);
                }
            }
            for (Card card : newFoundCards) {
                cardsToSort.remove(card);
            }
            currentSelector++;
            if (currentSelector > 10) {
                sortedCards.addAll(cardsToSort);
                break;
            }
        }
        return sortedCards;
    }

    public static List<Card> sortByColor(List<Card> cardsToSort) {
        List<Card> sortedCards = new ArrayList<>();
        Map<Color, List<Card>> groupedCards = groupByColor(cardsToSort);
        for (Color color : Color.values()) {
            List<Card> cardsOfColor = sortByNumber(groupedCards.get(color));
            sortedCards.addAll(cardsOfColor);
        }
        sortedCards.addAll(groupedCards.get(null));
        return sortedCards;
    }

    public static Map<Color, List<Card>> groupByColor(List<Card> cards) {
        Map<Color, List<Card>> groupedCards = new HashMap<>();
        for (Color color : Color.values()) {
            groupedCards.put(color, new ArrayList<>());
        }
        groupedCards.put(null, new ArrayList<>());
        for (Card card : cards) {
            groupedCards.get(card.getColor()).add(card);
        }
        return groupedCards;
    }
}
