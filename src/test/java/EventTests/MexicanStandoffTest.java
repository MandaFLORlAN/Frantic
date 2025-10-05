package EventTests;

import Connector.TestConnector;
import Events.Basegame.MexicanStandoff;
import Players.Player;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MexicanStandoffTest {
    @Test
    public void testEventExecution() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        connector.triggerEvent("threeCards", new MexicanStandoff());

        assertEquals(3, threeCards.getCards().size());
        assertEquals(3, hasAll.getCards().size());
        assertEquals(3, fiveCards.getCards().size());
        assertEquals(3, tenCards.getCards().size());
    }
}
