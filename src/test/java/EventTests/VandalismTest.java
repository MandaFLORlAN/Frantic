package EventTests;

import Cards.NormalAndCurses.RegularCard;
import Connector.TestConnector;
import Enums.Color;
import Events.Basegame.Vandalism;
import Players.TestHelpingBot;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VandalismTest {
    @Test
    public void TestEventExecution1() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot threeCards = (TestHelpingBot) connector.getPlayers().get("threeCards");
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");
        TestHelpingBot fiveCards = (TestHelpingBot) connector.getPlayers().get("fiveCards");
        TestHelpingBot tenCards = (TestHelpingBot) connector.getPlayers().get("tenCards");

        hasAll.play(new RegularCard(1,Color.BLUE));
        connector.triggerEvent("threeCards", new Vandalism());

        assertEquals(3, threeCards.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS - 15, hasAll.getCards().size());
        assertEquals(4, fiveCards.getCards().size());
        assertEquals(10, tenCards.getCards().size());
    }

    @Test
    public void TestEventExecution2() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot threeCards = (TestHelpingBot) connector.getPlayers().get("threeCards");
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");
        TestHelpingBot fiveCards = (TestHelpingBot) connector.getPlayers().get("fiveCards");
        TestHelpingBot tenCards = (TestHelpingBot) connector.getPlayers().get("tenCards");

        hasAll.play(new RegularCard(1,Color.GREEN));
        connector.triggerEvent("threeCards", new Vandalism());

        assertEquals(3, threeCards.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS - 15, hasAll.getCards().size());
        assertEquals(3, fiveCards.getCards().size());
        assertEquals(6, tenCards.getCards().size());
    }
}
