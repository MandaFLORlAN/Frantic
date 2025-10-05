package EventTests;

import Connector.TestConnector;
import Events.Basegame.RobinHood;
import Players.Player;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobinHoodTest {
    @Test
    public void testEventExecution() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player hasAll = connector.getPlayers().get("hasAll");
        Player threeCards = connector.getPlayers().get("threeCards");

        connector.triggerEvent("threeCards", new RobinHood());

        assertEquals(CardDatabase.UNIQUE_CARDS, threeCards.getCards().size());
        assertEquals(3, hasAll.getCards().size());

        connector.triggerEvent("tenCards", new RobinHood());

        assertEquals(CardDatabase.UNIQUE_CARDS, hasAll.getCards().size());
        assertEquals(3, threeCards.getCards().size());
    }
}
