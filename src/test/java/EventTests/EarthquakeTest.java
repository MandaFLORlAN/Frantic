package EventTests;

import Connector.TestConnector;
import Events.Earthquake;
import Players.Player;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EarthquakeTest {
    @Test
    public void testEventExecution1() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        connector.triggerEvent("threeCards", new Earthquake());

        List<String> players = connector.getAllPlayerNames();
        assertEquals(10, threeCards.getCards().size());
        assertEquals(3, hasAll.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS, fiveCards.getCards().size());
        assertEquals(5, tenCards.getCards().size());
    }

    @Test
    public void testEventExecution2() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        connector.triggerEvent("tenCards", new Earthquake());

        List<String> players = connector.getAllPlayerNames();
        assertEquals(10, threeCards.getCards().size());
        assertEquals(3, hasAll.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS, fiveCards.getCards().size());
        assertEquals(5, tenCards.getCards().size());
    }
}
