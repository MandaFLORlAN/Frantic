package CardsTest.ColoredSpecialTests;
import Cards.ColloredSpecial.Exchange;
import Cards.ColloredSpecial.TROUBLEMAKER;
import Cards.NormalAndCurses.RegularCard;
import Cards.Wishcards.Fantastic;
import Connector.TestConnector;
import Enums.Color;
import Players.TestHelpingBot;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeTest {

    @Test
    public void constructorTest() {
        assertEquals(Color.RED, new Exchange(Color.RED).getColor());
        assertEquals(Color.BLUE, new Exchange(Color.BLUE).getColor());
        assertEquals("Exchange: RED", new Exchange(Color.RED).getName());
        assertEquals("Exchange: BLUE", new Exchange(Color.BLUE).getName());
        assertEquals(0, new Exchange(Color.RED).getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");
        TestHelpingBot threeCards = (TestHelpingBot) connector.getPlayers().get("threeCards");

        assertTrue(hasAll.getCards().contains(new RegularCard(1, Color.BLUE)));
        assertTrue(hasAll.getCards().contains(new RegularCard(2, Color.BLUE)));
        assertTrue(threeCards.getCards().contains(new Fantastic()));
        assertTrue(threeCards.getCards().contains(new RegularCard(7, Color.RED)));

        hasAll.play(new Exchange(Color.RED));

        assertEquals(90, hasAll.getCards().size());
        assertEquals(3, threeCards.getCards().size());

        assertEquals(new Fantastic(), hasAll.getCards().get(89));
        assertEquals(new RegularCard(7, Color.RED), hasAll.getCards().get(88));
        assertEquals(new RegularCard(1,Color.BLUE), threeCards.getCards().get(1));
        assertEquals(new RegularCard(2, Color.BLUE), threeCards.getCards().get(2));

        assertFalse(hasAll.getCards().contains(new RegularCard(1, Color.BLUE)));
        assertFalse(hasAll.getCards().contains(new RegularCard(2, Color.BLUE)));
        assertFalse(threeCards.getCards().contains(new TROUBLEMAKER(Color.BLUE)));
        assertFalse(threeCards.getCards().contains(new RegularCard(7, Color.RED)));
    }

}
