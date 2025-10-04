package EventTests;

import Connector.TestConnector;
import Events.Communism;
import Players.Player;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommunismTest {
    @Test
    public void testEventExecution() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        connector.triggerEvent("threeCards", new Communism());

        assertEquals(CardDatabase.UNIQUE_CARDS, threeCards.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS, hasAll.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS, fiveCards.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS, tenCards.getCards().size());
    }
}
