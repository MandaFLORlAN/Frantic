package CardsTest.ColoredSpecialTests;

import Cards.ColloredSpecial.SecondChance;
import Connector.TestConnector;
import Enums.Color;
import Players.TestHelpingBot;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecondChanceTest {

    @Test
    public void constructorTest() {
        assertEquals(Color.RED, new SecondChance(Color.RED).getColor());
        assertEquals(Color.BLUE, new SecondChance(Color.BLUE).getColor());
        assertEquals("2nd Chance: RED", new SecondChance(Color.RED).getName());
        assertEquals("2nd Chance: BLUE", new SecondChance(Color.BLUE).getName());
        assertEquals(0, new SecondChance(Color.RED).getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");

        hasAll.play(new SecondChance(Color.RED));

        assertEquals(-1, connector.getGame().getStartOffset());
    }
}
