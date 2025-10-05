package EventTests;

import Connector.TestConnector;
import Events.Basegame.SurpriseParty;
import Players.TestHelpingBot;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurprisePartyTest {

    @Test
    public void testEventExecution() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");
        TestHelpingBot threeCards = (TestHelpingBot) connector.getPlayers().get("threeCards");
        TestHelpingBot fiveCards = (TestHelpingBot) connector.getPlayers().get("fiveCards");
        TestHelpingBot tenCards = (TestHelpingBot) connector.getPlayers().get("tenCards");

        connector.triggerEvent("tenCards", new SurpriseParty());
        assertEquals(CardDatabase.UNIQUE_CARDS, hasAll.getCards().size());
        assertEquals(5, threeCards.getCards().size());
        assertEquals(4, fiveCards.getCards().size());
        assertEquals(9, tenCards.getCards().size());

        connector.triggerEvent("hasAll", new SurpriseParty());
        assertEquals(CardDatabase.UNIQUE_CARDS, hasAll.getCards().size());
        assertEquals(7, threeCards.getCards().size());
        assertEquals(3, fiveCards.getCards().size());
        assertEquals(8, tenCards.getCards().size());
    }
}
