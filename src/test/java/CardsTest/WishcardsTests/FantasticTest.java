package CardsTest.WishcardsTests;

import Cards.Wishcards.Fantastic;
import Connector.TestConnector;
import Players.TestHelpingBot;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FantasticTest {
    @Test
    public void constructorTest() {
        assertNull(new Fantastic().getColor());
        assertEquals("Fantastic", new Fantastic().getName());
        assertEquals(0, new Fantastic().getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");

        hasAll.play(new Fantastic());

        assertNull(connector.getGame().getGameState().getPlayableColor());
        assertEquals(7, connector.getGame().getGameState().getPlayableNumber());
        assertEquals("Fantastic: 7", connector.getGame().getGameState().getLastCardName());
    }
}
