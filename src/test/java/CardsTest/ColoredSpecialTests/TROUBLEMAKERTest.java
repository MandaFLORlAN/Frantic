package CardsTest.ColoredSpecialTests;

import Cards.ColloredSpecial.TROUBLEMAKER;
import Cards.NormalAndCurses.FuckYou;
import Cards.NormalAndCurses.RegularCard;
import Connector.TestConnector;
import Enums.Color;
import Players.TestHelpingBot;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TROUBLEMAKERTest {

    @Test
    public void constructorTest() {
        assertEquals(Color.RED, new TROUBLEMAKER(Color.RED).getColor());
        assertEquals(Color.BLUE, new TROUBLEMAKER(Color.BLUE).getColor());
        assertEquals("TROUBLEMAKER: RED", new TROUBLEMAKER(Color.RED).getName());
        assertEquals("TROUBLEMAKER: BLUE", new TROUBLEMAKER(Color.BLUE).getName());
        assertEquals(0, new TROUBLEMAKER(Color.RED).getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");
        TestHelpingBot threeCards = (TestHelpingBot) connector.getPlayers().get("threeCards");
        TestHelpingBot fiveCards = (TestHelpingBot) connector.getPlayers().get("fiveCards");
        TestHelpingBot tenCards = (TestHelpingBot) connector.getPlayers().get("tenCards");

        hasAll.play(new TROUBLEMAKER(Color.RED)); //should trigger Surprise Party in TestConnector

        assertEquals(5, threeCards.getCards().size());
        assertTrue(threeCards.getCards().contains(new RegularCard(1, Color.BLUE)));
        assertTrue(threeCards.getCards().contains(new RegularCard(2, Color.GREEN)));
        assertTrue(threeCards.getCards().contains(new FuckYou()));
        assertEquals(4, fiveCards.getCards().size());
        assertFalse(fiveCards.getCards().contains(new RegularCard(2, Color.GREEN)));
        assertEquals(9, tenCards.getCards().size());
        assertFalse(tenCards.getCards().contains(new FuckYou()));
        assertEquals(CardDatabase.UNIQUE_CARDS-1, hasAll.getCards().size());
        assertFalse(hasAll.getCards().contains(new RegularCard(1, Color.BLUE)));
    }
}
