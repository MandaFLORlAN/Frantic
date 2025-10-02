package CardsTest.NormalAndCursesTests;

import Cards.NormalAndCurses.FuckYou;
import Connector.TestConnector;
import Players.TestHelpingBot;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FuckYouTest {

    @Test
    public void constructorTest() {
        assertNull(new FuckYou().getColor());
        assertEquals("Fuck You", new FuckYou().getName());
        assertEquals(0, new FuckYou().getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot tenCards = (TestHelpingBot) connector.getPlayers().get("tenCards");

        assertFalse(new FuckYou().isPlayable(connector.getGame().getGameState(), "hasAll"));
        assertTrue(new FuckYou().isPlayable(connector.getGame().getGameState(), "tenCards"));

        tenCards.play(new FuckYou());
        assertEquals("RED: 5" ,connector.getGame().getGameState().getLastCardName());

    }
}
