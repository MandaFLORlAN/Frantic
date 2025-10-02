package CardsTest.WishcardsTests;

import Cards.Wishcards.Fantastic;
import Cards.Wishcards.FantasticFour;
import Connector.TestConnector;
import Players.TestHelpingBot;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FantasticFourTest {
    @Test
    public void constructorTest() {
        assertNull(new Fantastic().getColor());
        assertEquals("FantasticFour", new FantasticFour().getName());
        assertEquals(0, new FantasticFour().getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");
        TestHelpingBot threeCards = (TestHelpingBot) connector.getPlayers().get("threeCards");
        TestHelpingBot fiveCards = (TestHelpingBot) connector.getPlayers().get("fiveCards");
        TestHelpingBot tenCards = (TestHelpingBot) connector.getPlayers().get("tenCards");

        hasAll.play(new FantasticFour());

        assertNull(connector.getGame().getGameState().getPlayableColor());
        assertEquals(7, connector.getGame().getGameState().getPlayableNumber());
        assertEquals("FantasticFour: 7", connector.getGame().getGameState().getLastCardName());

        assertEquals(5, threeCards.getCards().size());
        assertEquals(6, fiveCards.getCards().size());
        assertEquals(11, tenCards.getCards().size());
    }
}
