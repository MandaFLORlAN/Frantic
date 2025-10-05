package EventTests;

import Cards.InterfacesGroundclass.Card;
import Connector.TestConnector;
import Events.Basegame.MerryChristmas;
import Players.Player;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MerryChristmasTest {
    @Test
    public void TestEventExecution() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");


        int part1 = (int) (double) CardDatabase.UNIQUE_CARDS / 3;
        int part2 = (int) (double) CardDatabase.UNIQUE_CARDS / 3;
        int part3 = (int) (double) CardDatabase.UNIQUE_CARDS / 3;
        if (CardDatabase.UNIQUE_CARDS % 3 == 1) part1++;
        if (CardDatabase.UNIQUE_CARDS % 3 == 2) {
            part1++;
            part2++;
        }

        int numOfCardsInGame = CardDatabase.UNIQUE_CARDS + 3 + 5 + 10;

        List<Card> allCardsBevor = new ArrayList<>();
        allCardsBevor.addAll(connector.getAllCardsOfPlayer("threeCards"));
        allCardsBevor.addAll(connector.getAllCardsOfPlayer("hasAll"));
        allCardsBevor.addAll(connector.getAllCardsOfPlayer("fiveCards"));
        allCardsBevor.addAll(connector.getAllCardsOfPlayer("tenCards"));

        connector.triggerEvent("hasAll", new MerryChristmas());

        assertEquals(numOfCardsInGame, threeCards.getCards().size()
            + hasAll.getCards().size() + fiveCards.getCards().size() + tenCards.getCards().size());

        assertEquals(part1 + 2 + 4, threeCards.getCards().size());
        assertEquals(1 + 2 + 3, hasAll.getCards().size());
        assertEquals(1 + part2 + 3, fiveCards.getCards().size());
        assertEquals(1 + part3 + 1, tenCards.getCards().size());

        List<Card> allCardsAfter = new ArrayList<>();
        allCardsAfter.addAll(connector.getAllCardsOfPlayer("threeCards"));
        allCardsAfter.addAll(connector.getAllCardsOfPlayer("hasAll"));
        allCardsAfter.addAll(connector.getAllCardsOfPlayer("fiveCards"));
        allCardsAfter.addAll(connector.getAllCardsOfPlayer("tenCards"));

        assertTrue(allCardsBevor.containsAll(allCardsAfter));
        assertTrue(allCardsAfter.containsAll(allCardsBevor));
    }
}
