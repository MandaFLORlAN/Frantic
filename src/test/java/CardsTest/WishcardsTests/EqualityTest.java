package CardsTest.WishcardsTests;

import Cards.Wishcards.Equality;
import Connector.TestConnector;
import Players.TestHelpingBot;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EqualityTest {
    @Test
    public void constructorTest() {
        assertNull(new Equality().getColor());
        assertEquals("Equality", new Equality().getName());
        assertEquals(0, new Equality().getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");
        TestHelpingBot threeCards = (TestHelpingBot) connector.getPlayers().get("threeCards");

        hasAll.play(new Equality());

        assertEquals(CardDatabase.UNIQUE_CARDS-1, threeCards.getCards().size());
    }
}
