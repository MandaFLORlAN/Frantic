package EventTests;

import Connector.TestConnector;
import Events.Expansion;
import Players.Player;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpansionTest {
    @Test
    public void testEventExecution1() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        connector.triggerEvent("threeCards", new Expansion());

        List<String> players = connector.getAllPlayerNames();
        assertEquals(4, threeCards.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS+2, hasAll.getCards().size());
        assertEquals(8, fiveCards.getCards().size());
        assertEquals(14, tenCards.getCards().size());
    }

    @Test
    public void testEventExecution2() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        connector.triggerEvent("fiveCards", new Expansion());


        assertEquals(6, threeCards.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS+4, hasAll.getCards().size());
        assertEquals(6, fiveCards.getCards().size());
        assertEquals(12, tenCards.getCards().size());
    }
}
