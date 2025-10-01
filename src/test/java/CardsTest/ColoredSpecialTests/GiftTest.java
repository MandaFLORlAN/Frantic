package CardsTest.ColoredSpecialTests;

import Cards.ColloredSpecial.Exchange;
import Cards.ColloredSpecial.Gift;
import Cards.ColloredSpecial.TROUBLEMAKER;
import Cards.NormalAndCurses.RegularCard;
import Connector.TestConnector;
import Enums.Color;
import Players.TestHelpingBot;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GiftTest {

    @Test
    public void constructorTest() {
        assertEquals(Color.RED, new Gift(Color.RED).getColor());
        assertEquals(Color.BLUE, new Gift(Color.BLUE).getColor());
        assertEquals("Gift: RED", new Gift(Color.RED).getName());
        assertEquals("Gift: BLUE", new Gift(Color.BLUE).getName());
        assertEquals(0, new Gift(Color.RED).getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");
        TestHelpingBot threeCards = (TestHelpingBot) connector.getPlayers().get("threeCards");

        assertTrue(hasAll.getCards().contains(new RegularCard(1, Color.BLUE)));
        assertTrue(hasAll.getCards().contains(new RegularCard(2, Color.BLUE)));
        assertFalse(threeCards.getCards().contains(new RegularCard(1, Color.BLUE)));
        assertFalse(threeCards.getCards().contains(new RegularCard(2, Color.BLUE)));

        hasAll.play(new Gift(Color.RED));

        assertEquals(88, hasAll.getCards().size());
        assertEquals(5, threeCards.getCards().size());

        assertFalse(hasAll.getCards().contains(new RegularCard(1, Color.BLUE)));
        assertFalse(hasAll.getCards().contains(new RegularCard(2, Color.BLUE)));
        assertTrue(threeCards.getCards().contains(new RegularCard(1, Color.BLUE)));
        assertTrue(threeCards.getCards().contains(new RegularCard(2, Color.BLUE)));
    }
}
