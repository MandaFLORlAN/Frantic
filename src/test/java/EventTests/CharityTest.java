package EventTests;

import Connector.TestConnector;
import Events.Basegame.Charity;
import Players.TestHelpingBot;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharityTest {

    @Test
    public void testEventExecution() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");
        TestHelpingBot threeCards = (TestHelpingBot) connector.getPlayers().get("threeCards");
        TestHelpingBot fiveCards = (TestHelpingBot) connector.getPlayers().get("fiveCards");
        TestHelpingBot tenCards = (TestHelpingBot) connector.getPlayers().get("tenCards");

        connector.triggerEvent("tenCards",new Charity());
        assertEquals(CardDatabase.UNIQUE_CARDS-3, hasAll.getCards().size());
        assertEquals(4, threeCards.getCards().size());
        assertEquals(6, fiveCards.getCards().size());
        assertEquals(11, tenCards.getCards().size());

        connector.triggerEvent("hasAll",new Charity());
        assertEquals(CardDatabase.UNIQUE_CARDS-6, hasAll.getCards().size());
        assertEquals(5, threeCards.getCards().size());
        assertEquals(7, fiveCards.getCards().size());
        assertEquals(12, tenCards.getCards().size());
    }
}
