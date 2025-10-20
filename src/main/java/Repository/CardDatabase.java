package Repository;

import Cards.ColloredSpecial.*;
import Cards.InterfacesGroundclass.Card;
import Cards.NormalAndCurses.BlackCard;
import Cards.NormalAndCurses.FuckYou;
import Cards.NormalAndCurses.RegularCard;
import Cards.Wishcards.*;
import Enums.Color;
import Events.*;
import Events.Basegame.*;


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
        allCards.addAll(createCurses());
        return allCards;
    }

    public static List<Card> getAllCardsOnce() {
        List<Card> allCards = getAllCards();
        List<Card> allCardsOnce = new ArrayList<>();
        for (Card card : allCards) {
            if (!allCardsOnce.contains(card)) {
                allCardsOnce.add(card);
            }
        }
        return allCardsOnce;
    }

    public static int UNIQUE_CARDS = getAllCardsOnce().size();

    public static List<Event> getAllEvents() {
        List<Event> allEvents = new ArrayList<>();
        allEvents.add(new SurpriseParty());
        allEvents.add(new Charity());
        allEvents.add(new RobinHood());
        allEvents.add(new Expansion());
        allEvents.add(new Recession());
        allEvents.add(new Earthquake());
        allEvents.add(new Communism());
        allEvents.add(new MexicanStandoff());
        allEvents.add(new Tornado());
        allEvents.add(new MerryChristmas());
        allEvents.add(new MatingSeason());
        allEvents.add(new ThirdTimeLucky());
        allEvents.add(new Market());
        return allEvents;
    }

    public static List<Card> getShuffledCards() {
        List<Card> allCards = getAllCards();
        Collections.shuffle(allCards);
        return allCards;
    }

    public static List<Event> getShuffledEvents() {
        List<Event> shuffledEvents = new ArrayList<>();
        List<Event> allEvents = getAllEvents();
        Random rand = new Random();
        while (allEvents.size() > 1) {
            shuffledEvents.add(allEvents.remove(rand.nextInt(allEvents.size() - 1)));
        }
        shuffledEvents.add(allEvents.getFirst());
        return shuffledEvents;
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
        for (int r = 0; r < ratio("Counterattack"); r++) {
            coloredSpecialCards.add(new Counterattack());
        }
        for (int r = 0; r < ratio("NiceTry"); r++) {
            coloredSpecialCards.add(new NiceTry());
        }
        return coloredSpecialCards;
    }

    public static List<Card> createColoredSpecialCards() {
        List<Card> coloredSpecialCards = new ArrayList<>();
        for (Color color: Color.values()) {
            for (int i = 0; i< ratio("Gift"); i++) {
                coloredSpecialCards.add(new Gift(color));
            }
            for (int i = 0; i< ratio("Exchange"); i++) {
                coloredSpecialCards.add(new Exchange(color));
            }
            for (int i = 0; i< ratio("SecondChance"); i++) {
                coloredSpecialCards.add(new SecondChance(color));
            }
            for (int i = 0; i< ratio("Skip"); i++) {
                coloredSpecialCards.add(new Skip(color));
            }
            for (int i = 0; i< ratio("TROUBLEMAKER"); i++) {
                coloredSpecialCards.add(new TROUBLEMAKER(color));
            }
        }
        return coloredSpecialCards;
    }

    public static List<Card> createCurses() {
        List<Card> curses = new ArrayList<>();
        for (int r = 0; r < ratio("FuckYou"); r++) {
            curses.add(new FuckYou());
        }
        return curses;
    }
}
