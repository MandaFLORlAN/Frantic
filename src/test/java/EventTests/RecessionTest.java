package EventTests;

import Connector.TestConnector;
import Events.Expansion;
import Events.Recession;
import Players.Player;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecessionTest {
    @Test
    public void testEventExecution1() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        connector.triggerEvent("threeCards", new Recession());

        List<String> players = connector.getAllPlayerNames();
        assertEquals(2, threeCards.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS-2, hasAll.getCards().size());
        assertEquals(2, fiveCards.getCards().size());
        assertEquals(6, tenCards.getCards().size());
    }

    @Test
    public void testEventExecution2() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        connector.triggerEvent("tenCards", new Recession());


        assertEquals(1, threeCards.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS-3, hasAll.getCards().size());
        assertEquals(1, fiveCards.getCards().size());
        assertEquals(9, tenCards.getCards().size());
    }
}
