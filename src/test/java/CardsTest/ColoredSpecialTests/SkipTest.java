package CardsTest.ColoredSpecialTests;

import Cards.ColloredSpecial.Gift;
import Cards.ColloredSpecial.Skip;
import Cards.NormalAndCurses.RegularCard;
import Connector.TestConnector;
import Enums.Color;
import Players.TestHelpingBot;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkipTest {

    @Test
    public void constructorTest() {
        assertEquals(Color.RED, new Skip(Color.RED).getColor());
        assertEquals(Color.BLUE, new Skip(Color.BLUE).getColor());
        assertEquals("Skip: RED", new Skip(Color.RED).getName());
        assertEquals("Skip: BLUE", new Skip(Color.BLUE).getName());
        assertEquals(0, new Skip(Color.RED).getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");

        hasAll.play(new Skip(Color.RED));

        assertTrue(connector.getGame().getPlayersToSkip().contains("threeCards"));
    }
}
